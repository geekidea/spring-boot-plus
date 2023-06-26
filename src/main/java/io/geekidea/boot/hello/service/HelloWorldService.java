package io.geekidea.boot.hello.service;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.BaseService;
import io.geekidea.boot.hello.dto.HelloWorldAddDto;
import io.geekidea.boot.hello.dto.HelloWorldUpdateDto;
import io.geekidea.boot.hello.entity.HelloWorld;
import io.geekidea.boot.hello.query.HelloWorldQuery;
import io.geekidea.boot.hello.vo.HelloWorldInfoVo;
import io.geekidea.boot.hello.vo.HelloWorldVo;

/**
 * 你好世界 服务接口
 *
 * @author geekidea
 * @since 2023-06-26
 */
public interface HelloWorldService extends BaseService<HelloWorld> {

    /**
     * 添加你好世界
     *
     * @param helloWorldAddDto
     * @return
     * @throws Exception
     */
    boolean addHelloWorld(HelloWorldAddDto helloWorldAddDto) throws Exception;

    /**
     * 修改你好世界
     *
     * @param helloWorldUpdateDto
     * @return
     * @throws Exception
     */
    boolean updateHelloWorld(HelloWorldUpdateDto helloWorldUpdateDto) throws Exception;

    /**
     * 删除你好世界
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteHelloWorld(Long id) throws Exception;

    /**
     * 你好世界详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    HelloWorldInfoVo getHelloWorldById(Long id) throws Exception;

    /**
     * 你好世界分页列表
     *
     * @param helloWorldQuery
     * @return
     * @throws Exception
     */
    Paging<HelloWorldVo> getHelloWorldList(HelloWorldQuery helloWorldQuery) throws Exception;

}
