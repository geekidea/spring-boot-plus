package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.framework.util.ObjectValueUtil;
import io.geekidea.boot.system.dto.SysDeptAddDto;
import io.geekidea.boot.system.dto.SysDeptUpdateDto;
import io.geekidea.boot.system.entity.SysDept;
import io.geekidea.boot.system.mapper.SysDeptMapper;
import io.geekidea.boot.system.query.SysDeptQuery;
import io.geekidea.boot.system.service.SysDeptService;
import io.geekidea.boot.system.vo.SysDeptInfoVo;
import io.geekidea.boot.system.vo.SysDeptTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysDept(SysDeptAddDto sysDeptAddDto) throws Exception {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(sysDeptAddDto, sysDept);
        return save(sysDept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysDept(SysDeptUpdateDto sysDeptUpdateDto) throws Exception {
        Long id = sysDeptUpdateDto.getId();
        SysDept sysDept = getById(id);
        if (sysDept == null) {
            throw new BusinessException("部门不存在");
        }
        BeanUtils.copyProperties(sysDeptUpdateDto, sysDept);
        sysDept.setUpdateTime(new Date());
        return updateById(sysDept);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysDept(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysDeptInfoVo getSysDeptById(Long id) throws Exception {
        return sysDeptMapper.getSysDeptById(id);
    }

    @Override
    public List<SysDeptTreeVo> getAllSysDeptTreeList(SysDeptQuery sysDeptQuery) throws Exception {
        List<SysDeptTreeVo> list = sysDeptMapper.getSysDeptTreeList(sysDeptQuery);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 如果搜索条件有值，则直接返回普通列表
        boolean flag = ObjectValueUtil.isHaveValue(sysDeptQuery);
        if (flag) {
            return list;
        }
        // 递归返回树形列表
        return recursionSysDeptTreeList(0L, list);
    }

    @Override
    public List<SysDeptTreeVo> getSysDeptTreeList() throws Exception {
        SysDeptQuery sysDeptQuery = new SysDeptQuery();
        sysDeptQuery.setStatus(true);
        List<SysDeptTreeVo> list = sysDeptMapper.getSysDeptTreeList(sysDeptQuery);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 递归返回树形列表
        return recursionSysDeptTreeList(0L, list);
    }


    /**
     * 递归设置部门树形菜单
     *
     * @param parentId
     * @param list
     * @return
     */
    private List<SysDeptTreeVo> recursionSysDeptTreeList(Long parentId, List<SysDeptTreeVo> list) {
        return list.stream()
                .filter(vo -> vo.getParentId().equals(parentId))
                .map(item -> {
                    item.setChildren(recursionSysDeptTreeList(item.getId(), list));
                    return item;
                })
                .collect(Collectors.toList());
    }

}
