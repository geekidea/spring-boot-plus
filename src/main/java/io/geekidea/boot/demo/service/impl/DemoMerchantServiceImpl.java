package io.geekidea.boot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.demo.dto.DemoMerchantDto;
import io.geekidea.boot.demo.entity.DemoMerchant;
import io.geekidea.boot.demo.mapper.DemoMerchantMapper;
import io.geekidea.boot.demo.query.DemoMerchantQuery;
import io.geekidea.boot.demo.service.DemoMerchantService;
import io.geekidea.boot.demo.vo.DemoMerchantVo;
import io.geekidea.boot.demo.query.DemoMerchantAppQuery;
import io.geekidea.boot.demo.vo.DemoMerchantAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 测试商户 服务实现类
 *
 * @author geekidea
 * @since 2023-12-05
 */
@Slf4j
@Service
public class DemoMerchantServiceImpl extends ServiceImpl<DemoMerchantMapper, DemoMerchant> implements DemoMerchantService {

    @Autowired
    private DemoMerchantMapper demoMerchantMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addDemoMerchant(DemoMerchantDto dto) throws Exception {
        DemoMerchant demoMerchant = new DemoMerchant();
        BeanUtils.copyProperties(dto, demoMerchant);
        return save(demoMerchant);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDemoMerchant(DemoMerchantDto dto) throws Exception {
        Long id = dto.getId();
        DemoMerchant demoMerchant = getById(id);
        if (demoMerchant == null) {
            throw new BusinessException("测试商户不存在");
        }
        BeanUtils.copyProperties(dto, demoMerchant);
        demoMerchant.setUpdateTime(new Date());
        return updateById(demoMerchant);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDemoMerchant(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public DemoMerchantVo getDemoMerchantById(Long id) throws Exception {
        return demoMerchantMapper.getDemoMerchantById(id);
    }

    @Override
    public Paging<DemoMerchantVo> getDemoMerchantPage(DemoMerchantQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoMerchantVo> list = demoMerchantMapper.getDemoMerchantPage(query);
        Paging<DemoMerchantVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public DemoMerchantAppVo getAppDemoMerchantById(Long id) throws Exception {
        return demoMerchantMapper.getAppDemoMerchantById(id);
    }

    @Override
    public Paging<DemoMerchantAppVo> getAppDemoMerchantPage(DemoMerchantAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DemoMerchantAppVo> list = demoMerchantMapper.getAppDemoMerchantPage(query);
        Paging<DemoMerchantAppVo> paging = new Paging<>(list);
        return paging;
    }

}
