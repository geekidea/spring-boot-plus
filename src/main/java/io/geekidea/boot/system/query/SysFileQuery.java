package io.geekidea.boot.system.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 系统文件查询参数
 *
 * @author geekidea
 * @since 2023-11-26
 */
@Data
@Schema(description = "系统文件查询参数")
public class SysFileQuery extends BasePageQuery {
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件类型 1：图片，2：音视频，3：文档，4：文件")
    private Integer fileType;

    @Schema(description = "创建开始时间")
    private Date createStartTime;

    @Schema(description = "创建结束时间")
    private Date createEndTime;

}

