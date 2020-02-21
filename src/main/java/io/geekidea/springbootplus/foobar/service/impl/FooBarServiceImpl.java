package io.geekidea.springbootplus.foobar.service.impl;

import com.alibaba.fastjson.JSON;
import io.geekidea.springbootplus.common.pagination.PageUtil;
import io.geekidea.springbootplus.foobar.entity.FooBar;
import io.geekidea.springbootplus.foobar.mapper.FooBarMapper;
import io.geekidea.springbootplus.foobar.service.FooBarService;
import io.geekidea.springbootplus.foobar.param.FooBarPageParam;
import io.geekidea.springbootplus.foobar.vo.FooBarQueryVo;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.pagination.Paging;
import io.geekidea.springbootplus.util.LambdaColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;


/**
 * <pre>
 * FooBar 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-01
 */
@Slf4j
@Service
public class FooBarServiceImpl extends BaseServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveFooBar(FooBar fooBar) throws Exception {
        return super.save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBar fooBar) throws Exception {
        return super.updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public FooBarQueryVo getFooBarById(Serializable id) throws Exception {
        return fooBarMapper.getFooBarById(id);
    }

    /**
     *  String optimizeLimitColumn = getLambdaColumn(FooBar::getId);
     *  等价于
     *  String optimizeLimitColumn = new LambdaColumn<FooBar>().get(FooBar::getId);
     * @param fooBarPageParam
     * @return
     * @throws Exception
     */
    @Override
    public Paging<FooBarQueryVo> getFooBarPageList(FooBarPageParam fooBarPageParam) throws Exception {
        String optimizeLimitColumn = getLambdaColumn(FooBar::getId);
        Page page = PageUtil.getPage(fooBarPageParam, true, optimizeLimitColumn);
        IPage<FooBarQueryVo> iPage = fooBarMapper.getFooBarPageList(page, fooBarPageParam);
        return new Paging(iPage, true, optimizeLimitColumn);
    }

}
