-- 创建数据库
create database if not exists spring_boot_plus character set utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用过数据库
use spring_boot_plus;

-- 创建表结构

create table hello_world
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(20)                          not null comment '名称',
    remark      varchar(200)                         null comment '手机号码',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间'
)
    comment '你好世界';

create table sys_dept
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(32)                          not null comment '部门名称',
    parent_id   bigint                               not null comment '父id',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    sort        int        default 0                 not null comment '排序',
    create_time datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime                             null comment '修改时间'
)
    comment '部门';

create index sys_dept_name_index
    on sys_dept (name);

create table sys_log
(
    id                bigint                             not null comment '主键'
        primary key,
    trace_id          varchar(36)                        null comment '日志链路ID',
    request_time      varchar(30)                        null comment '请求时间',
    request_url       varchar(1000)                      null comment '全路径',
    permission_code   varchar(200)                       null comment '权限编码',
    log_name          varchar(200)                       null comment '日志名称',
    request_method    varchar(10)                        null comment '请求方式，GET/POST',
    content_type      varchar(200)                       null comment '内容类型',
    is_request_body   tinyint(1)                         null comment '是否是JSON请求映射参数',
    token             varchar(100)                       null comment 'token',
    module_name       varchar(100)                       null comment '模块名称',
    class_name        varchar(200)                       null comment 'controller类名称',
    method_name       varchar(200)                       null comment 'controller方法名称',
    request_param     text                               null comment '请求参数',
    user_id           bigint                             null comment '用户ID',
    username          varchar(100)                       null comment '用户名',
    request_ip        varchar(15)                        null comment '请求ip',
    ip_country        varchar(100)                       null comment 'IP国家',
    ip_province       varchar(100)                       null comment 'IP省份',
    ip_city           varchar(100)                       null comment 'IP城市',
    ip_area_desc      varchar(100)                       null comment 'IP区域描述',
    ip_isp            varchar(100)                       null comment 'IP运营商',
    log_type          int      default 0                 not null comment '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
    response_time     varchar(100)                       null comment '响应时间',
    response_success  tinyint(1)                         null comment '0:失败,1:成功',
    response_code     int                                null comment '响应结果状态码',
    response_message  text                               null comment '响应结果消息',
    response_data     text                               null comment '响应数据',
    exception_name    varchar(200)                       null comment '异常类名称',
    exception_message text                               null comment '异常信息',
    diff_time         bigint                             null comment '耗时，单位：毫秒',
    diff_time_desc    varchar(100)                       null comment '耗时描述',
    referer           varchar(1000)                      null comment '来源地址',
    origin            varchar(1000)                      null comment '请求来源',
    is_mobile         tinyint(1)                         null comment '是否手机 0：否，1：是',
    platform_name     varchar(100)                       null comment '平台名称',
    browser_name      varchar(100)                       null comment '浏览器名称',
    user_agent        varchar(1000)                      null comment '用户环境',
    remark            varchar(200)                       null comment '备注',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                           null comment '修改时间'
)
    comment '系统日志';

create index sys_log_log_name_index
    on sys_log (log_name);

create index sys_log_log_type_index
    on sys_log (log_type);

create index sys_log_module_name_index
    on sys_log (module_name);

create index sys_log_permission_code_index
    on sys_log (permission_code);

create index sys_log_request_ip_index
    on sys_log (request_ip);

create index sys_log_response_success_index
    on sys_log (response_success);

create index sys_log_trace_id_index
    on sys_log (trace_id);

create index sys_log_username_index
    on sys_log (username);

create table sys_login_log
(
    id                bigint                               not null comment '主键'
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
    device_model      varchar(100)                         null comment '移动端设备型号',
    remark            varchar(200)                         null comment '备注',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                             null comment '修改时间'
)
    comment '系统登录日志';

create table sys_menu
(
    id             bigint                               not null comment '主键'
        primary key,
    name           varchar(32)                          null comment '菜单名称',
    parent_id      bigint                               not null comment '父id',
    code           varchar(100)                         null comment '菜单唯一编码',
    icon           varchar(100)                         null comment '菜单图标',
    type           tinyint                              not null comment '菜单类型，1：菜单，2：外链，3：权限',
    status         tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    sort           int        default 0                 not null comment '排序',
    route_url      varchar(200)                         null comment '前端路由地址',
    route_name     varchar(20)                          null comment '路由名称',
    route_redirect varchar(100)                         null comment '重定向',
    component_path varchar(100)                         null comment '组件路径',
    is_show        tinyint(1)                           null comment '是否显示,0：不显示，1：显示',
    is_cache       tinyint(1) default 0                 not null comment '是否缓存，0：否 1：是',
    is_link        tinyint(1) default 0                 not null comment '是否外链，0：否 1：是',
    is_home        tinyint(1) default 0                 null comment '是否首页 0：否，1：是',
    link_url       varchar(100)                         null comment '链接地址',
    create_time    timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp                            null comment '修改时间',
    constraint sys_permission_code_uindex
        unique (code)
)
    comment '系统菜单';

create index sys_menu_name_index
    on sys_menu (name);

create index sys_menu_status_index
    on sys_menu (status);

create index sys_menu_type_index
    on sys_menu (type);

create table sys_role
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(32)                          not null comment '角色名称',
    code        varchar(100)                         null comment '角色唯一编码',
    status      tinyint(1) default 1                 not null comment '角色状态，0：禁用，1：启用',
    remark      varchar(200)                         null comment '备注',
    create_time timestamp  default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_role_code_uindex
        unique (code),
    constraint sys_role_name_uindex
        unique (name)
)
    comment '系统角色';

create index sys_role_status_index
    on sys_role (status);

create table sys_role_menu
(
    id          bigint                              not null comment '主键'
        primary key,
    role_id     bigint                              null comment '角色id',
    menu_id     bigint                              null comment '菜单id',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间'
)
    comment '角色菜单关系表';

create index sys_role_menu_menu_id_index
    on sys_role_menu (menu_id);

create index sys_role_menu_role_id_index
    on sys_role_menu (role_id);

create table sys_user
(
    id          bigint                               not null comment '主键'
        primary key,
    username    varchar(20)                          not null comment '用户名',
    nickname    varchar(20)                          not null comment '昵称',
    password    varchar(64)                          not null comment '密码',
    salt        varchar(32)                          not null comment '盐值',
    phone       varchar(20)                          null comment '手机号码',
    email       varchar(255)                         null comment '电子邮件',
    gender      tinyint    default 0                 not null comment '性别，0：未知，1：男，2：女，默认0',
    head        varchar(100)                         null comment '头像',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    dept_id     bigint                               null comment '部门id',
    is_admin    tinyint(1) default 0                 not null comment '是否是超管 0：否，1：是',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_us
        unique (username)
)
    comment '系统用户';

create index sys_user_dept_id_index
    on sys_user (dept_id);

create index sys_user_status_index
    on sys_user (status);

create table sys_user_role
(
    id          bigint                             not null comment '主键'
        primary key,
    user_id     bigint                             null comment '用户id',
    role_id     bigint                             null comment '角色id',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime                           null comment '修改时间'
)
    comment '用户角色关系表';

create index sys_user_role_role_id_index
    on sys_user_role (role_id);

create index sys_user_role_user_id_index
    on sys_user_role (user_id);


-- 测试数据
INSERT INTO hello_world (id, name, remark, status, create_time, update_time) VALUES (1, '你好', '你好世界', 1, '2023-06-26 15:04:02', null);


-- 部门数据
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (1, 'spring-boot-plus', 0, 1, 0, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (2, '技术部', 1, 1, 1, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (3, '测试部', 1, 1, 2, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (21, '技术一部', 2, 1, 1, '2023-06-18 13:48:13', '2023-06-20 22:19:32');
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (22, '技术二部', 2, 1, 2, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (23, '技术三部', 2, 1, 3, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (24, '技术四部', 2, 1, 4, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (25, '技术五部', 2, 1, 5, '2023-06-18 13:48:13', '2023-06-21 07:45:56');
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (31, '测试一部', 3, 1, 1, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (32, '测试二部', 3, 1, 2, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (33, '测试三部', 3, 1, 3, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (34, '测试四部', 3, 1, 4, '2023-06-18 13:48:13', null);
INSERT INTO sys_dept (id, name, parent_id, status, sort, create_time, update_time) VALUES (35, '测试五部', 3, 1, 10, '2023-06-18 13:48:13', '2023-06-20 22:37:25');


-- 菜单数据
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (10, '首页', 0, 'Home', 'ele-house', 1, 1, 0, '/home', 'Home', null, 'dashboard/index', 1, 0, 0, 1, null, '2023-05-27 11:31:28', '2023-06-21 12:51:44');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11, '系统管理', 0, 'system', 'ele-setting', 1, 1, 1, '/system', 'System', '/user', 'Layout', 1, 0, 0, 0, null, '2023-05-27 12:02:42', '2023-06-16 09:07:14');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1101, '用户管理', 11, 'User', 'ele-user', 1, 1, 1, '/user', 'User', null, 'system/user/index', 1, 0, 0, 0, null, '2023-05-27 12:04:05', '2023-06-16 09:08:20');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1102, '角色管理', 11, 'role', 'local-role', 1, 1, 2, '/role', 'Role', null, 'system/role/index', 1, 1, 0, 0, null, '2023-06-18 08:02:48', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1103, '菜单管理', 11, 'menu', 'local-menu', 1, 1, 3, '/menu', 'Menu', null, 'system/menu/index', 1, 1, 0, 0, null, '2023-06-18 08:03:32', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1104, '部门管理', 11, 'dept', 'local-dept', 1, 1, 4, '/dept', 'Dept', null, 'system/dept/index', 1, 0, 0, 0, null, '2023-06-18 08:01:35', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1105, '日志管理', 11, 'log', 'ele-memo', 1, 1, 5, '/log', 'Log', '/operationLog', 'Layout', 1, 0, 0, 0, null, '2023-06-23 21:06:44', '2023-06-23 21:46:47');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110101, '添加用户', 1101, 'sys:user:add', null, 3, 1, 1, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110102, '修改用户', 1101, 'sys:user:update', null, 3, 1, 2, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110103, '删除用户', 1101, 'sys:user:delete', null, 3, 1, 3, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110104, '用户详情', 1101, 'sys:user:info', null, 3, 1, 4, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110105, '用户列表', 1101, 'sys:user:list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110106, '重置用户密码', 1101, 'sys:user:reset-password', null, 3, 1, 6, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110107, '修改用户密码', 1101, 'sys:user:update-password', null, 3, 1, 6, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110108, '获取个人信息', 1101, 'sys:user:profile', null, 3, 1, 6, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110109, '修改个人信息', 1101, 'sys:user:update-profile', null, 3, 1, 6, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110201, '添加角色', 1102, 'sys:role:add', null, 3, 1, 1, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110202, '修改角色', 1102, 'sys:role:update', null, 3, 1, 2, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110203, '删除角色', 1102, 'sys:role:delete', null, 3, 1, 3, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110204, '角色详情', 1102, 'sys:role:info', null, 3, 1, 4, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110205, '角色列表', 1102, 'sys:role:list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110206, '所有角色列表', 1102, 'sys:role:all-list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110207, '设置角色权限', 1102, 'sys:role:set-role-menus', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110208, '角色权限ID集合', 1102, 'sys:role:menu-ids', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110301, '添加菜单', 1103, 'sys:menu:add', null, 3, 1, 1, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110302, '修改菜单', 1103, 'sys:menu:update', null, 3, 1, 2, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110303, '删除菜单', 1103, 'sys:menu:delete', null, 3, 1, 3, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110304, '菜单详情', 1103, 'sys:menu:info', null, 3, 1, 4, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110305, '菜单树形列表', 1103, 'sys:menu:tree-list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110306, '所有的菜单树形列表', 1103, 'sys:menu:all-tree-list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110307, '导航菜单', 1103, 'sys:menu:nav-tree-list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110401, '添加部门', 1104, 'sys:dept:add', null, 3, 1, 1, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110402, '修改部门', 1104, 'sys:dept:update', null, 3, 1, 2, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110403, '删除部门', 1104, 'sys:dept:delete', null, 3, 1, 3, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110404, '部门详情', 1104, 'sys:dept:info', null, 3, 1, 4, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110405, '部门树形列表', 1104, 'sys:dept:tree-list', null, 3, 1, 5, null, null, null, null, 1, 0, 0, 0, null, '2023-06-18 20:22:12', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110501, '操作日志', 1105, 'operationLog', 'ele-expand', 1, 1, 1, '/operationLog', 'OperationLog', null, 'system/operationLog/index', 1, 1, 0, 0, null, '2023-06-23 21:23:40', '2023-06-24 16:17:55');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11050101, '日志详情', 110501, 'sys:log:info', null, 3, 1, 2, null, null, null, null, null, 0, 0, 0, null, '2023-06-23 21:11:00', '2023-06-23 21:24:05');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11050102, '日志列表', 110501, 'sys:log:list', null, 3, 1, 3, null, null, null, null, null, 0, 0, 0, null, '2023-06-23 21:12:04', '2023-06-23 21:24:13');


-- 角色数据
INSERT INTO sys_role (id, name, code, status, remark, create_time, update_time) VALUES (1, '管理员', 'admin', 1, null, '2023-02-15 13:22:21', null);
INSERT INTO sys_role (id, name, code, status, remark, create_time, update_time) VALUES (2, '测试', 'test', 1, null, '2023-02-15 13:22:29', null);
INSERT INTO sys_role (id, name, code, status, remark, create_time, update_time) VALUES (3, '11', '22', 1, null, '2023-06-18 07:48:24', '2023-06-18 07:48:31');


-- 角色菜单数据
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1, 2, 10, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (2, 2, 11, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (3, 2, 1101, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (4, 2, 1102, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (5, 2, 1103, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (6, 2, 1104, '2023-06-18 20:47:19', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (7, 2, 110101, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (8, 2, 110102, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (9, 2, 110103, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (10, 2, 110104, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (11, 2, 110105, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (12, 2, 110106, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (13, 2, 110201, '2023-06-18 20:47:20', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (14, 2, 110202, '2023-06-18 20:47:21', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (15, 2, 110203, '2023-06-18 20:47:21', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (16, 2, 110204, '2023-06-18 20:47:21', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (17, 2, 110205, '2023-06-18 20:47:21', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (18, 2, 110206, '2023-06-18 20:47:21', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250660831233, 1671308003090935810, 1101, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250660831234, 1671308003090935810, 110101, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250660831235, 1671308003090935810, 110102, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250665025537, 1671308003090935810, 110103, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250665025538, 1671308003090935810, 110104, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250665025539, 1671308003090935810, 110105, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671311250665025540, 1671308003090935810, 110106, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865650552833, 3, 1101, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865658941442, 3, 110101, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865658941443, 3, 110102, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865658941444, 3, 110103, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865663135745, 3, 110104, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865663135746, 3, 110105, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1671596865663135747, 3, 110106, '2023-06-22 03:12:04', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918605877250, 1672234730873024514, 10, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918605877251, 1672234730873024514, 1101, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071554, 1672234730873024514, 110101, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071555, 1672234730873024514, 110102, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071556, 1672234730873024514, 110103, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071557, 1672234730873024514, 110104, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071558, 1672234730873024514, 110105, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071559, 1672234730873024514, 110106, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071560, 1672234730873024514, 110201, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918610071561, 1672234730873024514, 110202, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460161, 1672234730873024514, 110203, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460162, 1672234730873024514, 110204, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460163, 1672234730873024514, 110302, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460164, 1672234730873024514, 110303, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460165, 1672234730873024514, 110304, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460166, 1672234730873024514, 110305, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460167, 1672234730873024514, 110306, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460168, 1672234730873024514, 1104, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460169, 1672234730873024514, 110401, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460170, 1672234730873024514, 110402, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460171, 1672234730873024514, 110403, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918618460172, 1672234730873024514, 110404, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES (1672234918622654465, 1672234730873024514, 110405, '2023-06-23 21:27:33', null);


-- 用户数据
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, dept_id, is_admin, create_time, update_time) VALUES (1, 'admin', '管理员', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304', '94132dcd55eb478bb63170a2fa510ea1', '15812345678', 'geekidea@qq.com', 1, 'http://42.193.249.218:8888/file/20230626144521890462970.png', 1, 1669290098346250241, 1, '2023-02-15 13:21:02', '2023-06-25 16:55:18');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, dept_id, is_admin, create_time, update_time) VALUES (2, 'test', '测试', 'aa588acb55ee7ec0d7f2107a40bb47cfac8b73cf7cb1217a6930aecb3bf178ee', '666', '13577777777', 'asadasfasf', 0, null, 1, 1669290098346250241, 0, '2023-05-08 21:38:33', '2023-06-21 07:33:23');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, dept_id, is_admin, create_time, update_time) VALUES (3, 'cs', 'cs', 'aa588acb55ee7ec0d7f2107a40bb47cfac8b73cf7cb1217a6930aecb3bf178ee', '666', '13577777777', '121321@qq.com', 0, null, 0, 2, 0, '2023-06-16 04:00:45', '2023-06-22 03:01:20');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, dept_id, is_admin, create_time, update_time) VALUES (1672233328339709953, 'hello', 'xxx', '4bce435e22d61fc3e538964fe70ad920a071db6b8151c3e92f6203b20e5e447b', '2aa67c95f2214c52bb57072fa3b909ac', '18889898989', '2929@qa.com', 1, null, 1, 1, 0, '2023-06-23 21:21:13', '2023-06-25 20:50:46');


-- 用户角色数据
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1671300240755437570, 2, 2, '2023-06-21 07:33:23', null);
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1671550509347106817, 1, 1, '2023-06-22 00:07:51', null);
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1671550509359689729, 1, 2, '2023-06-22 00:07:51', null);
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1671594161742147585, 3, 1, '2023-06-22 03:01:19', null);
INSERT INTO sys_user_role (id, user_id, role_id, create_time, update_time) VALUES (1672950457731936258, 1672233328339709953, 2, '2023-06-25 20:50:45', null);
