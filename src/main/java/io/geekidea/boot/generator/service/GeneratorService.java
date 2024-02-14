package io.geekidea.boot.generator.service;

import io.geekidea.boot.generator.vo.GeneratorCodeVo;

import java.util.List;
import java.util.Map;

/**
 * 生成代码 服务接口
 *
 * @author geekidea
 * @since 2023-12-31
 */
public interface GeneratorService {

    /**
     * 预览代码
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    List<GeneratorCodeVo> previewCode(String tableName);

    /**
     * 生成代码
     *
     * @param tableNames
     * @return
     * @throws Exception
     */
    void generatorCode(List<String> tableNames) throws Exception;

    /**
     * 下载代码
     *
     * @param tableNames
     * @return
     * @throws Exception
     */
    Map<String, List<GeneratorCodeVo>> downloadCode(List<String> tableNames);

}
