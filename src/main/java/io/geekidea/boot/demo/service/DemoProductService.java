package io.geekidea.boot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.demo.dto.DemoProductDto;
import io.geekidea.boot.demo.entity.DemoProduct;
import io.geekidea.boot.demo.query.DemoProductQuery;
import io.geekidea.boot.demo.vo.DemoProductVo;
import io.geekidea.boot.demo.query.DemoProductAppQuery;
import io.geekidea.boot.demo.vo.DemoProductAppVo;


/**
 * 测试商品 服务接口
 *
 * @author geekidea
 * @since 2023-12-05
 */
public interface DemoProductService extends IService<DemoProduct> {

    /**
     * 添加测试商品
     *
     * @param demoProductDto
     * @return
     * @throws Exception
     */
    boolean addDemoProduct(DemoProductDto demoProductDto) throws Exception;

    /**
     * 修改测试商品
     *
     * @param demoProductDto
     * @return
     * @throws Exception
     */
    boolean updateDemoProduct(DemoProductDto demoProductDto) throws Exception;

    /**
     * 删除测试商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteDemoProduct(Long id) throws Exception;

    /**
     * 测试商品详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DemoProductVo getDemoProductById(Long id) throws Exception;

    /**
     * 测试商品分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DemoProductVo> getDemoProductPage(DemoProductQuery query) throws Exception;

    /**
     * App测试商品详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DemoProductAppVo getAppDemoProductById(Long id) throws Exception;

    /**
     * App测试商品分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DemoProductAppVo> getAppDemoProductPage(DemoProductAppQuery query) throws Exception;

}
