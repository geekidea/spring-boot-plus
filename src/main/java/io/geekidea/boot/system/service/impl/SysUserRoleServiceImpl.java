package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.geekidea.boot.framework.service.impl.BaseServiceImpl;
import io.geekidea.boot.system.entity.SysUserRole;
import io.geekidea.boot.system.mapper.SysUserRoleMapper;
import io.geekidea.boot.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色关系表 服务实现类
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public boolean batchSaveSysUserRole(Long userId, List<Long> roleIds) throws Exception {
        // 保存用户角色关系
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            sysUserRoles.add(sysUserRole);
        }
        return saveBatch(sysUserRoles);
    }

    @Override
    public boolean deleteSysUserRoleByUserId(Long userId) throws Exception {
        LambdaUpdateWrapper<SysUserRole> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SysUserRole::getUserId, userId);
        return remove(lambdaUpdateWrapper);
    }

}
