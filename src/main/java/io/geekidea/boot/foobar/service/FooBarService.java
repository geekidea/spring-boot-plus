package io.geekidea.boot.foobar.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.foobar.dto.FooBarAddDto;
import io.geekidea.boot.foobar.dto.FooBarUpdateDto;
import io.geekidea.boot.foobar.entity.FooBar;
import io.geekidea.boot.foobar.query.FooBarQuery;
import io.geekidea.boot.foobar.vo.FooBarInfoVo;
import io.geekidea.boot.foobar.vo.FooBarVo;

/**
 * FooBar 服务接口
 *
 * @author geekidea
 * @since 2023-07-01
 */
public interface FooBarService extends BaseService<FooBar> {

    /**
     * 添加FooBar
     *
     * @param fooBarAddDto
     * @return
     * @throws Exception
     */
    boolean addFooBar(FooBarAddDto fooBarAddDto) throws Exception;

    /**
     * 修改FooBar
     *
     * @param fooBarUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBarUpdateDto fooBarUpdateDto) throws Exception;

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteFooBar(Long id) throws Exception;

    /**
     * FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    FooBarInfoVo getFooBarById(Long id) throws Exception;

    /**
     * FooBar分页列表
     *
     * @param fooBarQuery
     * @return
     * @throws Exception
     */
    Paging<FooBarVo> getFooBarList(FooBarQuery fooBarQuery) throws Exception;

}
