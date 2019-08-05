package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.mapper.IpMapper;
import io.geekidea.springbootplus.system.service.IpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * IP地址 服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2019-08-04
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class IpServiceImpl extends BaseServiceImpl<IpMapper, Ip> implements IpService {

    @Autowired
    private IpMapper ipMapper;


}
