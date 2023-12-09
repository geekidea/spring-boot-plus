package io.geekidea.boot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.demo.dto.FooBarDto;
import io.geekidea.boot.demo.entity.FooBar;
import io.geekidea.boot.demo.query.FooBarQuery;
import io.geekidea.boot.demo.vo.FooBarVo;
import io.geekidea.boot.demo.query.FooBarAppQuery;
import io.geekidea.boot.demo.vo.FooBarAppVo;


/**
 * FooBar 服务接口
 *
 * @author geekidea
 * @since 2023-12-02
 */
public interface FooBarService extends IService<FooBar> {

    /**
     * 添加FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addFooBar(FooBarDto dto) throws Exception;

    /**
     * 修改FooBar
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBarDto dto) throws Exception;

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
    FooBarVo getFooBarById(Long id) throws Exception;

    /**
     * FooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<FooBarVo> getFooBarPage(FooBarQuery query) throws Exception;

    /**
     * AppFooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    FooBarAppVo getAppFooBarById(Long id) throws Exception;

    /**
     * AppFooBar分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<FooBarAppVo> getAppFooBarPage(FooBarAppQuery query) throws Exception;

}
