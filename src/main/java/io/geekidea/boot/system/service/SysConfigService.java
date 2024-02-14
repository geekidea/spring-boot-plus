package io.geekidea.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.SysConfigDto;
import io.geekidea.boot.system.entity.SysConfig;
import io.geekidea.boot.system.query.SysConfigQuery;
import io.geekidea.boot.system.vo.SysConfigVo;


/**
 * 系统配置 服务接口
 *
 * @author geekidea
 * @since 2023-11-27
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 添加系统配置
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean addSysConfig(SysConfigDto dto);

    /**
     * 修改系统配置
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateSysConfig(SysConfigDto dto);

    /**
     * 删除系统配置
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysConfig(Long id);

    /**
     * 系统配置详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysConfigVo getSysConfigById(Long id);

    /**
     * 系统配置分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysConfigVo> getSysConfigPage(SysConfigQuery query);

    /**
     * 检查configKey是否存在
     *
     * @param configKey
     * @return
     * @throws Exception
     */
    void checkConfigKeyExists(String configKey);

}
