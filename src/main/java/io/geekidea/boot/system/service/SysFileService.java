package io.geekidea.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.SysFileDto;
import io.geekidea.boot.system.entity.SysFile;
import io.geekidea.boot.system.query.SysFileQuery;
import io.geekidea.boot.system.vo.SysFileVo;

import java.util.List;


/**
 * 系统文件 服务接口
 *
 * @author geekidea
 * @since 2023-11-26
 */
public interface SysFileService extends IService<SysFile> {

    /**
     * 修改系统文件
     *
     * @param sysFileDto
     * @return
     * @throws Exception
     */
    boolean updateSysFile(SysFileDto sysFileDto) throws Exception;

    /**
     * 删除系统文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysFile(Long id) throws Exception;

    /**
     * 系统文件详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysFileVo getSysFileById(Long id) throws Exception;

    /**
     * 系统文件分页列表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<SysFileVo> getSysFilePage(SysFileQuery query) throws Exception;

    /**
     * 获取系统文件目录列表
     *
     * @return
     * @throws Exception
     */
    List<String> getSysFileDirList() throws Exception;

}
