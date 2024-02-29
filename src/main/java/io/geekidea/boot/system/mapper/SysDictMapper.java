package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysDict;
import io.geekidea.boot.system.query.SysDictAppQuery;
import io.geekidea.boot.system.query.SysDictQuery;
import io.geekidea.boot.system.vo.AppSysDictVo;
import io.geekidea.boot.system.vo.SysDictVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数据 Mapper 接口
 *
 * @author geekidea
 * @since 2023-11-25
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

    /**
     * 字典数据详情
     *
     * @param id
     * @return
     */
    SysDictVo getSysDictById(Long id);

    /**
     * 字典数据分页列表
     *
     * @param query
     * @return
     */
    List<SysDictVo> getSysDictPage(SysDictQuery query);

    /**
     * App字典数据列表
     *
     * @param query
     * @return
     */
    List<AppSysDictVo> getAppSysDictList(SysDictAppQuery query);

}
