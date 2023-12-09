package io.geekidea.boot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.demo.dto.DemoProductDto;
import io.geekidea.boot.demo.entity.DemoProduct;
import io.geekidea.boot.demo.mapper.DemoProductMapper;
import io.geekidea.boot.demo.query.DemoProductQuery;
import io.geekidea.boot.demo.service.DemoProductService;
import io.geekidea.boot.demo.vo.DemoProductVo;
import io.geekidea.boot.demo.query.DemoProductAppQuery;
import io.geekidea.boot.demo.vo.DemoProductAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 测试商品 服务实现类
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@Service
public class DemoProductServiceImpl extends ServiceImpl<DemoProductMapper, DemoProduct> implements DemoProductService {

    @Autowired
    private DemoProductMapper demoProductMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addDemoProduct(DemoProductDto dto) throws Exception {
        DemoProduct demoProduct = new DemoProduct();
        BeanUtils.copyProperties(dto, demoProduct);
        return save(demoProduct);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDemoProduct(DemoProductDto dto) throws Exception {
        Long id = dto.getId();
        DemoProduct demoProduct = getById(id);
        if (demoProduct == null) {
            throw new BusinessException("测试商品不存在");
        }
        BeanUtils.copyProperties(dto, demoProduct);
        demoProduct.setUpdateTime(new Date());
        return updateById(demoProduct);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDemoProduct(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public DemoProductVo getDemoProductById(Long id) throws Exception {
        return demoProductMapper.getDemoProductById(id);
    }

    @Override
    public Paging<DemoProductVo> getDemoProductPage(DemoProductQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoProductVo> list = demoProductMapper.getDemoProductPage(query);
        Paging<DemoProductVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public DemoProductAppVo getAppDemoProductById(Long id) throws Exception {
        return demoProductMapper.getAppDemoProductById(id);
    }

    @Override
    public Paging<DemoProductAppVo> getAppDemoProductPage(DemoProductAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoProductAppVo> list = demoProductMapper.getAppDemoProductPage(query);
        Paging<DemoProductAppVo> paging = new Paging<>(list);
        return paging;
    }

}
