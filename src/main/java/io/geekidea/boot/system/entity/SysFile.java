package io.geekidea.boot.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统文件
 *
 * @author geekidea
 * @since 2023-11-26
 */
@Data
@TableName("sys_file")
@Schema(description = "系统文件")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "日志链路ID")
    private String traceId;

    @Schema(description = "服务类型 1：本地服务，2：阿里云OSS")
    private Integer serverType;

    @Schema(description = "上传类型")
    private String uploadType;

    @Schema(description = "目录名称")
    private String dirName;

    @Schema(description = "源文件名称")
    private String originalFileName;

    @Schema(description = "生成的文件名称")
    private String fileName;

    @Schema(description = "文件内容类型")
    private String contentType;

    @Schema(description = "文件后缀")
    private String extension;

    @Schema(description = "文件大小")
    private Long size;

    @Schema(description = "文件大小MB")
    private BigDecimal sizeMb;

    @Schema(description = "访问的URL")
    private String url;

    @Schema(description = "系统类型 1：管理端，2：移动端")
    private Integer systemType;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "本地文件服务时的物流文件位置")
    private String filePath;

    @Schema(description = "文件类型 1：图片，2：音视频，3：文档，4：文件")
    private Integer fileType;

    @Schema(description = "IP地址")
    private String ip;

    @Schema(description = "IP区域")
    private String ipArea;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}

