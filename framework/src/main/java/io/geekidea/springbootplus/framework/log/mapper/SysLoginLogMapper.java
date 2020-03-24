package io.geekidea.springbootplus.framework.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.framework.log.entity.SysLoginLog;
import org.springframework.stereotype.Repository;

/**
 * 系统登录日志 Mapper 接口
 *
 * @author geekidea
 * @since 2020-03-24
 */
@Repository
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {


}
