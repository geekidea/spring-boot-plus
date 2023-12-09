package io.geekidea.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.OrderByItem;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.system.dto.SysFileDto;
import io.geekidea.boot.system.entity.SysFile;
import io.geekidea.boot.system.mapper.SysFileMapper;
import io.geekidea.boot.system.query.SysFileQuery;
import io.geekidea.boot.system.service.SysFileService;
import io.geekidea.boot.system.vo.SysFileVo;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统文件 服务实现类
 *
 * @author geekidea
 * @since 2023-11-26
 */
@Slf4j
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysFile(SysFileDto dto) throws Exception {
        Long id = dto.getId();
        SysFile sysFile = getById(id);
        if (sysFile == null) {
            throw new BusinessException("系统文件不存在");
        }
        BeanUtils.copyProperties(dto, sysFile);
        sysFile.setUpdateTime(new Date());
        return updateById(sysFile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysFile(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public SysFileVo getSysFileById(Long id) throws Exception {
        return sysFileMapper.getSysFileById(id);
    }

    @Override
    public Paging<SysFileVo> getSysFilePage(SysFileQuery query) throws Exception {
        PagingUtil.handlePage(query, OrderByItem.desc("id"));
        List<SysFileVo> list = sysFileMapper.getSysFilePage(query);
        Paging<SysFileVo> paging = new Paging<>(list);
        return paging;
    }

    @Override
    public List<String> getSysFileDirList() throws Exception {
        LambdaQueryWrapper<SysFile> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysFile::getDirName);
        wrapper.groupBy(SysFile::getDirName);
        wrapper.orderByDesc(SysFile::getDirName);
        List<SysFile> list = list(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().map(SysFile::getDirName).collect(Collectors.toList());
    }

}
