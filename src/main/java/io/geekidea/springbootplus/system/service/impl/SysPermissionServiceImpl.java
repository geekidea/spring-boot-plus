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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.geekidea.springbootplus.common.exception.BusinessException;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.enums.MenuLevelEnum;
import io.geekidea.springbootplus.enums.StateEnum;
import io.geekidea.springbootplus.system.convert.SysPermissionConvert;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.mapper.SysPermissionMapper;
import io.geekidea.springbootplus.system.param.SysPermissionQueryParam;
import io.geekidea.springbootplus.system.service.SysPermissionService;
import io.geekidea.springbootplus.system.service.SysRolePermissionService;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.system.vo.SysPermissionTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        SysPermission updateSysPermission = getById(sysPermission.getId());
        if (updateSysPermission == null) {
            throw new BusinessException("权限不存在");
        }

        // 指定需改的字段
        updateSysPermission.setParentId(sysPermission.getParentId())
                .setLevel(sysPermission.getLevel())
                .setName(sysPermission.getName())
                .setState(sysPermission.getState())
                .setCode(sysPermission.getCode())
                .setIcon(sysPermission.getIcon())
                .setRemark(sysPermission.getRemark())
                .setSort(sysPermission.getSort())
                .setType(sysPermission.getType())
                .setUrl(sysPermission.getUrl())
                .setUpdateTime(new Date());
        return super.updateById(updateSysPermission);
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
    public Paging<SysPermissionQueryVo> getSysPermissionPageList(SysPermissionQueryParam sysPermissionQueryParam) throws Exception {
        Page page = setPageParam(sysPermissionQueryParam, OrderItem.desc("create_time"));
        IPage<SysPermissionQueryVo> iPage = sysPermissionMapper.getSysPermissionPageList(page, sysPermissionQueryParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isExistsByPermissionIds(List<Long> permissionIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id", permissionIds);
        return sysPermissionMapper.selectCount(queryWrapper).intValue() == permissionIds.size();
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
                        oneVo.getChildren().add(twoVo);
                    }
                    List<SysPermission> threeList = map.get(MenuLevelEnum.THREE.getCode());
                    if (CollectionUtils.isNotEmpty(threeList)) {
                        for (SysPermission three : threeList) {
                            if (three.getParentId().equals(two.getId())) {
                                SysPermissionTreeVo threeVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(three);
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
}
