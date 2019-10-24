package io.geekidea.springbootplus.system.service.impl;

import io.geekidea.springbootplus.system.entity.SysDepartment;
import io.geekidea.springbootplus.system.mapper.SysDepartmentMapper;
import io.geekidea.springbootplus.system.service.SysDepartmentService;
import io.geekidea.springbootplus.system.param.SysDepartmentQueryParam;
import io.geekidea.springbootplus.system.vo.SysDepartmentQueryVo;
import io.geekidea.springbootplus.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.common.vo.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;


/**
 * <pre>
 * 部门 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public SysDepartmentQueryVo getSysDepartmentById(Serializable id) throws Exception {
        return sysDepartmentMapper.getSysDepartmentById(id);
    }

    @Override
    public Paging<SysDepartmentQueryVo> getSysDepartmentPageList(SysDepartmentQueryParam sysDepartmentQueryParam) throws Exception {
        Page page = setPageParam(sysDepartmentQueryParam, OrderItem.desc("create_time"));
        IPage<SysDepartmentQueryVo> iPage = sysDepartmentMapper.getSysDepartmentPageList(page, sysDepartmentQueryParam);
        return new Paging(iPage);
    }

}
