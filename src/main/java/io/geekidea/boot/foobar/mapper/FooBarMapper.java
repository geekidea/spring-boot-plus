package io.geekidea.boot.foobar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.foobar.entity.FooBar;
import io.geekidea.boot.foobar.query.FooBarQuery;
import io.geekidea.boot.foobar.vo.FooBarInfoVo;
import io.geekidea.boot.foobar.vo.FooBarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FooBar Mapper 接口
 *
 * @author geekidea
 * @since 2023-11-14
 */
@Mapper
public interface FooBarMapper extends BaseMapper<FooBar> {

    /**
     * FooBar详情
     *
     * @param id
     * @return
     */
    FooBarInfoVo getFooBarById(Long id);

    /**
     * FooBar分页列表
     *
     * @param fooBarQuery
     * @return
     */
    List<FooBarVo> getFooBarPage(FooBarQuery fooBarQuery);

}
