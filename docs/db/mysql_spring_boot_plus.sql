
create database if not exists spring_boot_plus character set utf8mb4 COLLATE utf8mb4_general_ci;

use spring_boot_plus;

create table sys_dept
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)                          not null comment '部门名称',
    parent_id   bigint                               null comment '父id',
    status      tinyint(2) default 1                 not null comment '状态，0：禁用，1：启用',
    sort        int        default 0                 not null comment '排序',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间'
)
    comment '部门';

create table sys_log
(
    id                bigint auto_increment comment '主键'
        primary key,
    request_id        varchar(32)                        null comment '请求ID',
    request_time      varchar(100)                       null comment '请求时间',
    request_url       varchar(1000)                      null comment '全路径',
    log_name          varchar(500)                       null comment '日志名称',
    request_method    varchar(10)                        null comment '请求方式，GET/POST',
    content_type      varchar(200)                       null comment '内容类型',
    is_request_body   tinyint(1)                         null comment '是否是JSON请求映射参数',
    request_param     text                               null comment '请求参数',
    user_id           bigint                             null comment '用户ID',
    username          varchar(100)                       null comment '用户名',
    request_ip        varchar(15)                        null comment '请求ip',
    log_type          int(3)                             null comment '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
    response_success  tinyint(1)                         null comment '0:失败,1:成功',
    response_code     int                                null comment '响应结果状态码',
    response_message  text                               null comment '响应结果消息',
    response_data     text                               null comment '响应数据',
    exception_name    varchar(200)                       null comment '异常类名称',
    exception_message text                               null comment '异常信息',
    response_time     varchar(100)                       null comment '响应时间',
    diff_time         bigint                             null comment '耗时，单位：毫秒',
    referer           varchar(500)                       null comment '来源地址',
    request_source    varchar(100)                       null comment '请求来源',
    user_agent        varchar(1000)                      null comment '用户环境',
    remark            varchar(200)                       null comment '备注',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                           null comment '修改时间'
)
    comment '系统日志';

create table sys_login_log
(
    id                bigint(18) auto_increment comment '主键'
        primary key,
    request_id        varchar(32)                          null comment '请求ID',
    username          varchar(32)                          null comment '用户名称',
    ip                varchar(15)                          null comment 'IP',
    token             varchar(32)                          null comment 'token',
    type              int                                  null comment '1:登录，2：登出',
    success           tinyint(1) default 0                 not null comment '是否成功 true:成功/false:失败',
    code              int                                  null comment '响应码',
    exception_message varchar(300)                         null comment '失败消息记录',
    user_agent        varchar(300)                         null comment '用户环境',
    browser_name      varchar(100)                         null comment '浏览器名称',
    browser_version   varchar(100)                         null comment '浏览器版本',
    engine_name       varchar(100)                         null comment '浏览器引擎名称',
    engine_version    varchar(100)                         null comment '浏览器引擎版本',
    os_name           varchar(100)                         null comment '系统名称',
    platform_name     varchar(100)                         null comment '平台名称',
    mobile            tinyint(1)                           null comment '是否是手机,0:否,1:是',
    device_name       varchar(100)                         null comment '移动端设备名称',
    device_model      varchar(100)                         null comment '移动端设备型号',
    remark            varchar(200)                         null comment '备注',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                             null comment '修改时间'
)
    comment '系统登录日志';

create table sys_menu
(
    id             bigint auto_increment comment '主键'
        primary key,
    name           varchar(32)                          null comment '菜单名称',
    parent_id      bigint                               null comment '父id',
    code           varchar(100)                         not null comment '菜单唯一编码',
    icon           varchar(100)                         null comment '菜单图标',
    type           tinyint(2)                           not null comment '菜单类型，1：菜单，2：按钮',
    status         tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    sort           int        default 0                 not null comment '排序',
    route_url      varchar(200)                         null comment '前端路由地址',
    route_name     varchar(20)                          null comment '路由名称',
    route_redirect varchar(100)                         null comment '重定向',
    request_url    varchar(200)                         null comment '后端请求地址',
    component_path varchar(100)                         null comment '组件路径',
    is_show        tinyint(1)                           null comment '是否显示,0：不显示，1：显示',
    is_cache       tinyint(1) default 0                 not null comment '是否缓存，0：否 1：是',
    is_link        tinyint(1) default 0                 not null comment '是否外链，0：否 1：是',
    link_url       varchar(100)                         null comment '链接地址',
    create_time    timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp                            null comment '修改时间',
    constraint sys_permission_code_uindex
        unique (code)
)
    comment '系统菜单';

create table sys_role
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)                          not null comment '角色名称',
    code        varchar(100)                         null comment '角色唯一编码',
    status      tinyint(1) default 1                 not null comment '角色状态，0：禁用，1：启用',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_role_name_uindex
        unique (name)
)
    comment '系统角色';

create table sys_role_menu
(
    id          bigint auto_increment comment '主键'
        primary key,
    role_id     bigint                              null comment '角色id',
    menu_id     bigint                              null comment '菜单id',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间'
)
    comment '角色菜单关系表';

create table sys_user
(
    id          bigint(19) auto_increment comment '主键'
        primary key,
    username    varchar(20)                          not null comment '用户名',
    nickname    varchar(20)                          not null comment '昵称',
    password    varchar(64)                          not null comment '密码',
    salt        varchar(32)                          not null comment '盐值',
    phone       varchar(20)                          null comment '手机号码',
    email       varchar(255)                         null comment '电子邮件',
    gender      tinyint(2) default 1                 not null comment '性别，0：女，1：男，默认1',
    head        varchar(200)                         null comment '头像',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    dept_id     bigint(19)                           null comment '部门id',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_user_username_uindex
        unique (username)
)
    comment '系统用户';

create index department_id
    on sys_user (dept_id);

create table sys_user_role
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_id     bigint                              null comment '用户id',
    role_id     bigint                              null comment '角色id',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间'
)
    comment '用户角色关系表';


INSERT INTO sys_role (id, name, code, status, create_time, update_time) VALUES (1, '管理员', 'admin', 1, '2023-02-15 13:22:21', null);
INSERT INTO sys_role (id, name, code, status, create_time, update_time) VALUES (2, '测试', 'test', 1, '2023-02-15 13:22:29', null);
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, dept_id, create_time, update_time) VALUES (1, 'admin', '管理员', '355e02d13bce12b4090b5117d6d4852f', '666', '15812345678', 'geekidea@qq.com', 1, null, 1, null, '2023-02-15 13:21:02', null);
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1, 1, 1, '2023-02-15 13:22:37', null);


