package io.geekidea.springbootplus.framework.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.framework.system.entity.Ip;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * IP地址 Mapper 接口
 *
 * @author geekidea
 * @since 2020-03-20
 */
@Repository
public interface IpMapper extends BaseMapper<Ip> {

    /**
     * 通过ip地址获取IP对象
     *
     * @param ip
     * @return
     */
    Ip getByIp(@Param("ip") String ip);

}
