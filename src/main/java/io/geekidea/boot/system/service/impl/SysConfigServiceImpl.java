package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.SysConfigDto;
import io.geekidea.boot.system.entity.SysConfig;
import io.geekidea.boot.system.mapper.SysConfigMapper;
import io.geekidea.boot.system.query.SysConfigQuery;
import io.geekidea.boot.system.service.SysConfigService;
import io.geekidea.boot.system.vo.SysConfigVo;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统配置 服务实现类
 *
 * @author geekidea
 * @since 2023-11-27
 */
@Slf4j
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysConfig(SysConfigDto dto) {
        checkConfigKeyExists(dto.getConfigKey());
        SysConfig sysConfig = new SysConfig();
        BeanUtils.copyProperties(dto, sysConfig);
        return save(sysConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysConfig(SysConfigDto dto) {
        Long id = dto.getId();
        SysConfig sysConfig = getById(id);
        if (sysConfig == null) {
            throw new BusinessException("系统配置不存在");
        }
        BeanUtils.copyProperties(dto, sysConfig);
        return updateById(sysConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysConfig(Long id) {
        return removeById(id);
    }

    @Override
    public SysConfigVo getSysConfigById(Long id) {
        return sysConfigMapper.getSysConfigById(id);
    }

    @Override
    public Paging<SysConfigVo> getSysConfigPage(SysConfigQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<SysConfigVo> list = sysConfigMapper.getSysConfigPage(query);
        Paging<SysConfigVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public void checkConfigKeyExists(String configKey) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getConfigKey, configKey);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(configKey + "配置key已经存在");
        }
    }

}
