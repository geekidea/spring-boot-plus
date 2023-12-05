package io.geekidea.boot.framework.mybatis.plugins.handler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;

import java.util.List;

/**
 * 商户处理器（ merchantId 行级 ）
 *
 * @author geekidea
 * @date 2023/12/5
 **/
public interface MerchantLineHandler {
    /**
     * 获取商户 ID 值表达式，只支持单个 ID 值
     *
     * @return 商户 ID 值表达式
     */
    Expression getMerchantId();

    /**
     * 获取商户字段名
     * 默认字段名叫: merchant_id
     *
     * @return 商户字段名
     */
    default String getMerchantIdColumn() {
        return "merchant_id";
    }

    /**
     * 根据表名判断是否忽略拼接多商户条件
     * 默认都不进行解析拼接多商户条件
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接多商户条件
     */
    default boolean ignoreTable(String tableName) {
        return true;
    }

    /**
     * 根据登录信息判断是否忽略拼接多商户条件
     * 默认都不进行解析拼接多商户条件
     * @return
     */
   default boolean ignoreMerchantId(){
        return true;
   }

    /**
     * 忽略插入商户字段逻辑
     *
     * @param columns        插入字段
     * @param merchantColumn 商户 ID 字段
     * @return
     */
    default boolean ignoreInsert(List<Column> columns, String merchantColumn) {
        return columns.stream().map(Column::getColumnName).anyMatch(i -> i.equalsIgnoreCase(merchantColumn));
    }
}
