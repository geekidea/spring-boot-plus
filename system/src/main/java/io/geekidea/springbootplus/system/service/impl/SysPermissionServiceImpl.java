/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.framework.common.exception.BusinessException;
import io.geekidea.springbootplus.framework.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.framework.core.pagination.PageInfo;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import io.geekidea.springbootplus.system.convert.SysPermissionConvert;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.enums.MenuLevelEnum;
import io.geekidea.springbootplus.system.enums.StateEnum;
import io.geekidea.springbootplus.system.mapper.SysPermissionMapper;
import io.geekidea.springbootplus.system.param.SysPermissionPageParam;
import io.geekidea.springbootplus.system.service.SysPermissionService;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.system.vo.SysPermissionTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <pre>
 * 系统权限 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-25
 */
@Slf4j
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysPermission(SysPermission sysPermission) throws Exception {
        sysPermission.setId(null);
        return super.save(sysPermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysPermission(SysPermission sysPermission) throws Exception {
        // 获取权限
        if (getById(sysPermission.getId()) == null) {
            throw new BusinessException("权限不存在");
        }
        sysPermission.setUpdateTime(new Date());
        return super.updateById(sysPermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysPermission(Long id) throws Exception {
        boolean isExists = sysRolePermissionService.isExistsByPermissionId(id);
        if (isExists) {
            throw new BusinessException("该权限存在角色关联关系，不能删除");
        }
        return super.removeById(id);
    }

    @Override
    public SysPermissionQueryVo getSysPermissionById(Serializable id) throws Exception {
        return sysPermissionMapper.getSysPermissionById(id);
    }

    @Override
    public Paging<SysPermissionQueryVo> getSysPermissionPageList(SysPermissionPageParam sysPermissionPageParam) throws Exception {
        Page<SysPermissionQueryVo> page = new PageInfo<>(sysPermissionPageParam, OrderItem.desc(getLambdaColumn(SysPermission::getCreateTime)));
        IPage<SysPermissionQueryVo> iPage = sysPermissionMapper.getSysPermissionPageList(page, sysPermissionPageParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isExistsByPermissionIds(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return false;
        }
        Wrapper wrapper = lambdaQuery().in(SysPermission::getId, permissionIds).getWrapper();
        return sysPermissionMapper.selectCount(wrapper).intValue() == permissionIds.size();
    }

    @Override
    public List<SysPermission> getAllMenuList() throws Exception {
        SysPermission sysPermission = new SysPermission().setState(StateEnum.ENABLE.getCode());
        // 获取所有已启用的权限列表
        return sysPermissionMapper.selectList(new QueryWrapper(sysPermission));
    }

    @Override
    public List<SysPermissionTreeVo> getAllMenuTree() throws Exception {
        List<SysPermission> list = getAllMenuList();
        // 转换成树形菜单
        List<SysPermissionTreeVo> treeVos = convertSysPermissionTreeVoList(list);
        return treeVos;
    }

    @Override
    public List<SysPermissionTreeVo> convertSysPermissionTreeVoList(List<SysPermission> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("SysPermission列表不能为空");
        }
        // 按level分组获取map
        Map<Integer, List<SysPermission>> map = list.stream().collect(Collectors.groupingBy(SysPermission::getLevel));
        List<SysPermissionTreeVo> treeVos = new ArrayList<>();
        // 循环获取三级菜单树形集合
        for (SysPermission one : map.get(MenuLevelEnum.ONE.getCode())) {
            SysPermissionTreeVo oneVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(one);
            Long oneParentId = oneVo.getParentId();
            if (oneParentId == null || oneParentId == 0) {
                treeVos.add(oneVo);
            }
            List<SysPermission> twoList = map.get(MenuLevelEnum.TWO.getCode());
            if (CollectionUtils.isNotEmpty(twoList)) {
                for (SysPermission two : twoList) {
                    SysPermissionTreeVo twoVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(two);
                    if (two.getParentId().equals(one.getId())) {
                        if (oneVo.getChildren() == null) {
                            oneVo.setChildren(new ArrayList<>());
                        }
                        oneVo.getChildren().add(twoVo);
                    }
                    List<SysPermission> threeList = map.get(MenuLevelEnum.THREE.getCode());
                    if (CollectionUtils.isNotEmpty(threeList)) {
                        for (SysPermission three : threeList) {
                            if (three.getParentId().equals(two.getId())) {
                                SysPermissionTreeVo threeVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(three);
                                if (twoVo.getChildren() == null) {
                                    twoVo.setChildren(new ArrayList<>());
                                }
                                twoVo.getChildren().add(threeVo);
                            }
                        }
                    }
                }
            }

        }
        return treeVos;
    }

    @Override
    public List<String> getPermissionCodesByUserId(Long userId) throws Exception {
        return sysPermissionMapper.getPermissionCodesByUserId(userId);
    }

    @Override
    public List<SysPermission> getMenuListByUserId(Long userId) throws Exception {
        return sysPermissionMapper.getMenuListByUserId(userId);
    }

    @Override
    public List<SysPermissionTreeVo> getMenuTreeByUserId(Long userId) throws Exception {
        List<SysPermission> list = getMenuListByUserId(userId);
        // 转换成树形菜单
        List<SysPermissionTreeVo> treeVos = convertSysPermissionTreeVoList(list);
        return treeVos;
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception {

        // 根据角色id获取该对应的所有三级权限ID

        return null;
    }


    @Override
    public List<SysPermissionTreeVo> getNavMenuTree() throws Exception {
        List<Integer> levels = Arrays.asList(MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode());
        Wrapper wrapper = lambdaQuery()
                .in(SysPermission::getLevel, levels)
                .eq(SysPermission::getState, StateEnum.ENABLE.getCode())
                .getWrapper();


        List<SysPermission> list = sysPermissionMapper.selectList(wrapper);

        return convertSysPermissionTreeVoList(list);

    }
}
