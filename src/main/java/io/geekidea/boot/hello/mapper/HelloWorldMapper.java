package io.geekidea.boot.hello.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.hello.entity.HelloWorld;
import io.geekidea.boot.hello.query.HelloWorldQuery;
import io.geekidea.boot.hello.vo.HelloWorldInfoVo;
import io.geekidea.boot.hello.vo.HelloWorldVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 你好世界 Mapper 接口
 *
 * @author geekidea
 * @since 2023-06-26
 */
@Mapper
public interface HelloWorldMapper extends BaseMapper<HelloWorld> {

    /**
     * 你好世界详情
     *
     * @param id
     * @return
     */
    HelloWorldInfoVo getHelloWorldById(Long id);

    /**
     * 你好世界分页列表
     *
     * @param helloWorldQuery
     * @return
     */
    List<HelloWorldVo> getHelloWorldList(HelloWorldQuery helloWorldQuery);

}
