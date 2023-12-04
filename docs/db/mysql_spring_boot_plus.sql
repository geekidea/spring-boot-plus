-- 创建数据库
create database if not exists spring_boot_plus character set utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用过数据库
use spring_boot_plus;

-- 系统用户
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
    role_id     bigint                               not null comment '角色ID',
    is_admin    tinyint(1) default 0                 not null comment '是否是超管 0：否，1：是',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_us
        unique (username)
)
    comment '系统用户' collate = utf8mb4_general_ci;

create index sys_user_status_index
    on sys_user (status);

INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, role_id, is_admin, create_time, update_time) VALUES (1, 'admin', '管理员', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304', '94132dcd55eb478bb63170a2fa510ea1', '15812345678', 'geekidea@qq.com', 1, 'http://42.193.249.218:8888/file/20230626144521890462970.png', 1, 1, 1, '2023-02-15 13:21:02', '2023-06-25 16:55:18');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, role_id, is_admin, create_time, update_time) VALUES (2, 'test', '测试', 'aa588acb55ee7ec0d7f2107a40bb47cfac8b73cf7cb1217a6930aecb3bf178ee', '666', '13577777777', 'asadasfasf', 0, null, 1, 2, 0, '2023-05-08 21:38:33', '2023-06-21 07:33:23');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, email, gender, head, status, role_id, is_admin, create_time, update_time) VALUES (3, 'cs', 'cs', 'aa588acb55ee7ec0d7f2107a40bb47cfac8b73cf7cb1217a6930aecb3bf178ee', '666', '13577777777', '121321@qq.com', 0, null, 0, 3, 0, '2023-06-16 04:00:45', '2023-06-22 03:01:20');

-- 系统角色
create table sys_role
(
    id          bigint                              not null comment '主键'
        primary key,
    code        varchar(100)                        null comment '角色唯一编码',
    name        varchar(32)                         not null comment '角色名称',
    remark      varchar(200)                        null comment '备注',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp                           null comment '修改时间',
    constraint sys_role_code_uindex
        unique (code),
    constraint sys_role_name_uindex
        unique (name)
)
    comment '系统角色' collate = utf8mb4_general_ci;

INSERT INTO sys_role (id, code, name, remark, create_time, update_time) VALUES (1, 'admin', '管理员', null, '2023-02-15 13:22:21', null);
INSERT INTO sys_role (id, code, name, remark, create_time, update_time) VALUES (2, 'test', '测试', null, '2023-02-15 13:22:29', null);
INSERT INTO sys_role (id, code, name, remark, create_time, update_time) VALUES (3, '22', '11', null, '2023-06-18 07:48:24', '2023-06-18 07:48:31');

-- 系统菜单
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
    comment '系统菜单' collate = utf8mb4_general_ci;

create index sys_menu_name_index
    on sys_menu (name);

create index sys_menu_status_index
    on sys_menu (status);

create index sys_menu_type_index
    on sys_menu (type);

INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (10, '首页', 0, 'Home', 'ele-house', 1, 1, 0, '/home', 'Home', null, 'dashboard/index', 1, 0, 0, 1, null, '2023-05-27 11:31:28', '2023-06-21 12:51:44');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11, '系统管理', 0, 'system', 'ele-setting', 1, 1, 1, '/system', 'System', '/user', 'Layout', 1, 0, 0, 0, null, '2023-05-27 12:02:42', '2023-06-16 09:07:14');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1101, '用户管理', 11, 'User', 'ele-user', 1, 1, 1, '/user', 'User', null, 'system/user/index', 1, 0, 0, 0, null, '2023-05-27 12:04:05', '2023-06-16 09:08:20');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1102, '角色管理', 11, 'role', 'local-role', 1, 1, 2, '/role', 'Role', null, 'system/role/index', 1, 1, 0, 0, null, '2023-06-18 08:02:48', null);
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (1103, '菜单管理', 11, 'menu', 'local-menu', 1, 1, 3, '/menu', 'Menu', null, 'system/menu/index', 1, 1, 0, 0, null, '2023-06-18 08:03:32', null);
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
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (110501, '操作日志', 1105, 'operationLog', 'ele-expand', 1, 1, 1, '/operationLog', 'OperationLog', null, 'system/operationLog/index', 1, 1, 0, 0, null, '2023-06-23 21:23:40', '2023-06-24 16:17:55');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11050101, '日志详情', 110501, 'sys:log:info', null, 3, 1, 2, null, null, null, null, null, 0, 0, 0, null, '2023-06-23 21:11:00', '2023-06-23 21:24:05');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (11050102, '日志列表', 110501, 'sys:log:list', null, 3, 1, 3, null, null, null, null, null, 0, 0, 0, null, '2023-06-23 21:12:04', '2023-06-23 21:24:13');
INSERT INTO sys_menu (id, name, parent_id, code, icon, type, status, sort, route_url, route_name, route_redirect, component_path, is_show, is_cache, is_link, is_home, link_url, create_time, update_time) VALUES (488299583537157, '字典管理', 11, 'dict', 'ele-notebook', 1, 1, 3, '/dict', 'Dict', null, 'system/dict/index', 1, 0, 0, 0, null, '2023-11-30 21:16:07', null);

-- 系统角色菜单
create table sys_role_menu
(
    id          bigint                              not null comment '主键'
        primary key,
    role_id     bigint                              null comment '角色id',
    menu_id     bigint                              null comment '菜单id',
    is_choice   tinyint(1)                          null comment '是否用户选中 0：否，1：是',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间'
)
    comment '角色菜单关系表' collate = utf8mb4_general_ci;

create index sys_role_menu_menu_id_index
    on sys_role_menu (menu_id);

create index sys_role_menu_role_id_index
    on sys_role_menu (role_id);

INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250660831233, 1671308003090935810, 1101, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250660831234, 1671308003090935810, 110101, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250660831235, 1671308003090935810, 110102, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250665025537, 1671308003090935810, 110103, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250665025538, 1671308003090935810, 110104, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250665025539, 1671308003090935810, 110105, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1671311250665025540, 1671308003090935810, 110106, null, '2023-06-21 08:17:08', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918605877250, 1672234730873024514, 10, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918605877251, 1672234730873024514, 1101, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071554, 1672234730873024514, 110101, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071555, 1672234730873024514, 110102, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071556, 1672234730873024514, 110103, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071557, 1672234730873024514, 110104, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071558, 1672234730873024514, 110105, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071559, 1672234730873024514, 110106, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071560, 1672234730873024514, 110201, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918610071561, 1672234730873024514, 110202, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460161, 1672234730873024514, 110203, null, '2023-06-23 21:27:32', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460162, 1672234730873024514, 110204, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460163, 1672234730873024514, 110302, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460164, 1672234730873024514, 110303, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460165, 1672234730873024514, 110304, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460166, 1672234730873024514, 110305, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460167, 1672234730873024514, 110306, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460168, 1672234730873024514, 1104, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460169, 1672234730873024514, 110401, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460170, 1672234730873024514, 110402, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460171, 1672234730873024514, 110403, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918618460172, 1672234730873024514, 110404, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1672234918622654465, 1672234730873024514, 110405, null, '2023-06-23 21:27:33', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376820191234, 3, 10, 1, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376820191235, 3, 110101, 1, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376820191236, 3, 110105, 1, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376820191237, 3, 110306, 1, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376828579842, 3, 11, 0, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376828579843, 3, 1101, 0, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1721933376828579844, 3, 1103, 0, '2023-11-08 00:51:23', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118368215041, 2, 10, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118376603649, 2, 11, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118376603650, 2, 1101, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118380797953, 2, 110101, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118380797954, 2, 110102, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118384992258, 2, 110103, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118384992259, 2, 110104, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118384992260, 2, 110105, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118389186561, 2, 110109, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118389186562, 2, 110107, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118389186563, 2, 110106, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118393380865, 2, 110108, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118393380866, 2, 1102, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118393380867, 2, 110201, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118393380868, 2, 110202, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118397575170, 2, 110203, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118397575171, 2, 110204, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118401769474, 2, 110205, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118401769475, 2, 110206, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118401769476, 2, 110207, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118401769477, 2, 110208, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118405963778, 2, 1103, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118405963779, 2, 110301, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118405963780, 2, 110302, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118405963781, 2, 110303, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118410158081, 2, 110304, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118410158082, 2, 110305, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118410158083, 2, 110306, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118410158084, 2, 110307, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118410158085, 2, 1104, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118414352385, 2, 110401, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118414352386, 2, 110402, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118414352387, 2, 110403, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118414352388, 2, 110404, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118418546690, 2, 110405, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118418546691, 2, 1105, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118418546692, 2, 110501, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118418546693, 2, 11050101, 1, '2023-11-12 00:04:27', null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_time, update_time) VALUES (1723371118418546694, 2, 11050102, 1, '2023-11-12 00:04:27', null);


-- 系统字典类型
create table sys_dict_type
(
    id          bigint                               not null comment '主键'
        primary key,
    code        varchar(100)                         not null comment '字典类型编码',
    name        varchar(100)                         not null comment '字典类型名称',
    is_system   tinyint(1) default 0                 not null comment '是否是系统字典类型',
    remark      varchar(200)                         null comment '备注',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_dict_type_code_uindex
        unique (code)
)
    comment '字典类型' collate = utf8mb4_general_ci;

INSERT INTO sys_dict_type (id, code, name, is_system, remark, create_time, update_time) VALUES (1, 'vip_level', 'VIP等级', 1, '会员等级 1：普通会员，2：黄金会员，3：铂金会员，4：钻石会员', '2023-11-25 22:17:51', null);


-- 系统字典数据
create table sys_dict
(
    id          bigint                               not null comment '主键'
        primary key,
    value       varchar(200)                         not null comment '字典值',
    label       varchar(100)                         not null comment '字典名称',
    dict_code   varchar(100)                         not null comment '字典类型code',
    status      tinyint(1) default 1                 not null comment '状态 1：启用，0：禁用',
    sort        int        default 0                 not null comment '排序值',
    remark      varchar(200)                         null comment '备注',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间',
    constraint sys_dict_dict_code_value_uindex
        unique (dict_code, value)
)
    comment '字典数据' collate = utf8mb4_general_ci;

create index sys_dict_dict_type_index
    on sys_dict (dict_code);

create index sys_dict_label_index
    on sys_dict (label);

create index sys_dict_value_index
    on sys_dict (value);

INSERT INTO sys_dict (id, value, label, dict_code, status, sort, remark, create_time, update_time) VALUES (1, '1', '普通会员', 'vip_level', 1, 1, null, '2023-11-25 22:19:55', null);
INSERT INTO sys_dict (id, value, label, dict_code, status, sort, remark, create_time, update_time) VALUES (2, '2', '黄金会员', 'vip_level', 1, 2, null, '2023-11-25 22:19:55', null);
INSERT INTO sys_dict (id, value, label, dict_code, status, sort, remark, create_time, update_time) VALUES (3, '3', '铂金会员', 'vip_level', 1, 3, null, '2023-11-25 22:19:55', null);
INSERT INTO sys_dict (id, value, label, dict_code, status, sort, remark, create_time, update_time) VALUES (4, '4', '钻石会员', 'vip_level', 1, 4, null, '2023-11-25 22:19:55', null);


-- 系统参数配置
create table sys_config
(
    id           bigint auto_increment comment '主键'
        primary key,
    config_name  varchar(100)                         not null comment '配置名称',
    config_key   varchar(100)                         not null comment '配置key',
    config_value varchar(200)                         not null comment '配置值',
    is_system    tinyint(1) default 0                 not null comment '是否是系统字典类型',
    status       tinyint(1) default 1                 not null comment '状态 1：正常，0：禁用',
    remark       varchar(200)                         null comment '备注',
    create_time  timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  timestamp                            null comment '修改时间',
    constraint sys_config_config_key_uindex
        unique (config_key)
)
    comment '系统配置' collate = utf8mb4_general_ci;


-- 系统文件
create table sys_file
(
    id                 bigint                              not null comment '主键'
        primary key,
    trace_id           varchar(32)                         null comment '日志链路ID',
    server_type        tinyint                             not null comment '服务类型 1：本地服务，2：阿里云OSS',
    upload_type        varchar(32)                         null comment '上传类型',
    dir_name           varchar(100)                        null comment '目录名称',
    original_file_name varchar(200)                        null comment '源文件名称',
    file_name          varchar(200)                        null comment '生成的文件名称',
    content_type       varchar(200)                        null comment '文件内容类型',
    extension          varchar(32)                         null comment '文件后缀',
    size               bigint                              null comment '文件大小',
    size_mb            decimal(10, 2)                      null comment '文件大小MB',
    url                varchar(500)                        null comment '访问的URL',
    system_type        tinyint                             null comment '系统类型 1：管理端，2：移动端',
    user_id            bigint                              null comment '用户ID',
    file_path          varchar(500)                        null comment '本地文件服务时的物流文件位置',
    file_type          int                                 null comment '文件类型 1：图片，2：音视频，3：文档，4：文件',
    ip                 varchar(32)                         null comment 'IP地址',
    ip_area            varchar(100)                        null comment 'IP区域',
    create_time        timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time        timestamp                           null comment '修改时间'
)
    comment '系统文件' collate = utf8mb4_general_ci;


-- 系统日志
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
    system_type       int                                null comment '系统类型 1：Admin管理后台，2：APP移动端端',
    user_id           bigint                             null comment '用户ID',
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
    referer           varchar(1000)                      null comment '请求来源地址',
    origin            varchar(1000)                      null comment '请求来源服务名',
    source_type       varchar(100)                       null comment '请求来源类型',
    is_mobile         tinyint(1)                         null comment '是否手机 0：否，1：是',
    platform_name     varchar(100)                       null comment '平台名称',
    browser_name      varchar(100)                       null comment '浏览器名称',
    user_agent        varchar(1000)                      null comment '用户环境',
    remark            varchar(200)                       null comment '备注',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                           null comment '修改时间'
)
    comment '系统日志' collate = utf8mb4_general_ci;

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

INSERT INTO sys_log (id, trace_id, request_time, request_url, permission_code, log_name, request_method, content_type, is_request_body, token, module_name, class_name, method_name, request_param, system_type, user_id, request_ip, ip_country, ip_province, ip_city, ip_area_desc, ip_isp, log_type, response_time, response_success, response_code, response_message, response_data, exception_name, exception_message, diff_time, diff_time_desc, referer, origin, source_type, is_mobile, platform_name, browser_name, user_agent, remark, create_time, update_time) VALUES (485872183824453, '485872036352069', '2023-11-24 00:39:00.133', '/app/fooBar/getAppFooBarPage', null, 'AppFooBar分页列表', 'POST', 'application/json', 1, 'boot.app.c4ca4238a0b923820dcc509a6f75849b.11570a16a9014161983d1688c49f891a', 'foobar', 'io.geekidea.boot.foobar.controller.FooBarAppController', 'getAppFooBarPage', '{
  "orderByColumn": "",
  "orderByAsc": true,
  "pageIndex": 1,
  "pageSize": 10
}', null, null, '127.0.0.1', '0', null, null, '内网IP', null, 0, '2023-11-24 00:39:00.586', 1, 200, '操作成功', '{"list":[{"bar":"Bar","createTime":"2023-07-01 21:01:10","foo":"Foo","id":1,"name":"FooBar","status":true}],"pageIndex":1,"pageSize":10,"total":1}', null, null, 453, '453ms', 'http://localhost:8888/api/doc.html', 'http://localhost:8888', 'Knife4j', 0, 'Mac', 'Chrome', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', null, '2023-11-24 00:39:00', null);
INSERT INTO sys_log (id, trace_id, request_time, request_url, permission_code, log_name, request_method, content_type, is_request_body, token, module_name, class_name, method_name, request_param, system_type, user_id, request_ip, ip_country, ip_province, ip_city, ip_area_desc, ip_isp, log_type, response_time, response_success, response_code, response_message, response_data, exception_name, exception_message, diff_time, diff_time_desc, referer, origin, source_type, is_mobile, platform_name, browser_name, user_agent, remark, create_time, update_time) VALUES (485872323690501, '485872307879941', '2023-11-24 00:39:34.449', '/app/fooBar/getAppFooBarPage', null, 'AppFooBar分页列表', 'POST', 'application/json', 1, 'boot.app.c4ca4238a0b923820dcc509a6f75849b.11570a16a9014161983d1688c49f891a', 'foobar', 'io.geekidea.boot.foobar.controller.FooBarAppController', 'getAppFooBarPage', '{
  "orderByColumn": "",
  "orderByAsc": true,
  "pageIndex": 1,
  "pageSize": 10
}', null, null, '127.0.0.1', '0', null, null, '内网IP', null, 0, '2023-11-24 00:39:34.758', 1, 200, '操作成功', '{"list":[{"bar":"Bar","createTime":"2023-07-01 21:01:10","foo":"Foo","id":1,"name":"FooBar","status":true}],"pageIndex":1,"pageSize":10,"total":1}', null, null, 309, '309ms', 'http://localhost:8888/api/doc.html', 'http://localhost:8888', 'Knife4j', 0, 'Mac', 'Chrome', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', null, '2023-11-24 00:39:34', null);
INSERT INTO sys_log (id, trace_id, request_time, request_url, permission_code, log_name, request_method, content_type, is_request_body, token, module_name, class_name, method_name, request_param, system_type, user_id, request_ip, ip_country, ip_province, ip_city, ip_area_desc, ip_isp, log_type, response_time, response_success, response_code, response_message, response_data, exception_name, exception_message, diff_time, diff_time_desc, referer, origin, source_type, is_mobile, platform_name, browser_name, user_agent, remark, create_time, update_time) VALUES (485875003797509, '485875000942597', '2023-11-24 00:50:28.827', '/app/fooBar/getAppFooBarPage', null, 'AppFooBar分页列表', 'POST', 'application/json', 1, 'boot.app.c4ca4238a0b923820dcc509a6f75849b.11570a16a9014161983d1688c49f891a', 'foobar', 'io.geekidea.boot.foobar.controller.FooBarAppController', 'getAppFooBarPage', '{
  "orderByColumn": "",
  "orderByAsc": true,
  "pageIndex": 1,
  "pageSize": 10
}', null, 1, '127.0.0.1', '0', null, null, '内网IP', null, 0, '2023-11-24 00:50:29.062', 1, 200, '操作成功', '{"list":[{"bar":"Bar","createTime":"2023-07-01 21:01:10","foo":"Foo","id":1,"name":"FooBar","status":true}],"pageIndex":1,"pageSize":10,"total":1}', null, null, 235, '235ms', 'http://localhost:8888/api/doc.html', 'http://localhost:8888', 'Knife4j', 0, 'Mac', 'Chrome', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', null, '2023-11-24 00:50:29', null);
INSERT INTO sys_log (id, trace_id, request_time, request_url, permission_code, log_name, request_method, content_type, is_request_body, token, module_name, class_name, method_name, request_param, system_type, user_id, request_ip, ip_country, ip_province, ip_city, ip_area_desc, ip_isp, log_type, response_time, response_success, response_code, response_message, response_data, exception_name, exception_message, diff_time, diff_time_desc, referer, origin, source_type, is_mobile, platform_name, browser_name, user_agent, remark, create_time, update_time) VALUES (486213068173317, '486213065392133', '2023-11-24 23:46:03.791', '/admin/login', null, '管理后台登录', 'POST', 'application/json', 1, null, 'auth', 'io.geekidea.boot.auth.controller.LoginController', 'login', '{
  "username": "admin",
  "password": "e10adc3949ba59abbe56e057f20f883e"
}', null, null, '127.0.0.1', '0', null, null, '内网IP', null, 0, '2023-11-24 23:46:04.300', 0, 500, '操作失败', null, 'RedisConnectionFailureException', 'Unable to connect to Redis; nested exception is org.springframework.data.redis.connection.PoolException: Could not get a resource from the pool; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to 127.0.0.1:6379', 509, '509ms', 'http://localhost:8888/api/doc.html', 'http://localhost:8888', 'Knife4j', 0, 'Mac', 'Chrome', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', null, '2023-11-24 23:46:04', null);


-- APP端用户
create table user
(
    id                 bigint                               not null comment 'ID'
        primary key,
    username           varchar(20)                          null comment '账号',
    nickname           varchar(20)                          null comment '昵称',
    password           varchar(64)                          null comment '密码',
    salt               varchar(32)                          null comment '盐值',
    openid             varchar(200)                         null comment '微信openid',
    phone              varchar(11)                          null comment '手机号码',
    head               varchar(200)                         null comment '头像',
    introduction       varchar(200)                         null comment '个人简介',
    is_vip             tinyint(1) default 0                 not null comment '是否是VIP，1：是，0：否',
    vip_level          int                                  not null comment 'VIP等级 1：普通会员，2：黄金会员，3：铂金会员，4：钻石会员',
    status             tinyint(1) default 1                 not null comment '状态 1：正常，0：禁用',
    register_time      datetime                             null comment '注册时间',
    register_ip        varchar(20)                          null comment '注册IP',
    register_ip_area   varchar(100)                         null comment '注册IP区域',
    last_login_time    datetime                             null comment '最后登录时间',
    last_login_ip      varchar(20)                          null comment '最后登录IP',
    last_login_ip_area varchar(100)                         null comment '最后一次登录IP区域',
    remark             varchar(200)                         null comment '备注',
    create_time        timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time        timestamp                            null comment '修改时间',
    constraint user_openid_uindex
        unique (openid)
)
    comment '用户信息' collate = utf8mb4_general_ci;

INSERT INTO user (id, username, nickname, password, salt, openid, phone, head, introduction, is_vip, vip_level, status, register_time, register_ip, register_ip_area, last_login_time, last_login_ip, last_login_ip_area, remark, create_time, update_time) VALUES (1, 'boot', 'boot用户', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304', '94132dcd55eb478bb63170a2fa510ea1', null, null, null, null, 1, 3, 1, '2023-11-25 00:00:00', null, null, '2023-12-04 12:42:03', '127.0.0.1', '内网IP', null, '2023-11-25 23:54:31', null);
INSERT INTO user (id, username, nickname, password, salt, openid, phone, head, introduction, is_vip, vip_level, status, register_time, register_ip, register_ip_area, last_login_time, last_login_ip, last_login_ip_area, remark, create_time, update_time) VALUES (2, 'geekidea', 'geekidea用户', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304', '94132dcd55eb478bb63170a2fa510ea1', null, null, null, null, 1, 4, 1, '2023-11-25 00:00:00', null, null, '2023-11-26 00:56:15', '127.0.0.1', '内网IP', null, '2023-11-25 23:54:31', null);

-- 示例
create table foo_bar
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(20)                          not null comment '名称',
    foo         varchar(100)                         null comment 'Foo',
    bar         varchar(100)                         null comment 'Bar',
    remark      varchar(200)                         null comment '备注',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间'
)
    comment 'FooBar' collate = utf8mb4_general_ci;

INSERT INTO foo_bar (id, name, foo, bar, remark, status, create_time, update_time) VALUES (1, 'FooBar', 'Foo', 'Bar', null, 1, '2023-07-01 21:01:10', null);


-- 演示
create table demo
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(20)                          not null comment '名称',
    remark      varchar(200)                         null comment '备注',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间'
)
    comment '演示';

INSERT INTO demo (id, name, remark, status, create_time, update_time) VALUES (1, '演示', null, 1, '2023-12-03 14:52:26', null);


-- 数据范围移动端测试
create table data_range_app_test
(
    id          bigint                              not null comment 'ID'
        primary key,
    name        varchar(20)                         not null comment '名称',
    user_id     bigint                              not null comment '用户ID',
    remark      varchar(200)                        null comment '备注',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间'
)
    comment '用户端数据权限测试' collate = utf8mb4_general_ci;

INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (1, '数据1', 1, null, '2023-12-02 17:12:56', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (2, '数据2', 1, null, '2023-12-02 17:12:56', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (3, '数据3', 1, null, '2023-12-02 17:12:57', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (4, '数据4', 1, null, '2023-12-02 17:12:57', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (5, '数据5', 2, null, '2023-12-02 17:12:57', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (6, '数据6', 2, null, '2023-12-02 17:12:57', null);
INSERT INTO data_range_app_test (id, name, user_id, remark, create_time, update_time) VALUES (488957102522373, '测试添加', 1, 'aa', '2023-12-02 17:51:34', null);

