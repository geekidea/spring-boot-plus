package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.common.constant.SystemConstant;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.system.dto.SysMenuDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.enums.SysMenuType;
import io.geekidea.boot.system.mapper.SysMenuMapper;
import io.geekidea.boot.system.query.SysMenuQuery;
import io.geekidea.boot.system.service.SysMenuService;
import io.geekidea.boot.system.service.SysRoleMenuService;
import io.geekidea.boot.system.vo.SysMenuTreeVo;
import io.geekidea.boot.system.vo.SysMenuVo;
import io.geekidea.boot.system.vo.SysNavMenuTreeVo;
import io.geekidea.boot.util.ObjectValueUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysMenu(SysMenuDto dto) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(dto, sysMenu);
        Long parentId = dto.getParentId();
        if (parentId == null) {
            sysMenu.setParentId(SystemConstant.ROOT_MENU_ID);
        }
        return save(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysMenu(SysMenuDto dto) {
        Long id = dto.getId();
        SysMenu sysMenu = getById(id);
        if (sysMenu == null) {
            throw new BusinessException("系统菜单不存在");
        }
        BeanUtils.copyProperties(dto, sysMenu);
        return updateById(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysMenu(Long id) {
        // 获取菜单
        SysMenu sysMenu = getById(id);
        if (sysMenu == null) {
            throw new BusinessException("菜单数据不存在");
        }
        // 所有关联菜单ID集合
        List<Long> deleteMenuIds = new ArrayList<>();
        deleteMenuIds.add(id);
        Integer type = sysMenu.getType();
        SysMenuType sysMenuType = SysMenuType.get(type);
        if (SysMenuType.DIR == sysMenuType) {
            // 如果是目录，则先查询所有的子菜单ID，再根据子菜单ID查询所有的权限菜单ID
            List<Long> menuIds = sysMenuMapper.getChildrenMenuIds(Arrays.asList(id));
            deleteMenuIds.addAll(menuIds);
            if (CollectionUtils.isNotEmpty(menuIds)) {
                List<Long> permissionMenuIds = sysMenuMapper.getChildrenMenuIds(menuIds);
                deleteMenuIds.addAll(permissionMenuIds);
            }
        } else if (SysMenuType.MENU == sysMenuType) {
            // 如果是菜单，则查询所有的权限子菜单
            List<Long> menuIds = sysMenuMapper.getChildrenMenuIds(Arrays.asList(id));
            if (CollectionUtils.isNotEmpty(menuIds)) {
                deleteMenuIds.addAll(menuIds);
            }
        }
        // 删除角色菜单关系表中的相关菜单关联
        sysRoleMenuService.deleteSysRoleMenuByMenuIds(deleteMenuIds);
        // 删除所有关联菜单
        return removeByIds(deleteMenuIds);
    }

    @Override
    public SysMenuVo getSysMenuById(Long id) {
        return sysMenuMapper.getSysMenuById(id);
    }

    @Override
    public List<SysMenuTreeVo> getAllSysMenuTreeList(SysMenuQuery query) {
        List<SysMenuTreeVo> list = sysMenuMapper.getSysMenuTreeList(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 如果搜索条件有值，则直接返回普通列表
        boolean flag = ObjectValueUtil.isHaveValue(query);
        if (flag) {
            return list;
        }
        // 递归返回树形列表
        return recursionSysMenuTreeList(0L, list);
    }

    @Override
    public List<SysMenuTreeVo> getSysMenuTreeList() {
        SysMenuQuery query = new SysMenuQuery();
        query.setStatus(true);
        List<SysMenuTreeVo> list = sysMenuMapper.getSysMenuTreeList(query);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 递归返回树形列表
        return recursionSysMenuTreeList(0L, list);
    }

    @Override
    public List<SysNavMenuTreeVo> getNavMenuTreeList() {
        Long userId = LoginUtil.getUserId();
        // 如果是管理员，则查询所有可用菜单，否则获取当前用户所有可用的菜单
        boolean isAdmin = LoginUtil.isAdmin();
        List<SysNavMenuTreeVo> list;
        if (isAdmin) {
            list = sysMenuMapper.getNavMenuTreeAllList();
        } else {
            list = sysMenuMapper.getNavMenuTreeList(userId);
        }
        // 递归返回树形列表
        return recursionSysNavMenuTreeList(0L, list);
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        return sysMenuMapper.getMenuIdsByRoleId(roleId);
    }

    /**
     * 递归设置树形菜单
     *
     * @param parentId
     * @param list
     * @return
     */
    private List<SysMenuTreeVo> recursionSysMenuTreeList(Long parentId, List<SysMenuTreeVo> list) {
        return list.stream()
                .filter(vo -> vo.getParentId().equals(parentId))
                .map(item -> {
                    item.setChildren(recursionSysMenuTreeList(item.getId(), list));
                    return item;
                })
                .collect(Collectors.toList());
    }

    /**
     * 递归设置树形导航菜单
     *
     * @param parentId
     * @param list
     * @return
     */
    private List<SysNavMenuTreeVo> recursionSysNavMenuTreeList(Long parentId, List<SysNavMenuTreeVo> list) {
        return list.stream()
                .filter(vo -> vo.getParentId().equals(parentId))
                .map(item -> {
                    item.setChildren(recursionSysNavMenuTreeList(item.getId(), list));
                    return item;
                })
                .collect(Collectors.toList());
    }

}
