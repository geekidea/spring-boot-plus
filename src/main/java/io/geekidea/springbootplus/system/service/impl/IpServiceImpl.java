package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.mapper.IpMapper;
import io.geekidea.springbootplus.system.service.IpService;
import io.geekidea.springbootplus.system.web.param.IpQueryParam;
import io.geekidea.springbootplus.system.web.vo.IpQueryVo;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.web.vo.Paging;
import io.geekidea.springbootplus.common.enums.OrderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author geekidea
 * @since 2019-07-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class IpServiceImpl extends BaseServiceImpl<IpMapper, Ip> implements IpService {

    @Autowired
    private IpMapper ipMapper;

    @Override
    public IpQueryVo getIpById(Serializable id) throws Exception{
        return ipMapper.getIpById(id);
    }

    @Override
    public Paging<IpQueryVo> getIpPageList(IpQueryParam ipQueryParam) throws Exception{
        Page page = setPageParam(ipQueryParam,"create_time", OrderEnum.DESC);
        IPage<IpQueryVo> iPage = ipMapper.getIpPageList(page,ipQueryParam);
        return new Paging(iPage);
    }

}
