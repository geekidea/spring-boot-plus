package io.geekidea.boot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.demo.dto.DemoDto;
import io.geekidea.boot.demo.entity.Demo;
import io.geekidea.boot.demo.mapper.DemoMapper;
import io.geekidea.boot.demo.query.DemoQuery;
import io.geekidea.boot.demo.service.DemoService;
import io.geekidea.boot.demo.vo.DemoVo;
import io.geekidea.boot.demo.query.DemoAppQuery;
import io.geekidea.boot.demo.vo.DemoAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 演示 服务实现类
 *
 * @author geekidea
 * @since 2023-12-06
 */
@Slf4j
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addDemo(DemoDto demoDto) throws Exception {
        Demo demo = new Demo();
        BeanUtils.copyProperties(demoDto, demo);
        return save(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDemo(DemoDto demoDto) throws Exception {
        Long id = demoDto.getId();
        Demo demo = getById(id);
        if (demo == null) {
            throw new BusinessException("演示不存在");
        }
        BeanUtils.copyProperties(demoDto, demo);
        demo.setUpdateTime(new Date());
        return updateById(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDemo(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public DemoVo getDemoById(Long id) throws Exception {
        return demoMapper.getDemoById(id);
    }

    @Override
    public Paging<DemoVo> getDemoPage(DemoQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoVo> list = demoMapper.getDemoPage(query);
        Paging<DemoVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public DemoAppVo getAppDemoById(Long id) throws Exception {
        return demoMapper.getAppDemoById(id);
    }

    @Override
    public Paging<DemoAppVo> getAppDemoPage(DemoAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoAppVo> list = demoMapper.getAppDemoPage(query);
        Paging<DemoAppVo> paging = new Paging<>(list);
        return paging;
    }

}
