package io.geekidea.boot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.demo.dto.DataRangeAppTestDto;
import io.geekidea.boot.demo.entity.DataRangeAppTest;
import io.geekidea.boot.demo.query.DataRangeAppTestQuery;
import io.geekidea.boot.demo.vo.DataRangeAppTestVo;
import io.geekidea.boot.demo.query.DataRangeAppTestAppQuery;
import io.geekidea.boot.demo.vo.DataRangeAppTestAppVo;


/**
 * 用户端数据权限测试 服务接口
 *
 * @author geekidea
 * @since 2023-12-02
 */
public interface DataRangeAppTestService extends IService<DataRangeAppTest> {

    /**
     * 添加用户端数据权限测试
     *
     * @param dataRangeAppTestDto
     * @return
     * @throws Exception
     */
    boolean addDataRangeAppTest(DataRangeAppTestDto dataRangeAppTestDto) throws Exception;

    /**
     * 修改用户端数据权限测试
     *
     * @param dataRangeAppTestDto
     * @return
     * @throws Exception
     */
    boolean updateDataRangeAppTest(DataRangeAppTestDto dataRangeAppTestDto) throws Exception;

    /**
     * 删除用户端数据权限测试
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteDataRangeAppTest(Long id) throws Exception;

    /**
     * 用户端数据权限测试详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DataRangeAppTestVo getDataRangeAppTestById(Long id) throws Exception;

    /**
     * 用户端数据权限测试分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DataRangeAppTestVo> getDataRangeAppTestPage(DataRangeAppTestQuery query) throws Exception;

    /**
     * App用户端数据权限测试详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    DataRangeAppTestAppVo getAppDataRangeAppTestById(Long id) throws Exception;

    /**
     * App用户端数据权限测试分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<DataRangeAppTestAppVo> getAppDataRangeAppTestPage(DataRangeAppTestAppQuery query) throws Exception;

}
