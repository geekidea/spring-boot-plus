package io.geekidea.boot.framework.mybatis.plugins;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.plugins.inner.BaseMultiTableInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.toolkit.PropertyMapper;
import io.geekidea.boot.framework.mybatis.plugins.handler.MerchantLineHandler;
import lombok.*;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.RowConstructor;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 多商户拦截器
 * 参考 {@link com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor}
 *
 * @author geekidea
 * @date 2023/12/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings({"rawtypes"})
public class MerchantLineInnerInterceptor extends BaseMultiTableInnerInterceptor implements InnerInterceptor {

    public static final String MERCHANT_LINE_HANDLER = "merchantLineHandler";
    public static final String IGNORE = MERCHANT_LINE_HANDLER + "@true";

    private static final String _COUNT = "_COUNT";

    private MerchantLineHandler merchantLineHandler;

    /**
     * 处理pageHelper的_count查询过滤
     *
     * @param ms
     * @return
     */
    private String getMappedStatementId(MappedStatement ms) {
        String msId = ms.getId();
        if (msId.endsWith(_COUNT)) {
            msId = msId.substring(0, msId.lastIndexOf(_COUNT));
        }
        return msId;
    }


    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (merchantLineHandler.ignoreMerchantId()) {
            return;
        }
        String mappedStatementId = getMappedStatementId(ms);
        if (InterceptorIgnoreHelper.willIgnoreOthersByKey(mappedStatementId, MERCHANT_LINE_HANDLER)) {
            return;
        }
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), null));
    }

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = mpSh.mappedStatement();
        SqlCommandType sct = ms.getSqlCommandType();
        if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE || sct == SqlCommandType.DELETE) {
            if (merchantLineHandler.ignoreMerchantId()) {
                return;
            }
            String mappedStatementId = getMappedStatementId(ms);
            if (InterceptorIgnoreHelper.willIgnoreOthersByKey(mappedStatementId, MERCHANT_LINE_HANDLER)) {
                return;
            }
            PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
            mpBs.sql(parserMulti(mpBs.sql(), null));
        }
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj) {
        final String whereSegment = (String) obj;
        processSelectBody(select.getSelectBody(), whereSegment);
        List<WithItem> withItemsList = select.getWithItemsList();
        if (!CollectionUtils.isEmpty(withItemsList)) {
            withItemsList.forEach(withItem -> processSelectBody(withItem, whereSegment));
        }
    }

    @Override
    protected void processInsert(Insert insert, int index, String sql, Object obj) {
        if (merchantLineHandler.ignoreTable(insert.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        List<Column> columns = insert.getColumns();
        if (CollectionUtils.isEmpty(columns)) {
            // 针对不给列名的insert 不处理
            return;
        }
        String merchantIdColumn = merchantLineHandler.getMerchantIdColumn();
        if (merchantLineHandler.ignoreInsert(columns, merchantIdColumn)) {
            // 针对已给出商户列的insert 不处理
            return;
        }
        columns.add(new Column(merchantIdColumn));

        List<Expression> duplicateUpdateColumns = insert.getDuplicateUpdateExpressionList();
        if (CollectionUtils.isNotEmpty(duplicateUpdateColumns)) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new StringValue(merchantIdColumn));
            equalsTo.setRightExpression(merchantLineHandler.getMerchantId());
            duplicateUpdateColumns.add(equalsTo);
        }

        Select select = insert.getSelect();
        if (select != null && (select.getSelectBody() instanceof PlainSelect)) {
            this.processInsertSelect(select.getSelectBody(), (String) obj);
        } else if (insert.getItemsList() != null) {
            ItemsList itemsList = insert.getItemsList();
            Expression merchantId = merchantLineHandler.getMerchantId();
            if (itemsList instanceof MultiExpressionList) {
                ((MultiExpressionList) itemsList).getExpressionLists().forEach(el -> el.getExpressions().add(merchantId));
            } else {
                List<Expression> expressions = ((ExpressionList) itemsList).getExpressions();
                if (CollectionUtils.isNotEmpty(expressions)) {
                    int len = expressions.size();
                    for (int i = 0; i < len; i++) {
                        Expression expression = expressions.get(i);
                        if (expression instanceof RowConstructor) {
                            ((RowConstructor) expression).getExprList().getExpressions().add(merchantId);
                        } else if (expression instanceof Parenthesis) {
                            RowConstructor rowConstructor = new RowConstructor()
                                    .withExprList(new ExpressionList(((Parenthesis) expression).getExpression(), merchantId));
                            expressions.set(i, rowConstructor);
                        } else {
                            if (len - 1 == i) {
                                expressions.add(merchantId);
                            }
                        }
                    }
                } else {
                    expressions.add(merchantId);
                }
            }
        } else {
            throw ExceptionUtils.mpe("Failed to process multiple-table update, please exclude the tableName or statementId");
        }
    }

    /**
     * update 语句处理
     */
    @Override
    protected void processUpdate(Update update, int index, String sql, Object obj) {
        final Table table = update.getTable();
        if (merchantLineHandler.ignoreTable(table.getName())) {
            // 过滤退出执行
            return;
        }
        ArrayList<UpdateSet> sets = update.getUpdateSets();
        if (!CollectionUtils.isEmpty(sets)) {
            sets.forEach(us -> us.getExpressions().forEach(ex -> {
                if (ex instanceof SubSelect) {
                    processSelectBody(((SubSelect) ex).getSelectBody(), (String) obj);
                }
            }));
        }
        update.setWhere(this.andExpression(table, update.getWhere(), (String) obj));
    }

    /**
     * delete 语句处理
     */
    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {
        if (merchantLineHandler.ignoreTable(delete.getTable().getName())) {
            // 过滤退出执行
            return;
        }
        delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere(), (String) obj));
    }

    /**
     * 处理 insert into select
     * 进入这里表示需要 insert 的表启用了多商户,则 select 的表都启动了
     *
     * @param selectBody SelectBody
     */
    protected void processInsertSelect(SelectBody selectBody, final String whereSegment) {
        PlainSelect plainSelect = (PlainSelect) selectBody;
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            processPlainSelect(plainSelect, whereSegment);
            appendSelectItem(plainSelect.getSelectItems());
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            appendSelectItem(plainSelect.getSelectItems());
            processInsertSelect(subSelect.getSelectBody(), whereSegment);
        }
    }

    /**
     * 追加 SelectItem
     *
     * @param selectItems SelectItem
     */
    protected void appendSelectItem(List<SelectItem> selectItems) {
        if (CollectionUtils.isEmpty(selectItems)) {
            return;
        }
        if (selectItems.size() == 1) {
            SelectItem item = selectItems.get(0);
            if (item instanceof AllColumns || item instanceof AllTableColumns) {
                return;
            }
        }
        selectItems.add(new SelectExpressionItem(new Column(merchantLineHandler.getMerchantIdColumn())));
    }

    /**
     * 商户字段别名设置
     * <p>merchantId 或 tableAlias.merchantId</p>
     *
     * @param table 表对象
     * @return 字段
     */
    protected Column getAliasColumn(Table table) {
        StringBuilder column = new StringBuilder();
        // 该起别名就要起别名,禁止修改此处逻辑
        if (table.getAlias() != null) {
            column.append(table.getAlias().getName()).append(StringPool.DOT);
        }
        column.append(merchantLineHandler.getMerchantIdColumn());
        return new Column(column.toString());
    }

    @Override
    public void setProperties(Properties properties) {
        PropertyMapper.newInstance(properties).whenNotBlank(MERCHANT_LINE_HANDLER,
                ClassUtils::newInstance, this::setMerchantLineHandler);
    }

    /**
     * 构建商户条件表达式
     *
     * @param table        表对象
     * @param where        当前where条件
     * @param whereSegment 所属Mapper对象全路径（在原商户拦截器功能中，这个参数并不需要参与相关判断）
     * @return 商户条件表达式
     * @see BaseMultiTableInnerInterceptor#buildTableExpression(Table, Expression, String)
     */
    @Override
    public Expression buildTableExpression(final Table table, final Expression where, final String whereSegment) {
        System.out.println("merchantLineHandler = " + merchantLineHandler);
        if (merchantLineHandler.ignoreTable(table.getName())) {
            return null;
        }
        return new EqualsTo(getAliasColumn(table), merchantLineHandler.getMerchantId());
    }

}
