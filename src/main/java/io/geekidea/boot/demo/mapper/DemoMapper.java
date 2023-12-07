package io.geekidea.boot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.demo.entity.Demo;
import io.geekidea.boot.demo.query.DemoQuery;
import io.geekidea.boot.demo.vo.DemoVo;
import io.geekidea.boot.demo.query.DemoAppQuery;
import io.geekidea.boot.demo.vo.DemoAppVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 演示 Mapper 接口
 *
 * @author geekidea
 * @since 2023-12-06
 */
@Mapper
public interface DemoMapper extends BaseMapper<Demo> {

    /**
     * 演示详情
     *
     * @param id
     * @return
     */
    DemoVo getDemoById(Long id);

    /**
     * 演示分页列表
     *
     * @param query
     * @return
     */
    List<DemoVo> getDemoPage(DemoQuery query);

    /**
     * App演示详情
     *
     * @param id
     * @return
     */
    DemoAppVo getAppDemoById(Long id);

    /**
     * App演示分页列表
     *
     * @param query
     * @return
     */
    List<DemoAppVo> getAppDemoPage(DemoAppQuery query);

}
