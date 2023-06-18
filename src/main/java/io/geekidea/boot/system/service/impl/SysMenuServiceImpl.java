package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.auth.util.LoginUtil;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysMenuAddDto;
import io.geekidea.boot.system.dto.SysMenuUpdateDto;
import io.geekidea.boot.system.entity.SysMenu;
import io.geekidea.boot.system.mapper.SysMenuMapper;
import io.geekidea.boot.system.service.SysMenuService;
import io.geekidea.boot.system.vo.SysMenuInfoVo;
import io.geekidea.boot.system.vo.SysMenuTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysMenu(SysMenuAddDto sysMenuAddDto) throws Exception {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuAddDto, sysMenu);
        return save(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysMenu(SysMenuUpdateDto sysMenuUpdateDto) throws Exception {
        Long id = sysMenuUpdateDto.getId();
        SysMenu sysMenu = getById(id);
        if (sysMenu == null) {
            throw new BusinessException("系统菜单不存在");
        }
        BeanUtils.copyProperties(sysMenuUpdateDto, sysMenu);
        sysMenu.setUpdateTime(new Date());
        return updateById(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysMenu(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysMenuInfoVo getSysMenuById(Long id) throws Exception {
        return sysMenuMapper.getSysMenuById(id);
    }

    @Override
    public List<SysMenuTreeVo> getSysMenuTreeList() throws Exception {
        List<SysMenuTreeVo> list = sysMenuMapper.getSysMenuTreeList();
        return recursionSysMenuTreeList(0L, list);
    }

    @Override
    public List<SysMenuTreeVo> getNavMenuTreeList() throws Exception {
        Long userId = LoginUtil.getUserId();
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        boolean isAdmin = LoginUtil.isAdmin();
        List<SysMenuTreeVo> list;
        if (isAdmin) {
            list = sysMenuMapper.getNavMenuTreeAllList();
        } else {
            list = sysMenuMapper.getNavMenuTreeList(userId);
        }
        return recursionSysMenuTreeList(0L, list);
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

}
