package io.geekidea.boot.generator.config;

import lombok.Data;

/**
 * @author geekidea
 * @date 2022/7/2
 **/
@Data
public class SimpleGeneratorConfig {
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String desc;
    /**
     * 作者
     */
    private String author;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 是否生成controller，默认生成
     */
    private boolean generateController = true;
    /**
     * 是否生成service及serviceImpl，默认生成
     */
    private boolean generateService = true;
    /**
     * 是否生成mapper及xml，默认生成
     */
    private boolean generateMapper = true;
}
