package io.geekidea.springbootplus.foobar.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.foobar.entity.FooBar;
import io.geekidea.springbootplus.foobar.param.FooBarPageParam;
import io.geekidea.springbootplus.foobar.vo.FooBarQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * FooBar Mapper 接口
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-01
 */
@Repository
public interface FooBarMapper extends BaseMapper<FooBar> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    FooBarQueryVo getFooBarById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param fooBarPageParam
     * @return
     */
    IPage<FooBarQueryVo> getFooBarPageList(@Param("page") Page page, @Param("param") FooBarPageParam fooBarPageParam);

}
