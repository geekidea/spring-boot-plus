package io.geekidea.springbootplus.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.springbootplus.system.entity.Ip;
import io.geekidea.springbootplus.system.web.param.IpQueryParam;
import io.geekidea.springbootplus.system.web.vo.IpQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author geekidea
 * @since 2019-07-27
 */
@Repository
public interface IpMapper extends BaseMapper<Ip> {

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    IpQueryVo getIpById(Serializable id);

    /**
     * 获取分页对象
     * @param page
     * @param ipQueryParam
     * @return
     */
    IPage<IpQueryVo> getIpPageList(@Param("page") Page page, @Param("param") IpQueryParam ipQueryParam);

}
