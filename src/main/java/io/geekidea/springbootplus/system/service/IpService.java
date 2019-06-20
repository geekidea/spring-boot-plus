package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.web.vo.IpVo;
import io.geekidea.springbootplus.system.entity.Ip;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-06-20
 */
public interface IpService extends BaseService<Ip> {
    /**
     * 根据ID获取查询对象
     * @param ip
     * @return
     */
    IpVo getByIp(String ip);

}
