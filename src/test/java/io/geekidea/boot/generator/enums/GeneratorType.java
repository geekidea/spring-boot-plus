package io.geekidea.boot.generator.enums;

/**
 * @author geekidea
 * @date 2023/11/19
 **/
public enum GeneratorType {

    /**
     * 基本代码结构：entity、controller、service、serviceImpl、mapper、mapperXml，不包含dto、query、vo，没有增删改查代码
     */
    BASIC,
    /**
     * 生成entity、controller、service、serviceImpl、mapper、mapperXml，包含dto、query、vo，以及增删改查代码
     */
    FULL

}
