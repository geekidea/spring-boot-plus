package io.geekidea.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.system.entity.SysDept;
import io.geekidea.boot.system.query.SysDeptQuery;
import io.geekidea.boot.system.vo.SysDeptInfoVo;
import io.geekidea.boot.system.vo.SysDeptTreeVo;
import io.geekidea.boot.system.vo.SysDeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门 Mapper 接口
 *
 * @author geekidea
 * @since 2022-12-26
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 部门详情
     *
     * @param id
     * @return
     */
    SysDeptInfoVo getSysDeptById(Long id);

    /**
     * 部门分页列表
     *
     * @param sysDeptQuery
     * @return
     */
    List<SysDeptVo> getSysDeptList(SysDeptQuery sysDeptQuery);

    /**
     * 获取所有部门列表
     *
     * @return
     */
    List<SysDeptTreeVo> getSysDeptAllList();

}
