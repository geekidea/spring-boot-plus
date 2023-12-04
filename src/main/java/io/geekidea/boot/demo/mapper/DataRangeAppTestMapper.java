package io.geekidea.boot.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.auth.annotation.DataScope;
import io.geekidea.boot.demo.entity.DataRangeAppTest;
import io.geekidea.boot.demo.query.DataRangeAppTestAppQuery;
import io.geekidea.boot.demo.query.DataRangeAppTestQuery;
import io.geekidea.boot.demo.vo.DataRangeAppTestAppVo;
import io.geekidea.boot.demo.vo.DataRangeAppTestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户端数据权限测试 Mapper 接口
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Mapper
public interface DataRangeAppTestMapper extends BaseMapper<DataRangeAppTest> {

    /**
     * 用户端数据权限测试详情
     *
     * @param id
     * @return
     */
    DataRangeAppTestVo getDataRangeAppTestById(Long id);

    /**
     * 用户端数据权限测试分页列表
     *
     * @param query
     * @return
     */
    List<DataRangeAppTestVo> getDataRangeAppTestPage(DataRangeAppTestQuery query);

    /**
     * App用户端数据权限测试详情
     *
     * @param id
     * @return
     */
    DataRangeAppTestAppVo getAppDataRangeAppTestById(Long id);

    /**
     * App用户端数据权限测试分页列表
     *
     * @param query
     * @return
     */
//    @DataScope(userAlias = "d",userIdColumn = "userId")
    @DataScope
    List<DataRangeAppTestAppVo> getAppDataRangeAppTestPage(DataRangeAppTestAppQuery query);

}
