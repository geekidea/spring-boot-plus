package io.geekidea.springbootplus.foobar.service;

import io.geekidea.springbootplus.foobar.entity.FooBar;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.foobar.param.FooBarPageParam;
import io.geekidea.springbootplus.foobar.vo.FooBarQueryVo;
import io.geekidea.springbootplus.common.pagination.Paging;

import java.io.Serializable;

/**
 * <pre>
 * FooBar 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-01
 */
public interface FooBarService extends BaseService<FooBar> {

    /**
     * 保存
     *
     * @param fooBar
     * @return
     * @throws Exception
     */
    boolean saveFooBar(FooBar fooBar) throws Exception;

    /**
     * 修改
     *
     * @param fooBar
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBar fooBar) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteFooBar(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    FooBarQueryVo getFooBarById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param fooBarPageParam
     * @return
     * @throws Exception
     */
    Paging<FooBarQueryVo> getFooBarPageList(FooBarPageParam fooBarPageParam) throws Exception;

}
