package io.geekidea.boot.framework.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目信息
 *
 * @author geekidea
 * @date 2023/12/17
 **/
@Data
@Schema(description = "项目信息")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 8809514536353797869L;

    @Schema(description = "项目名称")
    private String name;

    @Schema(description = "项目端口")
    private String port;

    @Schema(description = "项目路径")
    private String contextPath;

    @Schema(description = "激活的环境")
    private String active;

    @Schema(description = "项目运行目录")
    private String userDir;


}
