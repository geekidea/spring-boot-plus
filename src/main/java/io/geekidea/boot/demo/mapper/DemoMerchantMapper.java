package io.geekidea.boot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.demo.entity.DemoMerchant;
import io.geekidea.boot.demo.query.DemoMerchantQuery;
import io.geekidea.boot.demo.vo.DemoMerchantVo;
import io.geekidea.boot.demo.query.DemoMerchantAppQuery;
import io.geekidea.boot.demo.vo.DemoMerchantAppVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 测试商户 Mapper 接口
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Mapper
public interface DemoMerchantMapper extends BaseMapper<DemoMerchant> {

    /**
     * 测试商户详情
     *
     * @param id
     * @return
     */
    DemoMerchantVo getDemoMerchantById(Long id);

    /**
     * 测试商户分页列表
     *
     * @param query
     * @return
     */
    List<DemoMerchantVo> getDemoMerchantPage(DemoMerchantQuery query);

    /**
     * App测试商户详情
     *
     * @param id
     * @return
     */
    DemoMerchantAppVo getAppDemoMerchantById(Long id);

    /**
     * App测试商户分页列表
     *
     * @param query
     * @return
     */
    List<DemoMerchantAppVo> getAppDemoMerchantPage(DemoMerchantAppQuery query);

}
