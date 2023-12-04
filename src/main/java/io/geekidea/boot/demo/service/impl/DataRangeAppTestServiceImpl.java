package io.geekidea.boot.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.util.PagingUtil;
import io.geekidea.boot.demo.dto.DataRangeAppTestDto;
import io.geekidea.boot.demo.entity.DataRangeAppTest;
import io.geekidea.boot.demo.mapper.DataRangeAppTestMapper;
import io.geekidea.boot.demo.query.DataRangeAppTestQuery;
import io.geekidea.boot.demo.service.DataRangeAppTestService;
import io.geekidea.boot.demo.vo.DataRangeAppTestVo;
import io.geekidea.boot.demo.query.DataRangeAppTestAppQuery;
import io.geekidea.boot.demo.vo.DataRangeAppTestAppVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户端数据权限测试 服务实现类
 *
 * @author geekidea
 * @since 2023-12-02
 */
@Slf4j
@Service
public class DataRangeAppTestServiceImpl extends ServiceImpl<DataRangeAppTestMapper, DataRangeAppTest> implements DataRangeAppTestService {

    @Autowired
    private DataRangeAppTestMapper dataRangeAppTestMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addDataRangeAppTest(DataRangeAppTestDto dataRangeAppTestDto) throws Exception {
        DataRangeAppTest dataRangeAppTest = new DataRangeAppTest();
        BeanUtils.copyProperties(dataRangeAppTestDto, dataRangeAppTest);
        return save(dataRangeAppTest);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDataRangeAppTest(DataRangeAppTestDto dataRangeAppTestDto) throws Exception {
        Long id = dataRangeAppTestDto.getId();
        DataRangeAppTest dataRangeAppTest = getById(id);
        if (dataRangeAppTest == null) {
            throw new BusinessException("用户端数据权限测试不存在");
        }
        BeanUtils.copyProperties(dataRangeAppTestDto, dataRangeAppTest);
        dataRangeAppTest.setUpdateTime(new Date());
        return updateById(dataRangeAppTest);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteDataRangeAppTest(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public DataRangeAppTestVo getDataRangeAppTestById(Long id) throws Exception {
        return dataRangeAppTestMapper.getDataRangeAppTestById(id);
    }

    @Override
    public Paging<DataRangeAppTestVo> getDataRangeAppTestPage(DataRangeAppTestQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DataRangeAppTestVo> list = dataRangeAppTestMapper.getDataRangeAppTestPage(query);
        Paging<DataRangeAppTestVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public DataRangeAppTestAppVo getAppDataRangeAppTestById(Long id) throws Exception {
        return dataRangeAppTestMapper.getAppDataRangeAppTestById(id);
    }

    @Override
    public Paging<DataRangeAppTestAppVo> getAppDataRangeAppTestPage(DataRangeAppTestAppQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<DataRangeAppTestAppVo> list = dataRangeAppTestMapper.getAppDataRangeAppTestPage(query);
        Paging<DataRangeAppTestAppVo> paging = new Paging<>(list);
        return paging;
    }

}
