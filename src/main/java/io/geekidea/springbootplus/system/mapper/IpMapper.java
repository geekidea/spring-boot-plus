package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.web.vo.IpVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author geekidea
 * @since 2019-06-20
 */
@Repository
public interface IpMapper extends BaseMapper<Ip> {

    /**
     * 根据ID获取查询对象
     * @param ip
     * @return
     */
    IpVo getByIp(String ip);


}
