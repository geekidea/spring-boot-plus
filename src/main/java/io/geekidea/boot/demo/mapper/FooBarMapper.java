package io.geekidea.boot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.demo.entity.FooBar;
import io.geekidea.boot.demo.query.FooBarQuery;
import io.geekidea.boot.demo.vo.FooBarVo;
import io.geekidea.boot.demo.query.FooBarAppQuery;
import io.geekidea.boot.demo.vo.FooBarAppVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FooBar Mapper 接口
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Mapper
public interface FooBarMapper extends BaseMapper<FooBar> {

    /**
     * FooBar详情
     *
     * @param id
     * @return
     */
    FooBarVo getFooBarById(Long id);

    /**
     * FooBar分页列表
     *
     * @param query
     * @return
     */
    List<FooBarVo> getFooBarPage(FooBarQuery query);

    /**
     * AppFooBar详情
     *
     * @param id
     * @return
     */
    FooBarAppVo getAppFooBarById(Long id);

    /**
     * AppFooBar分页列表
     *
     * @param query
     * @return
     */
    List<FooBarAppVo> getAppFooBarPage(FooBarAppQuery query);

}
