package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysLoginLog;
import io.geekidea.boot.system.query.SysLoginLogQuery;
import io.geekidea.boot.system.vo.SysLoginLogInfoVo;
import io.geekidea.boot.system.vo.SysLoginLogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统登录日志 Mapper 接口
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    /**
     * 系统登录日志详情
     *
     * @param id
     * @return
     */
    SysLoginLogInfoVo getSysLoginLogById(Long id);

    /**
     * 系统登录日志分页列表
     *
     * @param sysLoginLogQuery
     * @return
     */
    List<SysLoginLogVo> getSysLoginLogList(SysLoginLogQuery sysLoginLogQuery);

}
