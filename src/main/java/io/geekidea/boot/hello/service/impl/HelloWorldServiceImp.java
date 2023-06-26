package io.geekidea.boot.hello.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.hello.dto.HelloWorldAddDto;
import io.geekidea.boot.hello.dto.HelloWorldUpdateDto;
import io.geekidea.boot.hello.entity.HelloWorld;
import io.geekidea.boot.hello.mapper.HelloWorldMapper;
import io.geekidea.boot.hello.query.HelloWorldQuery;
import io.geekidea.boot.hello.service.HelloWorldService;
import io.geekidea.boot.hello.vo.HelloWorldInfoVo;
import io.geekidea.boot.hello.vo.HelloWorldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 你好世界 服务实现类
 *
 * @author geekidea
 * @since 2023-06-26
 */
@Slf4j
@Service
public class HelloWorldServiceImp extends BaseServiceImpl<HelloWorldMapper, HelloWorld> implements HelloWorldService {

    @Autowired
    private HelloWorldMapper helloWorldMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addHelloWorld(HelloWorldAddDto helloWorldAddDto) throws Exception {
        HelloWorld helloWorld = new HelloWorld();
        BeanUtils.copyProperties(helloWorldAddDto, helloWorld);
        return save(helloWorld);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateHelloWorld(HelloWorldUpdateDto helloWorldUpdateDto) throws Exception {
        Long id = helloWorldUpdateDto.getId();
        HelloWorld helloWorld = getById(id);
        if (helloWorld == null) {
            throw new BusinessException("你好世界不存在");
        }
        BeanUtils.copyProperties(helloWorldUpdateDto, helloWorld);
        helloWorld.setUpdateTime(new Date());
        return updateById(helloWorld);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteHelloWorld(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public HelloWorldInfoVo getHelloWorldById(Long id) throws Exception {
        return helloWorldMapper.getHelloWorldById(id);
    }

    @Override
    public Paging<HelloWorldVo> getHelloWorldList(HelloWorldQuery helloWorldQuery) throws Exception {
        handlePage(helloWorldQuery, OrderByItem.desc("id"));
        List<HelloWorldVo> list = helloWorldMapper.getHelloWorldList(helloWorldQuery);
        Paging<HelloWorldVo> paging = new Paging<>(list);
        return paging;
    }

}
