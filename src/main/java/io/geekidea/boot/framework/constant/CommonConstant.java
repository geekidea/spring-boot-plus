package io.geekidea.boot.framework.constant;

/**
 * 公共常量
 *
 * @author geekidea
 * @date 2018-11-08
 */
public interface CommonConstant {

    String PACKAGE_NAME = "io.geekidea.boot";

    /**
     * 默认页码为1
     */
    Integer DEFAULT_PAGE_INDEX = 1;

    /**
     * 默认页大小为10
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页总行数名称
     */
    String PAGE_TOTAL_NAME = "total";

    /**
     * 分页数据列表名称
     */
    String PAGE_DATA_NAME = "list";

    /**
     * 分页当前页码名称
     */
    String PAGE_INDEX_NAME = "pageIndex";

    /**
     * 分页当前页大小名称
     */
    String PAGE_SIZE_NAME = "pageSize";

    String COMMA = ",";

    /**
     * 请求的原始字符串
     */
    String REQUEST_PARAM_BODY_STRING = "REQUEST_PARAM_BODY_STRING";

    /**
     * 日志链路ID
     */
    String TRACE_ID = "traceId";

    /**
     * 1000
     */
    int ONE_THOUSAND = 1000;

}
