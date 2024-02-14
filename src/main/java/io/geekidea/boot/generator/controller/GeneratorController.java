package io.geekidea.boot.generator.controller;

import cn.hutool.core.util.CharsetUtil;
import io.geekidea.boot.auth.annotation.Permission;
import io.geekidea.boot.common.enums.SysLogType;
import io.geekidea.boot.framework.annotation.Log;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.generator.dto.GeneratorCodeDto;
import io.geekidea.boot.generator.dto.GeneratorTableDto;
import io.geekidea.boot.generator.entity.GeneratorTable;
import io.geekidea.boot.generator.query.GeneratorTableQuery;
import io.geekidea.boot.generator.service.GeneratorService;
import io.geekidea.boot.generator.service.GeneratorTableService;
import io.geekidea.boot.generator.vo.GeneratorCodeVo;
import io.geekidea.boot.generator.vo.GeneratorTableDbVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 生成代码 控制器
 *
 * @author geekidea
 * @since 2023-12-29
 */
@Slf4j
@RestController
@Tag(name = "生成代码")
@RequestMapping("/admin/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @Autowired
    private GeneratorTableService generatorTableService;

    /**
     * 获取数据库表
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getDbTablePage")
    @Operation(summary = "获取数据库表")
    @Permission("generator:db-table:page")
    public ApiResult<GeneratorTableDbVo> getDbTablePage(@Valid @RequestBody GeneratorTableQuery query) {
        Paging<GeneratorTableDbVo> paging = generatorTableService.getDbTablePage(query);
        return ApiResult.success(paging);
    }

    /**
     * 获取生成代码表详情
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    @PostMapping("/getGeneratorTable/{tableName}")
    @Operation(summary = "生成代码表详情")
    @Permission("generator:table:info")
    public ApiResult<GeneratorTable> getGeneratorTable(@PathVariable String tableName) {
        GeneratorTable generatorTable = generatorTableService.getGeneratorTableInfo(tableName);
        return ApiResult.success(generatorTable);
    }

    /**
     * 修改生成代码表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @Log(value = "修改生成代码表", type = SysLogType.UPDATE)
    @PostMapping("/updateGeneratorTable")
    @Operation(summary = "修改生成代码表")
    @Permission("generator:table:update")
    public ApiResult updateGeneratorTable(@Valid @RequestBody GeneratorTableDto dto) {
        boolean flag = generatorTableService.updateGeneratorTable(dto);
        return ApiResult.result(flag);
    }

    /**
     * 预览代码
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    @PostMapping("/previewCode/{tableName}")
    @Operation(summary = "预览代码")
    @Permission("generator:preview-code")
    public ApiResult<GeneratorCodeVo> previewCode(@PathVariable String tableName) {
        List<GeneratorCodeVo> list = generatorService.previewCode(tableName);
        return ApiResult.success(list);
    }

    /**
     * 生成代码
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/generatorCode")
    @Operation(summary = "生成代码")
    @Permission("generator:generator-code")
    public ApiResult<GeneratorCodeVo> generatorCode(@Valid @RequestBody GeneratorCodeDto dto) throws Exception {
        log.info("生成代码开始：" + dto);
        List<String> tableNames = dto.getTableNames();
        generatorService.generatorCode(tableNames);
        log.info("生成代码结束：" + dto);
        return ApiResult.success();
    }

    /**
     * 下载代码
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/downloadCode")
    @Operation(summary = "下载代码")
    @Permission("generator:download-code")
    public void downloadCode(@Valid @RequestBody GeneratorCodeDto dto, HttpServletResponse response) throws Exception {
        log.info("下载代码开始：" + dto);
        List<String> tableNames = dto.getTableNames();
        Map<String, List<GeneratorCodeVo>> map = generatorService.downloadCode(tableNames);
        if (MapUtils.isNotEmpty(map)) {
            // zip下载
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            for (Map.Entry<String, List<GeneratorCodeVo>> entry : map.entrySet()) {
                List<GeneratorCodeVo> codeVos = entry.getValue();
                if (CollectionUtils.isNotEmpty(codeVos)) {
                    for (GeneratorCodeVo codeVo : codeVos) {
                        String fileName = codeVo.getFileName();
                        String zipFilePath = codeVo.getZipFilePath();
                        String fileContent = codeVo.getFileContent();
                        log.info("generatorFileName：" + fileName);
                        log.info("generatorZipFilePath：" + zipFilePath);
                        // 添加到zip
                        zipOutputStream.putNextEntry(new ZipEntry(zipFilePath));
                        IOUtils.write(fileContent, zipOutputStream, CharsetUtil.CHARSET_UTF_8);
                        zipOutputStream.flush();
                        zipOutputStream.closeEntry();
                    }
                }
            }
            IOUtils.closeQuietly(zipOutputStream);
            byte[] bytes = outputStream.toByteArray();
            String zipFileName;
            if (tableNames.size() > 1) {
                zipFileName = "spring-boot-plus-code.zip";
            } else {
                zipFileName = tableNames.get(0) + "-code.zip";
            }
            log.info("downloadZipFileName = " + zipFileName);
            response.reset();
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipFileName);
            response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytes.length));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");
            IOUtils.write(bytes, response.getOutputStream());
            log.info("downloadZipFile完成");
        }
        log.info("下载代码结束：" + dto);
    }

}
