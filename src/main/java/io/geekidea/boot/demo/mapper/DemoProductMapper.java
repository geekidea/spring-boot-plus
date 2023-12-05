package io.geekidea.boot.demo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.demo.entity.DemoProduct;
import io.geekidea.boot.demo.query.DemoProductAppQuery;
import io.geekidea.boot.demo.query.DemoProductQuery;
import io.geekidea.boot.demo.vo.DemoProductAppVo;
import io.geekidea.boot.demo.vo.DemoProductVo;
import io.geekidea.boot.framework.mybatis.plugins.MerchantLineInnerInterceptor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 测试商品 Mapper 接口
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Mapper
public interface DemoProductMapper extends BaseMapper<DemoProduct> {

    /**
     * 测试商品详情
     *
     * @param id
     * @return
     */
    @InterceptorIgnore(others = MerchantLineInnerInterceptor.IGNORE)
    DemoProductVo getDemoProductById(Long id);

    /**
     * 测试商品分页列表
     *
     * @param query
     * @return
     */
    List<DemoProductVo> getDemoProductPage(DemoProductQuery query);

    /**
     * App测试商品详情
     *
     * @param id
     * @return
     */
    DemoProductAppVo getAppDemoProductById(Long id);

    /**
     * App测试商品分页列表
     *
     * @param query
     * @return
     */
    List<DemoProductAppVo> getAppDemoProductPage(DemoProductAppQuery query);

}
