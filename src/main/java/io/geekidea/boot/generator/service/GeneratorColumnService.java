package io.geekidea.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.generator.dto.GeneratorColumnDto;
import io.geekidea.boot.generator.entity.GeneratorColumn;
import io.geekidea.boot.generator.vo.GeneratorColumnDbVo;

import java.util.List;


/**
 * 生成代码列 服务接口
 *
 * @author geekidea
 * @since 2023-12-29
 */
public interface GeneratorColumnService extends IService<GeneratorColumn> {

    /**
     * 添加生成代码列
     *
     * @param tableName
     * @param validateField
     * @return
     * @throws Exception
     */
    boolean addGeneratorColumn(String tableName, Boolean validateField);

    /**
     * 修改生成代码列
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateGeneratorColumn(GeneratorColumnDto dto);

    /**
     * 根据表名称获取数据库原生列集合
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    List<GeneratorColumnDbVo> getDbColumnListByTableName(String tableName);

    /**
     * 根据表名称获取生成列集合
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    List<GeneratorColumn> getGeneratorColumnList(String tableName);

}
