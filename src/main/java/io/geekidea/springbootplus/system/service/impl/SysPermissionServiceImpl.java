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
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import io.geekidea.springbootplus.enums.LevelEnum;
import io.geekidea.springbootplus.enums.StateEnum;
import io.geekidea.springbootplus.system.convert.SysPermissionConvert;
import io.geekidea.springbootplus.system.entity.SysPermission;
import io.geekidea.springbootplus.system.mapper.SysPermissionMapper;
import io.geekidea.springbootplus.system.param.SysPermissionQueryParam;
import io.geekidea.springbootplus.system.service.SysPermissionService;
import io.geekidea.springbootplus.system.vo.SysPermissionQueryVo;
import io.geekidea.springbootplus.system.vo.SysPermissionTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysPermission(SysPermission sysPermission) throws Exception {
        return super.save(sysPermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysPermission(SysPermission sysPermission) throws Exception {
        return super.updateById(sysPermission);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysPermission(Long id) throws Exception {
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
        return sysPermissionMapper.selectCount(queryWrapper) == permissionIds.size();
    }

    @Override
    public List<SysPermission> getAllMenuList() throws Exception {
        SysPermission sysPermission = new SysPermission().setState(StateEnum.ENABLE.ordinal());
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
        // 按level分组获取map
        Map<Integer, List<SysPermission>> map = list.stream().collect(Collectors.groupingBy(SysPermission::getLevel));
        List<SysPermissionTreeVo> treeVos = new ArrayList<>();
        for (SysPermission one : map.get(LevelEnum.ONE.getKey())) {
            SysPermissionTreeVo oneVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(one);
            Long oneParentId = oneVo.getParentId();
            if (oneParentId == null || oneParentId == 0) {
                treeVos.add(oneVo);
            }
            for (SysPermission two : map.get(LevelEnum.TWO.getKey())) {
                SysPermissionTreeVo twoVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(two);
                if (two.getParentId().equals(one.getId())) {
                    oneVo.getChildren().add(twoVo);
                }
                for (SysPermission three : map.get(LevelEnum.THREE.getKey())) {
                    if (three.getParentId().equals(two.getId())) {
                        SysPermissionTreeVo threeVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(three);
                        twoVo.getChildren().add(threeVo);
                    }
                }
            }
        }
        return treeVos;
    }

}
