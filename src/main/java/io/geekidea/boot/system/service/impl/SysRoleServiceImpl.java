package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.RoleMenusDto;
import io.geekidea.boot.system.dto.SysRoleAddDto;
import io.geekidea.boot.system.dto.SysRoleUpdateDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.entity.SysRoleMenu;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.service.SysMenuService;
import io.geekidea.boot.system.service.SysRoleMenuService;
import io.geekidea.boot.system.service.SysRoleService;
import io.geekidea.boot.system.vo.SysRoleInfoVo;
import io.geekidea.boot.system.vo.SysRoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统角色 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysMenuService sysMenuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysRole(SysRoleAddDto sysRoleAddDto) throws Exception {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleAddDto, sysRole);
        return save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRoleUpdateDto sysRoleUpdateDto) throws Exception {
        Long id = sysRoleUpdateDto.getId();
        SysRole sysRole = getById(id);
        if (sysRole == null) {
            throw new BusinessException("系统角色不存在");
        }
        BeanUtils.copyProperties(sysRoleUpdateDto, sysRole);
        sysRole.setUpdateTime(new Date());
        return updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysRoleInfoVo getSysRoleById(Long id) throws Exception {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRoleVo> getSysRoleList(SysRoleQuery sysRoleQuery) throws Exception {
        handlePage(sysRoleQuery, OrderByItem.desc("id"));
        List<SysRoleVo> list = sysRoleMapper.getSysRoleList(sysRoleQuery);
        Paging<SysRoleVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public List<SysRole> getSysRoleAllList() throws Exception {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRole::getStatus, true);
        lambdaQueryWrapper.orderByAsc(SysRole::getId);
        return list(lambdaQueryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setRoleMenus(RoleMenusDto roleMenusDto) throws Exception {
        Long roleId = roleMenusDto.getRoleId();
        List<Long> menuIds = roleMenusDto.getMenuIds();
        SysRole sysRole = getById(roleId);
        if (sysRole == null) {
            throw new BusinessException("角色不存在");
        }
        // 先删除角色权限关系
        sysRoleMenuService.deleteSysRoleMenuByRoleId(roleId);
        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
        // 保存角色权限关系
        for (Long menuId : menuIds) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setChoice(true);
            sysRoleMenus.add(sysRoleMenu);
        }
        // 补全上级菜单父ID
        Set<Long> completionMenuIds = completionMenuParentIds(menuIds);
        if (CollectionUtils.isNotEmpty(completionMenuIds)) {
            for (Long completionMenuId : completionMenuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(completionMenuId);
                sysRoleMenu.setChoice(false);
                sysRoleMenus.add(sysRoleMenu);
            }
        }
        boolean flag = sysRoleMenuService.saveBatch(sysRoleMenus);
        if (!flag) {
            throw new BusinessException("设置角色权限异常");
        }
        return true;
    }

    /**
     * 补全菜单父ID
     *
     * @param choiceMenuIds
     * @return
     */
    private Set<Long> completionMenuParentIds(List<Long> choiceMenuIds) {
        // 获取所有菜单
        List<SysMenu> menus = sysMenuService.list();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        Map<Long, SysMenu> menuMap = menus.stream().collect(Collectors.toMap(SysMenu::getId, SysMenu -> SysMenu));
        // 获取选择的菜单对象集合
        List<SysMenu> choiceMenus = new ArrayList<>();
        for (Long choiceMenuId : choiceMenuIds) {
            SysMenu sysMenu = menuMap.get(choiceMenuId);
            if (sysMenu != null) {
                choiceMenus.add(sysMenu);
            }
        }
        // 递归获取上级ID直到parentId为0截止
        if (CollectionUtils.isEmpty(choiceMenus)) {
            return null;
        }
        Set<Long> menuIdSet = new HashSet<>();
        for (SysMenu choiceMenu : choiceMenus) {
            recursionCompletionMenu(choiceMenuIds, menuIdSet, menuMap, choiceMenu);
        }
        return menuIdSet;
    }

    /**
     * 递归补全菜单ID集合
     *
     * @param choiceMenuIds
     * @param menuIdSet
     * @param menuMap
     * @param choiceMenu
     */
    private void recursionCompletionMenu(List<Long> choiceMenuIds, Set<Long> menuIdSet, Map<Long, SysMenu> menuMap, SysMenu choiceMenu) {
        Long parentId = choiceMenu.getParentId();
        // 判断是否存在此父ID，不存在则添加
        if (!choiceMenuIds.contains(parentId) && !menuIdSet.contains(parentId)) {
            if (parentId != 0) {
                menuIdSet.add(parentId);
                SysMenu parentMenu = menuMap.get(parentId);
                recursionCompletionMenu(choiceMenuIds, menuIdSet, menuMap, parentMenu);
            }
        }
    }

}
