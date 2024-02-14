package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.OrderMapping;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.RoleMenusDto;
import io.geekidea.boot.system.dto.SysRoleDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.entity.SysRole;
import io.geekidea.boot.system.entity.SysRoleMenu;
import io.geekidea.boot.system.mapper.SysRoleMapper;
import io.geekidea.boot.system.mapper.SysUserMapper;
import io.geekidea.boot.system.query.SysRoleQuery;
import io.geekidea.boot.system.service.SysMenuService;
import io.geekidea.boot.system.service.SysRoleMenuService;
import io.geekidea.boot.system.service.SysRoleService;
import io.geekidea.boot.system.vo.SysRoleVo;
import io.geekidea.boot.util.PagingUtil;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysRole(SysRoleDto dto) {
        checkCodeExists(dto.getCode());
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(dto, sysRole);
        return save(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRole(SysRoleDto dto) {
        Long id = dto.getId();
        SysRole sysRole = getById(id);
        if (sysRole == null) {
            throw new BusinessException("系统角色不存在");
        }
        sysRole.setName(dto.getName());
        sysRole.setIsSystem(dto.getIsSystem());
        sysRole.setRemark(dto.getRemark());
        return updateById(sysRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRole(Long id) {
        // 判断角色下是否存在用户，如果存在，则不能删除
        Integer count = sysUserMapper.getCountByRoleId(id);
        if (count > 0) {
            throw new BusinessException("该角色下还存在用户，不可删除");
        }
        return removeById(id);
    }

    @Override
    public SysRoleVo getSysRoleById(Long id) {
        return sysRoleMapper.getSysRoleById(id);
    }

    @Override
    public Paging<SysRoleVo> getSysRolePage(SysRoleQuery query) {
        OrderMapping orderMapping = new OrderMapping();
        orderMapping.put("createTime", "create_time");
        PagingUtil.handlePage(query, orderMapping, OrderByItem.desc("id"));
        List<SysRoleVo> list = sysRoleMapper.getSysRolePage(query);
        Paging<SysRoleVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public List<SysRole> getSysRoleAllList() {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(SysRole::getId);
        return list(lambdaQueryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setRoleMenus(RoleMenusDto roleMenusDto) {
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

    @Override
    public void checkCodeExists(String code) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getCode, code);
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(code + "角色编码已经存在");
        }
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
