package io.geekidea.springbootplus.system.service;

import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.common.service.BaseService;
import io.geekidea.springbootplus.system.param.IpQueryParam;
import io.geekidea.springbootplus.system.vo.IpQueryVo;
import io.geekidea.springbootplus.common.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * IP地址 服务类
 * </p>
 *
 * @author geekidea
 * @since 2019-10-11
 */
public interface IpService extends BaseService<Ip> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    IpQueryVo getIpById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param ipQueryParam
     * @return
     */
    Paging<IpQueryVo> getIpPageList(IpQueryParam ipQueryParam) throws Exception;

}
