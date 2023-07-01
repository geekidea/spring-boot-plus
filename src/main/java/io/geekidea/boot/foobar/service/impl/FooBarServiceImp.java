package io.geekidea.boot.foobar.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.foobar.dto.FooBarAddDto;
import io.geekidea.boot.foobar.dto.FooBarUpdateDto;
import io.geekidea.boot.foobar.entity.FooBar;
import io.geekidea.boot.foobar.mapper.FooBarMapper;
import io.geekidea.boot.foobar.query.FooBarQuery;
import io.geekidea.boot.foobar.service.FooBarService;
import io.geekidea.boot.foobar.vo.FooBarInfoVo;
import io.geekidea.boot.foobar.vo.FooBarVo;
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
 * @since 2023-07-01
 */
@Slf4j
@Service
public class FooBarServiceImp extends BaseServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFooBar(FooBarAddDto fooBarAddDto) throws Exception {
        FooBar fooBar = new FooBar();
        BeanUtils.copyProperties(fooBarAddDto, fooBar);
        return save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBarUpdateDto fooBarUpdateDto) throws Exception {
        Long id = fooBarUpdateDto.getId();
        FooBar fooBar = getById(id);
        if (fooBar == null) {
            throw new BusinessException("FooBar不存在");
        }
        BeanUtils.copyProperties(fooBarUpdateDto, fooBar);
        fooBar.setUpdateTime(new Date());
        return updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public FooBarInfoVo getFooBarById(Long id) throws Exception {
        return fooBarMapper.getFooBarById(id);
    }

    @Override
    public Paging<FooBarVo> getFooBarList(FooBarQuery fooBarQuery) throws Exception {
        handlePage(fooBarQuery, OrderByItem.desc("id"));
        List<FooBarVo> list = fooBarMapper.getFooBarList(fooBarQuery);
        Paging<FooBarVo> paging = new Paging<>(list);
        return paging;
    }

}
