package io.geekidea.boot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.demo.dto.FooBarDto;
import io.geekidea.boot.demo.entity.FooBar;
import io.geekidea.boot.demo.mapper.FooBarMapper;
import io.geekidea.boot.demo.query.FooBarQuery;
import io.geekidea.boot.demo.service.FooBarService;
import io.geekidea.boot.demo.vo.FooBarVo;
import io.geekidea.boot.demo.query.FooBarAppQuery;
import io.geekidea.boot.demo.vo.FooBarAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * FooBar 服务实现类
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Slf4j
@Service
public class FooBarServiceImpl extends ServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFooBar(FooBarDto fooBarDto) throws Exception {
        FooBar fooBar = new FooBar();
        BeanUtils.copyProperties(fooBarDto, fooBar);
        return save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBarDto fooBarDto) throws Exception {
        Long id = fooBarDto.getId();
        FooBar fooBar = getById(id);
        if (fooBar == null) {
            throw new BusinessException("FooBar不存在");
        }
        BeanUtils.copyProperties(fooBarDto, fooBar);
        fooBar.setUpdateTime(new Date());
        return updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public FooBarVo getFooBarById(Long id) throws Exception {
        return fooBarMapper.getFooBarById(id);
    }

    @Override
    public Paging<FooBarVo> getFooBarPage(FooBarQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<FooBarVo> list = fooBarMapper.getFooBarPage(query);
        Paging<FooBarVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public FooBarAppVo getAppFooBarById(Long id) throws Exception {
        return fooBarMapper.getAppFooBarById(id);
    }

    @Override
    public Paging<FooBarAppVo> getAppFooBarPage(FooBarAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<FooBarAppVo> list = fooBarMapper.getAppFooBarPage(query);
        Paging<FooBarAppVo> paging = new Paging<>(list);
        return paging;
    }

}
