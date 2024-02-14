package io.geekidea.boot.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.config.properties.MerchantLineProperties;
import io.geekidea.boot.framework.mybatis.plugins.MerchantLineInnerInterceptor;
import io.geekidea.boot.framework.mybatis.plugins.handler.DataScopeHandler;
import io.geekidea.boot.framework.mybatis.plugins.handler.MerchantLineHandler;
import io.geekidea.boot.util.SystemTypeUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * MybatisPlus配置
 *
 * @author geekidea
 * @date 2023/11/25
 **/
@Slf4j
@Configuration
public class MybatisPlusConfig {

    @Autowired
    private MerchantLineProperties merchantLineProperties;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 数据范围权限
        interceptor.addInnerInterceptor(new DataPermissionInterceptor(new DataScopeHandler()));
        // 多商户插件，默认关闭，如有需要，放开注释即可
        // interceptor.addInnerInterceptor(new MerchantLineInnerInterceptor(merchantLineHandler()));
        // 攻击 SQL 阻断解析器,防止全表更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 配置多商户插件
     * 如果不需要删除即可
     *
     * @return
     */
    @Bean
    public MerchantLineHandler merchantLineHandler() {
        log.info("merchantLineProperties:" + merchantLineProperties);
        return new MerchantLineHandler() {
            @Override
            public Expression getMerchantId() {
                // TODO 可以在LoginUtil中添加获取商户ID的方法
                // LoginUtil.getMerchantId();
                return new LongValue(1);
            }

            @Override
            public String getMerchantIdColumn() {
                return merchantLineProperties.getMerchantIdColumn();
            }

            @Override
            public boolean ignoreTable(String tableName) {
                List<String> includeTables = merchantLineProperties.getIncludeTables();
                // 判断如果包含指定的表，则不忽略，否则忽略
                if (CollectionUtils.isNotEmpty(includeTables)) {
                    if (includeTables.contains(tableName)) {
                        return false;
                    }
                }
                return true;
            }

            /**
             * 判断系统类型和系统角色，是否忽略添加商户ID
             * 添加商户ID的情况
             * 1：是管理后台的请求
             * 2：非管理员
             * 除此之外，都忽略
             *
             * @return
             */
            @Override
            public boolean ignoreMerchantId() {
                try {
                    if (SystemTypeUtil.isAdminSystem() && LoginUtil.isNotAdmin()) {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
    }

}
