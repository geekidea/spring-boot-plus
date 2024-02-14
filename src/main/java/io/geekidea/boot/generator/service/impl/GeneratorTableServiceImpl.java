package io.geekidea.boot.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.config.properties.GeneratorProperties;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.generator.dto.GeneratorColumnDto;
import io.geekidea.boot.generator.dto.GeneratorTableDto;
import io.geekidea.boot.generator.entity.GeneratorColumn;
import io.geekidea.boot.generator.entity.GeneratorTable;
import io.geekidea.boot.generator.mapper.GeneratorTableMapper;
import io.geekidea.boot.generator.query.GeneratorTableQuery;
import io.geekidea.boot.generator.service.GeneratorColumnService;
import io.geekidea.boot.generator.service.GeneratorTableService;
import io.geekidea.boot.generator.util.GeneratorUtil;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;
import io.geekidea.boot.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 生成代码表 服务实现类
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Slf4j
@Service
public class GeneratorTableServiceImpl extends ServiceImpl<GeneratorTableMapper, GeneratorTable> implements GeneratorTableService {

    @Autowired
    private GeneratorTableMapper generatorTableMapper;

    @Autowired
    private GeneratorColumnService generatorColumnService;

    @Autowired
    private GeneratorProperties generatorProperties;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addGeneratorTable(String tableName) {
        GeneratorTableDbVo generatorTableDbVo = generatorTableMapper.getDbTableByTableName(tableName);
        if (generatorTableDbVo == null) {
            throw new BusinessException("表不存在或不允许操作");
        }
        GeneratorTable generatorTable = new GeneratorTable();
        // 设置表基础信息
        GeneratorUtil.setGeneratorTable(generatorProperties, tableName, generatorTableDbVo, generatorTable);
        return save(generatorTable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateGeneratorTable(GeneratorTableDto dto) {
        Long id = dto.getId();
        GeneratorTable generatorTable = getById(id);
        if (generatorTable == null) {
            throw new BusinessException("生成代码表不存在");
        }
        BeanUtils.copyProperties(dto, generatorTable);
        boolean flag = updateById(generatorTable);
        // 修改列
        List<GeneratorColumnDto> columns = dto.getColumns();
        if (CollectionUtils.isNotEmpty(columns)) {
            for (GeneratorColumnDto column : columns) {
                generatorColumnService.updateGeneratorColumn(column);
            }
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GeneratorTable getGeneratorTableInfo(String tableName) {
        GeneratorTable table = generatorTableMapper.getGeneratorTableByTableName(tableName);
        List<GeneratorColumn> columns;
        if (table == null) {
            // 添加生成的表
            boolean addTableResult = addGeneratorTable(tableName);
            if (!addTableResult) {
                throw new BusinessException("添加生成表数据异常");
            }
            table = generatorTableMapper.getGeneratorTableByTableName(tableName);
            Boolean validateField = table.getValidateField();
            // 添加生成的列
            generatorColumnService.addGeneratorColumn(tableName, validateField);
        }
        // 读取数据库表的列信息
        columns = generatorColumnService.getGeneratorColumnList(tableName);
        // 设置表的列信息
        GeneratorUtil.setTableColumnInfo(table, columns);
        return table;
    }

    @Override
    public Paging<GeneratorTableDbVo> getDbTablePage(GeneratorTableQuery query) {
        PagingUtil.handlePage(query);
        List<GeneratorTableDbVo> list = generatorTableMapper.getDbTablePage(query);
        if (CollectionUtils.isNotEmpty(list)) {
            List<String> tablePrefixes = generatorProperties.getTablePrefixes();
            for (GeneratorTableDbVo vo : list) {
                String tableName = vo.getTableName();
                String className = vo.getClassName();
                if (StringUtils.isBlank(className)) {
                    // 生成类名称
                    String tableClassName = GeneratorUtil.getTableClassName(tableName, tablePrefixes);
                    vo.setClassName(tableClassName);
                }
            }
        }
        Paging<GeneratorTableDbVo> paging = new Paging<>(list);
        return paging;
    }

}
