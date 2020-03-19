package io.geekidea.springbootplus.framework.system.convert;

import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserRedisVo;
import io.geekidea.springbootplus.framework.shiro.vo.LoginSysUserVo;
import io.geekidea.springbootplus.framework.system.entity.SysUser;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-19T20:07:51+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class SysUserConvertImpl implements SysUserConvert {

    @Override
    public LoginSysUserVo sysUserToLoginSysUserVo(SysUser sysUser) {
        if ( sysUser == null ) {
            return null;
        }

        LoginSysUserVo loginSysUserVo = new LoginSysUserVo();

        loginSysUserVo.setId( sysUser.getId() );
        loginSysUserVo.setUsername( sysUser.getUsername() );
        loginSysUserVo.setNickname( sysUser.getNickname() );
        loginSysUserVo.setGender( sysUser.getGender() );
        loginSysUserVo.setState( sysUser.getState() );
        loginSysUserVo.setDepartmentId( sysUser.getDepartmentId() );
        loginSysUserVo.setRoleId( sysUser.getRoleId() );

        return loginSysUserVo;
    }

    @Override
    public LoginSysUserRedisVo loginSysUserVoToLoginSysUserRedisVo(LoginSysUserVo loginSysUserVo) {
        if ( loginSysUserVo == null ) {
            return null;
        }

        LoginSysUserRedisVo loginSysUserRedisVo = new LoginSysUserRedisVo();

        loginSysUserRedisVo.setId( loginSysUserVo.getId() );
        loginSysUserRedisVo.setUsername( loginSysUserVo.getUsername() );
        loginSysUserRedisVo.setNickname( loginSysUserVo.getNickname() );
        loginSysUserRedisVo.setGender( loginSysUserVo.getGender() );
        loginSysUserRedisVo.setState( loginSysUserVo.getState() );
        loginSysUserRedisVo.setDepartmentId( loginSysUserVo.getDepartmentId() );
        loginSysUserRedisVo.setDepartmentName( loginSysUserVo.getDepartmentName() );
        loginSysUserRedisVo.setRoleId( loginSysUserVo.getRoleId() );
        loginSysUserRedisVo.setRoleName( loginSysUserVo.getRoleName() );
        loginSysUserRedisVo.setRoleCode( loginSysUserVo.getRoleCode() );
        Set<String> set = loginSysUserVo.getPermissionCodes();
        if ( set != null ) {
            loginSysUserRedisVo.setPermissionCodes( new HashSet<String>( set ) );
        }

        return loginSysUserRedisVo;
    }
}
