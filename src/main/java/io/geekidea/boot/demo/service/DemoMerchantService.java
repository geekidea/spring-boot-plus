package io.geekidea.boot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.demo.dto.DemoMerchantDto;
import io.geekidea.boot.demo.entity.DemoMerchant;
import io.geekidea.boot.demo.query.DemoMerchantQuery;
import io.geekidea.boot.demo.vo.DemoMerchantVo;
import io.geekidea.boot.demo.query.DemoMerchantAppQuery;
import io.geekidea.boot.demo.vo.DemoMerchantAppVo;


/**
 * 测试商户 服务接口
 *
 * @author geekidea
 * @since 2023-12-05
 */
public interface DemoMerchantService extends IService<DemoMerchant> {

    /**
     * 添加测试商户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addDemoMerchant(DemoMerchantDto dto) throws Exception;

    /**
     * 修改测试商户
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateDemoMerchant(DemoMerchantDto dto) throws Exception;

    /**
     * 删除测试商户
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteDemoMerchant(Long id) throws Exception;

    /**
     * 测试商户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DemoMerchantVo getDemoMerchantById(Long id) throws Exception;

    /**
     * 测试商户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DemoMerchantVo> getDemoMerchantPage(DemoMerchantQuery query) throws Exception;

    /**
     * App测试商户详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DemoMerchantAppVo getAppDemoMerchantById(Long id) throws Exception;

    /**
     * App测试商户分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DemoMerchantAppVo> getAppDemoMerchantPage(DemoMerchantAppQuery query) throws Exception;

}
