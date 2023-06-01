package io.geekidea.boot.system.service.impl;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.dto.SysRoleMenuAddDto;
import io.geekidea.boot.system.dto.SysRoleMenuUpdateDto;
import io.geekidea.boot.system.entity.SysRoleMenu;
import io.geekidea.boot.system.mapper.SysRoleMenuMapper;
import io.geekidea.boot.system.query.SysRoleMenuQuery;
import io.geekidea.boot.system.service.SysRoleMenuService;
import io.geekidea.boot.system.vo.SysRoleMenuInfoVo;
import io.geekidea.boot.system.vo.SysRoleMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 角色菜单关系表 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysRoleMenu(SysRoleMenuAddDto sysRoleMenuAddDto) throws Exception {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        BeanUtils.copyProperties(sysRoleMenuAddDto, sysRoleMenu);
        return save(sysRoleMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysRoleMenu(SysRoleMenuUpdateDto sysRoleMenuUpdateDto) throws Exception {
        Long id = sysRoleMenuUpdateDto.getId();
        SysRoleMenu sysRoleMenu = getById(id);
        if (sysRoleMenu == null) {
            throw new BusinessException("角色菜单关系表不存在");
        }
        BeanUtils.copyProperties(sysRoleMenuUpdateDto, sysRoleMenu);
        sysRoleMenu.setUpdateTime(new Date());
        return updateById(sysRoleMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysRoleMenu(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysRoleMenuInfoVo getSysRoleMenuById(Long id) throws Exception {
        return sysRoleMenuMapper.getSysRoleMenuById(id);
    }

    @Override
    public Paging<SysRoleMenuVo> getSysRoleMenuList(SysRoleMenuQuery sysRoleMenuQuery) throws Exception {
        handlePage(sysRoleMenuQuery, OrderByItem.desc("id"));
        List<SysRoleMenuVo> list = sysRoleMenuMapper.getSysRoleMenuList(sysRoleMenuQuery);
        Paging<SysRoleMenuVo> paging = new Paging<>(list);
        return paging;
    }

}
