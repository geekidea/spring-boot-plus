/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

create table foo_bar
(
    id          bigint identity
        constraint foo_bar_pk
        primary key nonclustered,
    name        varchar(20)                not null,
    foo         varchar(20),
    bar         varchar(20)                not null,
    remark      varchar(200),
    state       int      default 1         not null,
    version     int      default 0         not null,
    create_time datetime default getdate() not null,
    update_time datetime
) go

exec sp_addextendedproperty 'MS_Description', 'FooBar', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '名称', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', 'Foo', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'foo'
go

exec sp_addextendedproperty 'MS_Description', 'Bar', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'bar'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '状态，0：禁用，1：启用', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'foo_bar', 'COLUMN', 'update_time'
go

create table ip
(
    id           bigint identity
        constraint ip_pk
        primary key nonclustered,
    ip_start     varchar(15)                not null,
    ip_end       varchar(15)                not null,
    area         varchar(100)               not null,
    operator     varchar(200)               not null,
    ip_start_num bigint                     not null,
    ip_end_num   bigint                     not null,
    create_time  datetime default getdate() not null
) go

exec sp_addextendedproperty 'MS_Description', 'IP', 'SCHEMA', 'dbo', 'TABLE', 'ip'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'ip', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'ip', 'COLUMN', 'create_time'
go

create table sys_department
(
    id          bigint identity
        constraint sys_department_pk
        primary key nonclustered,
    name        varchar(32)                not null,
    parent_id   bigint,
    level       int,
    state       int      default 1         not null,
    sort        int      default 0         not null,
    remark      varchar(200),
    version     int      default 0         not null,
    create_time datetime default getdate() not null,
    update_time datetime
) go

exec sp_addextendedproperty 'MS_Description', '部门', 'SCHEMA', 'dbo', 'TABLE', 'sys_department'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '部门名称', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', '父id', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'parent_id'
go

exec sp_addextendedproperty 'MS_Description', '部门层级', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'level'
go

exec sp_addextendedproperty 'MS_Description', '状态，0：禁用，1：启用', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'sort'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_department', 'COLUMN', 'update_time'
go

create table sys_log
(
    log_id      bigint identity
        constraint sys_log_pk
        primary key nonclustered,
    type        smallint,
    content     varchar(255),
    create_id   bigint,
    create_time datetime default getdate() not null
) go

exec sp_addextendedproperty 'MS_Description', '系统日志', 'SCHEMA', 'dbo', 'TABLE', 'sys_log'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_log', 'COLUMN', 'log_id'
go

exec sp_addextendedproperty 'MS_Description', '类型', 'SCHEMA', 'dbo', 'TABLE', 'sys_log', 'COLUMN', 'type'
go

exec sp_addextendedproperty 'MS_Description', '内容', 'SCHEMA', 'dbo', 'TABLE', 'sys_log', 'COLUMN', 'content'
go

exec sp_addextendedproperty 'MS_Description', '创建人ID', 'SCHEMA', 'dbo', 'TABLE', 'sys_log', 'COLUMN', 'create_id'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_log', 'COLUMN', 'create_time'
go

create table sys_permission
(
    id          bigint identity
        constraint sys_permission_pk
        primary key nonclustered,
    name        varchar(32),
    parent_id   bigint,
    url         varchar(200),
    code        varchar(100)               not null,
    icon        varchar(100),
    type        int                        not null,
    level       int                        not null,
    state       int      default 1         not null,
    sort        int      default 0         not null,
    remark      varchar(200),
    version     int      default 0         not null,
    create_time datetime default getdate() not null,
    update_time datetime
) go

exec sp_addextendedproperty 'MS_Description', '系统权限', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '权限名称', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', '父id', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'parent_id'
go

exec sp_addextendedproperty 'MS_Description', '路径', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'url'
go

exec sp_addextendedproperty 'MS_Description', '唯一编码', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'code'
go

exec sp_addextendedproperty 'MS_Description', '图标', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'icon'
go

exec sp_addextendedproperty 'MS_Description', '类型，1：菜单，2：按钮', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'type'
go

exec sp_addextendedproperty 'MS_Description', '层级，1：第一级，2：第二级，N：第N级', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'level'
go

exec sp_addextendedproperty 'MS_Description', '状态，0：禁用，1：启用', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '排序', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'sort'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_permission', 'COLUMN', 'update_time'
go

create table sys_role
(
    id          bigint identity
        constraint sys_role_pk
        primary key nonclustered,
    name        varchar(32)                not null,
    code        varchar(100),
    type        int,
    state       int      default 1         not null,
    remark      varchar(200),
    version     int      default 0         not null,
    create_time datetime default getdate() not null,
    update_time datetime
) go

exec sp_addextendedproperty 'MS_Description', '系统角色', 'SCHEMA', 'dbo', 'TABLE', 'sys_role'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '角色名称', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'name'
go

exec sp_addextendedproperty 'MS_Description', '角色唯一编码', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'code'
go

exec sp_addextendedproperty 'MS_Description', '角色类型', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'type'
go

exec sp_addextendedproperty 'MS_Description', '角色状态，0：禁用，1：启用', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_role', 'COLUMN', 'update_time'
go

create table sys_role_permission
(
    id            bigint identity
        constraint sys_role_permission_pk
        primary key nonclustered,
    role_id       bigint                     not null,
    permission_id bigint                     not null,
    state         int      default 1         not null,
    remark        varchar(200),
    version       int      default 0         not null,
    create_time   datetime default getdate() not null,
    update_time   datetime
) go

exec sp_addextendedproperty 'MS_Description', '角色权限', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '角色id', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'role_id'
go

exec sp_addextendedproperty 'MS_Description', '权限id', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'permission_id'
go

exec sp_addextendedproperty 'MS_Description', '状态，0：禁用，1：启用', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_role_permission', 'COLUMN', 'update_time'
go

create table sys_user
(
    id            bigint identity
        constraint sys_user_pk
        primary key nonclustered,
    username      varchar(20)                not null,
    nickname      varchar(20),
    password      varchar(64)                not null,
    salt          varchar(32),
    phone         varchar(20)                not null,
    gender        int      default 1         not null,
    head          varchar(200),
    remark        varchar(200),
    state         int      default 1         not null,
    department_id bigint                     not null,
    role_id       bigint                     not null,
    deleted       int      default 0         not null,
    version       int      default 0         not null,
    create_time   datetime default getdate() not null,
    update_time   datetime
) go

exec sp_addextendedproperty 'MS_Description', '系统用户', 'SCHEMA', 'dbo', 'TABLE', 'sys_user'
go

exec sp_addextendedproperty 'MS_Description', '主键', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'id'
go

exec sp_addextendedproperty 'MS_Description', '用户名', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'username'
go

exec sp_addextendedproperty 'MS_Description', '昵称', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'nickname'
go

exec sp_addextendedproperty 'MS_Description', '密码', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'password'
go

exec sp_addextendedproperty 'MS_Description', '盐值', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'salt'
go

exec sp_addextendedproperty 'MS_Description', '手机号码', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'phone'
go

exec sp_addextendedproperty 'MS_Description', '性别，0：女，1：男，默认1', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'gender'
go

exec sp_addextendedproperty 'MS_Description', '头像', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'head'
go

exec sp_addextendedproperty 'MS_Description', '备注', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'remark'
go

exec sp_addextendedproperty 'MS_Description', '状态，0：禁用，1：启用，2：锁定', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'state'
go

exec sp_addextendedproperty 'MS_Description', '部门id', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'department_id'
go

exec sp_addextendedproperty 'MS_Description', '角色id', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'role_id'
go

exec sp_addextendedproperty 'MS_Description', '逻辑删除，0：未删除，1：已删除', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'deleted'
go

exec sp_addextendedproperty 'MS_Description', '版本', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'version'
go

exec sp_addextendedproperty 'MS_Description', '创建时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'create_time'
go

exec sp_addextendedproperty 'MS_Description', '修改时间', 'SCHEMA', 'dbo', 'TABLE', 'sys_user', 'COLUMN', 'update_time'
go



set identity_insert spring_boot_plus.dbo.foo_bar on
INSERT INTO spring_boot_plus.dbo.foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time) VALUES (1, 'FooBar', 'foo', 'bar', 'remark...', 1, 0, '2019-11-01 14:05:14.000', null);
INSERT INTO spring_boot_plus.dbo.foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time) VALUES (2, 'HelloWorld', 'hello', 'world', null, 1, 0, '2019-11-01 14:05:14.000', null);
set identity_insert spring_boot_plus.dbo.foo_bar off


set identity_insert spring_boot_plus.dbo.sys_department on
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (1, '管理部', null, 1, 1, 0, null, 0, '2019-10-25 09:46:49.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (2, '技术部', null, 1, 1, 0, null, 0, '2019-11-01 20:45:43.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (20, '前端开发部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (21, '后台开发部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (22, '测试部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (201, '前端一组', 20, 3, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (202, '前端二组', 20, 3, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (203, '后台一组', 21, 3, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (204, '后台二组', 21, 3, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
INSERT INTO spring_boot_plus.dbo.sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (205, '测试一组', 22, 3, 1, 0, null, 0, '2019-11-01 20:48:38.000', null);
set identity_insert spring_boot_plus.dbo.sys_department off


set identity_insert spring_boot_plus.dbo.sys_permission on
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1, '系统管理', null, null, 'system:management', null, 1, 1, 1, 0, null, 0, '2019-10-26 11:12:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (100, '用户管理', 1, null, 'sys:user:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (200, '角色管理', 1, null, 'sys:role:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (300, '权限管理', 1, null, 'sys:permission:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (400, '部门管理', 1, null, 'sys:department:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1000, '用户新增', 100, null, 'sys:user:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1001, '用户修改', 100, null, 'sys:user:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1002, '用户删除', 100, null, 'sys:user:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1003, '用户详情', 100, null, 'sys:user:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1004, '用户分页列表', 100, null, 'sys:user:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1005, '用户修改密码', 100, null, 'sys:user:update:password', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1006, '用户修改头像', 100, null, 'sys:user:update:head', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2000, '角色新增', 200, null, 'sys:role:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2001, '角色修改', 200, null, 'sys:role:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2002, '角色删除', 200, null, 'sys:role:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2003, '角色详情', 200, null, 'sys:role:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2004, '角色分页列表', 200, null, 'sys:role:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3000, '权限新增', 300, null, 'sys:permission:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3001, '权限修改', 300, null, 'sys:permission:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3002, '权限删除', 300, null, 'sys:permission:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3003, '权限详情', 300, null, 'sys:permission:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3004, '权限分页列表', 300, null, 'sys:permission:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3005, '权限所有列表', 300, null, 'sys:permission:all:menu:list', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3006, '权限所有树形列表', 300, null, 'sys:permission:all:menu:tree', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3007, '权限用户列表', 300, null, 'sys:permission:menu:list', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3008, '权限用户树形列表', 300, null, 'sys:permission:menu:tree', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3009, '权限用户代码列表', 300, null, 'sys:permission:codes', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4000, '部门新增', 400, null, 'sys:department:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4001, '部门修改', 400, null, 'sys:department:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4002, '部门删除', 400, null, 'sys:department:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4003, '部门详情', 400, null, 'sys:department:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
INSERT INTO spring_boot_plus.dbo.sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4004, '部门分页列表', 400, null, 'sys:department:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40.000', null);
set identity_insert spring_boot_plus.dbo.sys_permission off


set identity_insert spring_boot_plus.dbo.sys_role on
INSERT INTO spring_boot_plus.dbo.sys_role (id, name, code, type, state, remark, version, create_time, update_time) VALUES (1, '管理员', 'admin', null, 1, null, 0, '2019-10-25 09:47:21.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role (id, name, code, type, state, remark, version, create_time, update_time) VALUES (2, 'test', 'test', null, 1, null, 0, '2019-10-25 09:48:02.000', null);
set identity_insert spring_boot_plus.dbo.sys_role off


set identity_insert spring_boot_plus.dbo.sys_role_permission on
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (1, 1, 1, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (2, 1, 100, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (3, 1, 200, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (4, 1, 300, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (5, 1, 400, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (6, 1, 1000, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (7, 1, 1001, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (8, 1, 1002, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (9, 1, 1003, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (10, 1, 1004, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (11, 1, 1005, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (12, 1, 1006, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (13, 1, 2000, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (14, 1, 2001, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (15, 1, 2002, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (16, 1, 2003, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (17, 1, 2004, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (18, 1, 3000, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (19, 1, 3001, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (20, 1, 3002, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (21, 1, 3003, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (22, 1, 3004, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (23, 1, 3005, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (24, 1, 3006, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (25, 1, 3007, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (26, 1, 3008, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (27, 1, 3009, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (28, 1, 4001, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (29, 1, 4002, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (30, 1, 4003, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (31, 1, 4004, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (100, 1, 1, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (101, 1, 100, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (102, 1, 1000, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (103, 1, 1001, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (104, 1, 1002, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (105, 1, 1003, 1, null, 0, '2019-10-26 22:16:19.000', null);
INSERT INTO spring_boot_plus.dbo.sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (106, 1, 1004, 1, null, 0, '2019-10-26 22:16:19.000', null);
set identity_insert spring_boot_plus.dbo.sys_role_permission off


set identity_insert spring_boot_plus.dbo.sys_user on
INSERT INTO spring_boot_plus.dbo.sys_user (id, username, nickname, password, salt, phone, gender, head, remark, state, department_id, role_id, deleted, version, create_time, update_time) VALUES (1, 'admin', '管理员', '11a254dab80d52bc4a347e030e54d861a9d2cdb2af2185a9ca4a7318e830d04d', '666', '', 1, 'http://localhost:8888//resource/201910281559227.jpg', 'Administrator Account', 1, 1, 1, 0, 1, '2019-08-26 00:52:01.000', '2019-10-27 23:32:29.000');
INSERT INTO spring_boot_plus.dbo.sys_user (id, username, nickname, password, salt, phone, gender, head, remark, state, department_id, role_id, deleted, version, create_time, update_time) VALUES (2, 'test', '测试人员', '34783fb724b259beb71a1279f7cd93bdcfd92a273d566f926419a37825c500df', '087c2e9857f35f1e243367f3b89b81c1', '', 1, null, 'Tester Account', 1, 1, 2, 0, 0, '2019-10-05 14:04:27.000', null);
set identity_insert spring_boot_plus.dbo.sys_user off