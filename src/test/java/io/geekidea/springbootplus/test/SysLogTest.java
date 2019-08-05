package io.geekidea.springbootplus.test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springbootplus.common.web.vo.Paging;
import io.geekidea.springbootplus.system.entity.SysLog;
import io.geekidea.springbootplus.system.service.SysLogService;
import io.geekidea.springbootplus.system.web.param.SysLogQueryParam;
import io.geekidea.springbootplus.system.web.vo.SysLogQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 单元测试类
 * @author geekidea
 * @date 2019/8/4
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogTest {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 测试保存
     */
    @Test
    public void saveTest() {
        SysLog sysLog = new SysLog();
        sysLog.setContent("test add log");
        sysLog.setType(true);
        sysLog.setCreateId(1L);
        sysLog.setCreateTime(new Date());
        boolean result = sysLogService.save(sysLog);
        log.info("result：{}",result);
        log.info("logId：{}",sysLog.getLogId());
        Assert.assertTrue(result);
    }

    /**
     * 测试根据ID查询
     * @throws Exception
     */
    @Test
    public void getSysLogByIdTest() throws Exception {
        SysLogQueryVo sysLogQueryVo = sysLogService.getSysLogById(1060438746056376321L);
        log.info("sysLogQueryVo：{}",sysLogQueryVo);
        log.info("sysLogQueryVo JSON：{}", JSON.toJSONString(sysLogQueryVo,true));
    }

    /**
     * 测试分页查询
     * @throws Exception
     */
    @Test
    public void getSysLogPageListTest() throws Exception {
        SysLogQueryParam sysLogQueryParam = new SysLogQueryParam();
//        sysLogQueryParam.defaultOrder(OrderItem.desc("create_time"));
        Paging<SysLogQueryVo> paging = sysLogService.getSysLogPageList(sysLogQueryParam);
        log.info("paging：{}",paging);
        log.info("paging JSON：{}", JSON.toJSONString(paging,true));
    }

    /**
     * 测试修改
     */
    @Test
    public void updateTest() {
        SysLog sysLog = new SysLog();
        sysLog.setLogId(1060438746056376321L);
        sysLog.setContent("test update log");
        sysLog.setType(false);
        sysLog.setCreateId(6L);
        sysLog.setCreateTime(new Date());
        boolean result = sysLogService.updateById(sysLog);
        log.info("result：{}",result);
        Assert.assertTrue(result);
    }

    /**
     * 测试根据ID删除
     */
    @Test
    public void deleteTest() {
        boolean result = sysLogService.removeById(1060438746056376321L);
        log.info("result：{}",result);
        Assert.assertTrue(result);
    }

}
