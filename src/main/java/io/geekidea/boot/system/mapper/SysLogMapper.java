package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysLog;
import io.geekidea.boot.system.query.SysLogQuery;
import io.geekidea.boot.system.vo.SysLogInfoVo;
import io.geekidea.boot.system.vo.SysLogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统日志 Mapper 接口
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 系统日志详情
     *
     * @param id
     * @return
     */
    SysLogInfoVo getSysLogById(Long id);

    /**
     * 系统日志分页列表
     *
     * @param sysLogQuery
     * @return
     */
    List<SysLogVo> getSysLogList(SysLogQuery sysLogQuery);

}
