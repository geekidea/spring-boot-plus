package io.geekidea.boot.common.enums;

/**
 * 系统日志类型
 * 0:访问日志,1:新增,2:修改,3:删除,4:详情,5:所有列表,6:分页列表,7:其它查询,8:上传文件,9:登录,10:退出
 *
 * @author geekidea
 * @date 2023/2/15
 **/
public enum SysLogType {

    OTHER(0, "访问日志"),
    ADD(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    INFO(4, "详情查询"),
    ALL_LIST(5, "所有列表"),
    LIST(6, "分页列表"),
    OTHER_QUERY(7, "其它查询"),
    UPLOAD(8, "上传文件"),
    Login(9, "登录"),
    LOGOUT(10, "退出");

    private Integer code;
    private String desc;

    SysLogType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SysLogType getSysLogType(Integer code) {
        if (code == null) {
            return OTHER;
        }
        for (SysLogType sysLogType : values()) {
            if (sysLogType.getCode().equals(code)) {
                return sysLogType;
            }
        }
        return OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
