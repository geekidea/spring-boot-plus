package io.geekidea.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.generator.dto.GeneratorTableDto;
import io.geekidea.boot.generator.entity.GeneratorTable;
import io.geekidea.boot.generator.query.GeneratorTableQuery;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;


/**
 * 生成代码表 服务接口
 *
 * @author geekidea
 * @since 2023-12-29
 */
public interface GeneratorTableService extends IService<GeneratorTable> {

    /**
     * 添加生成代码表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    boolean addGeneratorTable(String tableName);

    /**
     * 修改生成代码表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    boolean updateGeneratorTable(GeneratorTableDto dto);

    /**
     * 生成代码表详情
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    GeneratorTable getGeneratorTableInfo(String tableName);

    /**
     * 获取数据库表
     *
     * @param query
     * @return
     * @throws Exception
     */
    Paging<GeneratorTableDbVo> getDbTablePage(GeneratorTableQuery query);

}
