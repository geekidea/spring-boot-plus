package io.geekidea.springbootplus.framework.system.service;

import io.geekidea.springbootplus.framework.system.entity.Ip;
import io.geekidea.springbootplus.framework.common.service.BaseService;
import io.geekidea.springbootplus.framework.core.pagination.Paging;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * IP地址 服务类
 *
 * @author geekidea
 * @since 2020-03-20
 */
public interface IpService extends BaseService<Ip> {

    /**
     * 通过ip地址获取IP对象
     *
     * @param ip
     * @return
     */
    Ip getByIp(@Param("ip") String ip);

}
