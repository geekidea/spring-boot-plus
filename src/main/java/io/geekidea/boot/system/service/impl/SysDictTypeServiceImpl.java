package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.system.dto.SysDictTypeDto;
import io.geekidea.boot.system.entity.SysDictType;
import io.geekidea.boot.system.mapper.SysDictTypeMapper;
import io.geekidea.boot.system.query.SysDictTypeQuery;
import io.geekidea.boot.system.service.SysDictTypeService;
import io.geekidea.boot.system.vo.SysDictTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 字典类型 服务实现类
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysDictType(SysDictTypeDto sysDictTypeDto) throws Exception {
        checkCodeExists(sysDictTypeDto.getCode());
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(sysDictTypeDto, sysDictType);
        return save(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDictType(SysDictTypeDto sysDictTypeDto) throws Exception {
        Long id = sysDictTypeDto.getId();
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("字典类型不存在");
        }
        sysDictType.setIsSystem(sysDictTypeDto.getIsSystem());
        sysDictType.setName(sysDictTypeDto.getName());
        sysDictType.setRemark(sysDictTypeDto.getRemark());
        sysDictType.setUpdateTime(new Date());
        return updateById(sysDictType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDictType(Long id) throws Exception {
        SysDictType sysDictType = getById(id);
        if (sysDictType == null) {
            throw new BusinessException("系统字典类型不存在");
        }
        Boolean isSystem = sysDictType.getIsSystem();
        if (isSystem) {
            throw new BusinessException("系统类型不能删除");
        }
        return removeById(id);
    }

    @Override
    public SysDictTypeVo getSysDictTypeById(Long id) throws Exception {
        return sysDictTypeMapper.getSysDictTypeById(id);
    }

    @Override
    public List<SysDictTypeVo> getSysDictTypeList(SysDictTypeQuery query) throws Exception {
        List<SysDictTypeVo> list = sysDictTypeMapper.getSysDictTypeList(query);
        return list;
    }

    @Override
    public void checkCodeExists(String code) throws Exception {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictType::getCode, code);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(code + "类型编码已经存在");
        }
    }

}
