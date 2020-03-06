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

drop database spring_boot_plus;
-- ----------------------------
-- Create Database for spring_boot_plus
-- ----------------------------
create database if not exists spring_boot_plus character set utf8mb4 COLLATE utf8mb4_general_ci;

use spring_boot_plus;

-- ----------------------------
-- Table structure for ip
-- ----------------------------
DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip`
(
    `ip_start`     varchar(15)  NOT NULL,
    `ip_end`       varchar(15)  NOT NULL,
    `area`         varchar(100) NOT NULL,
    `operator`     varchar(200) NOT NULL,
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
    `ip_start_num` bigint(20)   NOT NULL,
    `ip_end_num`   bigint(20)   NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 526718
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'IP地址';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `log_id`      bigint(18)   NOT NULL COMMENT '主键',
    `type`        tinyint(1)   NULL DEFAULT NULL COMMENT '类型',
    `content`     varchar(255) NULL DEFAULT NULL COMMENT '内容',
    `create_id`   bigint(18)   NULL DEFAULT NULL COMMENT '创建人ID',
    `create_time` datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1060438746056376321, 0, 'A', 100000, '2018-11-08 15:41:58');
INSERT INTO `sys_log` VALUES (1060438788502732802, 0, 'B', 100000, '2018-11-08 15:42:08');
INSERT INTO `sys_log` VALUES (1060438799600861185, 0, 'C', 100000, '2018-11-08 15:42:10');
INSERT INTO `sys_log` VALUES (1060438809495224322, 0, 'D', 100000, '2018-11-08 15:42:13');


-- ----------------------------
-- Table structure for foo_bar
-- ----------------------------
DROP TABLE IF EXISTS `foo_bar`;
CREATE TABLE `foo_bar`
(
    `id`            bigint(20)  NOT NULL COMMENT '主键',
    `name`          varchar(20) NOT NULL COMMENT '名称',
    `foo`           varchar(20)          DEFAULT NULL COMMENT 'Foo',
    `bar`           varchar(20) NOT NULL COMMENT 'Bar',
    `remark`        varchar(200)         DEFAULT NULL COMMENT '备注',
    `state`         int(11)     NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `version`       int(11)     NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time`   timestamp   NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp   NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='FooBar';

-- ----------------------------
-- Records of foo_bar
-- ----------------------------
INSERT INTO foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time)
VALUES (1, 'FooBar', 'foo', 'bar', 'remark...', 1, 0, '2019-11-01 14:05:14', null);
INSERT INTO foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time)
VALUES (2, 'HelloWorld', 'hello', 'world', null, 1, 0, '2019-11-01 14:05:14', null);


-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`
(
    `id`          bigint(20)  NOT NULL COMMENT '主键',
    `name`        varchar(32) NOT NULL COMMENT '部门名称',
    `parent_id`   bigint(20)           DEFAULT NULL COMMENT '父id',
    `level`       int         NULL COMMENT '部门层级',
    `state`       int(11)     NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `sort`        int(11)     NOT NULL DEFAULT '0' COMMENT '排序',
    `remark`      varchar(200)         DEFAULT NULL COMMENT '备注',
    `version`     int(11)     NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp   NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp   NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_department_name_uindex` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='部门';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (1, '管理部', null, 1, 1, 0, null, 0, '2019-10-25 09:46:49', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (2, '技术部', null, 1, 1, 0, null, 0, '2019-11-01 20:45:43', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (20, '前端开发部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (21, '后台开发部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (22, '测试部', 2, 2, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (201, '前端一组', 20, 3, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (202, '前端二组', 20, 3, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (203, '后台一组', 21, 3, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (204, '后台二组', 21, 3, 1, 0, null, 0, '2019-11-01 20:48:38', null);
INSERT INTO sys_department (id, name, parent_id, level, state, sort, remark, version, create_time, update_time) VALUES (205, '测试一组', 22, 3, 1, 0, null, 0, '2019-11-01 20:48:38', null);


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`            bigint(20)  NOT NULL COMMENT '主键',
    `username`      varchar(20) NOT NULL COMMENT '用户名',
    `nickname`      varchar(20)          DEFAULT NULL COMMENT '昵称',
    `password`      varchar(64) NOT NULL COMMENT '密码',
    `salt`          varchar(32)          DEFAULT NULL COMMENT '盐值',
    `phone`         varchar(20) NOT NULL COMMENT '手机号码',
    `gender`        int(11)     NOT NULL DEFAULT '1' COMMENT '性别，0：女，1：男，默认1',
    `head`          varchar(200) null comment '头像',
    `remark`        varchar(200)         DEFAULT NULL COMMENT 'remark',
    `state`         int(11)     NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用，2：锁定',
    `department_id` bigint(20)  NOT NULL COMMENT '部门id',
    `role_id`       bigint(20)  NOT NULL COMMENT '角色id',
    `deleted`       int(11)     NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version`       int(11)     NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time`   timestamp   NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp   NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_user_username_uindex` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user (id, username, nickname, password, salt, phone, gender, head, remark, state, department_id, role_id, deleted, version, create_time, update_time) VALUES (1, 'admin', '管理员', '11a254dab80d52bc4a347e030e54d861a9d2cdb2af2185a9ca4a7318e830d04d', '666', '', 1, 'http://localhost:8888//resource/201910281559227.jpg', 'Administrator Account', 1, 1, 1, 0, 1, '2019-08-26 00:52:01', '2019-10-27 23:32:29');
INSERT INTO sys_user (id, username, nickname, password, salt, phone, gender, head, remark, state, department_id, role_id, deleted, version, create_time, update_time) VALUES (2, 'test', '测试人员', '34783fb724b259beb71a1279f7cd93bdcfd92a273d566f926419a37825c500df', '087c2e9857f35f1e243367f3b89b81c1', '', 1, null, 'Tester Account', 1, 1, 2, 0, 0, '2019-10-05 14:04:27', null);


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20)  NOT NULL COMMENT '主键',
    `name`        varchar(32) NOT NULL COMMENT '角色名称',
    `code`        varchar(100)         DEFAULT NULL COMMENT '角色唯一编码',
    `type`        int(11)              DEFAULT NULL COMMENT '角色类型',
    `state`       int(11)     NOT NULL DEFAULT '1' COMMENT '角色状态，0：禁用，1：启用',
    `remark`      varchar(200)         DEFAULT NULL COMMENT '备注',
    `version`     int(11)     NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp   NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_role_name_uindex` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role (id, name, code, type, state, remark, version, create_time, update_time) VALUES (1, '管理员', 'admin', null, 1, null, 0, '2019-10-25 09:47:21', null);
INSERT INTO sys_role (id, name, code, type, state, remark, version, create_time, update_time) VALUES (2, 'test', 'test', null, 1, null, 0, '2019-10-25 09:48:02', null);


-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint(20)   NOT NULL COMMENT '主键',
    `name`        varchar(32)           DEFAULT NULL COMMENT '权限名称',
    `parent_id`   bigint(20)            DEFAULT NULL COMMENT '父id',
    `url`         varchar(200)          DEFAULT NULL COMMENT '路径',
    `code`        varchar(100) NOT NULL COMMENT '唯一编码',
    `icon`        varchar(100)          DEFAULT NULL COMMENT '图标',
    `type`        int(11)      NOT NULL COMMENT '类型，1：菜单，2：按钮',
    `level`       int(11)      NOT NULL COMMENT '层级，1：第一级，2：第二级，N：第N级',
    `state`       int(11)      NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `sort`        int(11)      NOT NULL DEFAULT '0' COMMENT '排序',
    `remark`      varchar(200)          DEFAULT NULL COMMENT '备注',
    `version`     int(11)      NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_permission_code_uindex` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='系统权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1, '系统管理', null, null, 'system:management', null, 1, 1, 1, 0, null, 0, '2019-10-26 11:12:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (100, '用户管理', 1, null, 'sys:user:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (200, '角色管理', 1, null, 'sys:role:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (300, '权限管理', 1, null, 'sys:permission:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (400, '部门管理', 1, null, 'sys:department:management', null, 1, 2, 1, 0, null, 0, '2019-10-26 11:15:48', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1000, '用户新增', 100, null, 'sys:user:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1001, '用户修改', 100, null, 'sys:user:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1002, '用户删除', 100, null, 'sys:user:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1003, '用户详情', 100, null, 'sys:user:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1004, '用户分页列表', 100, null, 'sys:user:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1005, '用户修改密码', 100, null, 'sys:user:update:password', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (1006, '用户修改头像', 100, null, 'sys:user:update:head', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2000, '角色新增', 200, null, 'sys:role:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2001, '角色修改', 200, null, 'sys:role:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2002, '角色删除', 200, null, 'sys:role:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2003, '角色详情', 200, null, 'sys:role:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (2004, '角色分页列表', 200, null, 'sys:role:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3000, '权限新增', 300, null, 'sys:permission:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3001, '权限修改', 300, null, 'sys:permission:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3002, '权限删除', 300, null, 'sys:permission:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3003, '权限详情', 300, null, 'sys:permission:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3004, '权限分页列表', 300, null, 'sys:permission:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3005, '权限所有列表', 300, null, 'sys:permission:all:menu:list', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3006, '权限所有树形列表', 300, null, 'sys:permission:all:menu:tree', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3007, '权限用户列表', 300, null, 'sys:permission:menu:list', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3008, '权限用户树形列表', 300, null, 'sys:permission:menu:tree', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (3009, '权限用户代码列表', 300, null, 'sys:permission:codes', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4000, '部门新增', 400, null, 'sys:department:add', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4001, '部门修改', 400, null, 'sys:department:update', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4002, '部门删除', 400, null, 'sys:department:delete', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4003, '部门详情', 400, null, 'sys:department:info', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);
INSERT INTO sys_permission (id, name, parent_id, url, code, icon, type, level, state, sort, remark, version, create_time, update_time) VALUES (4004, '部门分页列表', 400, null, 'sys:department:page', null, 2, 3, 1, 0, null, 0, '2019-10-26 11:18:40', null);


-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`            bigint(20) NOT NULL COMMENT '主键',
    `role_id`       bigint(20) NOT NULL COMMENT '角色id',
    `permission_id` bigint(20) NOT NULL COMMENT '权限id',
    `state`         int(11)    NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `remark`        varchar(200)        DEFAULT NULL COMMENT '备注',
    `version`       int(11)    NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp  NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色权限关系';

-- ------------------------------
-- Records of sys_role_permission
-- ------------------------------
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (1, 1, 1, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (2, 1, 100, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (3, 1, 200, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (4, 1, 300, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (5, 1, 400, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (6, 1, 1000, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (7, 1, 1001, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (8, 1, 1002, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (9, 1, 1003, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (10, 1, 1004, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (11, 1, 1005, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (12, 1, 1006, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (13, 1, 2000, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (14, 1, 2001, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (15, 1, 2002, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (16, 1, 2003, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (17, 1, 2004, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (18, 1, 3000, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (19, 1, 3001, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (20, 1, 3002, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (21, 1, 3003, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (22, 1, 3004, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (23, 1, 3005, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (24, 1, 3006, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (25, 1, 3007, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (26, 1, 3008, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (27, 1, 3009, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (28, 1, 4001, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (29, 1, 4002, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (30, 1, 4003, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (31, 1, 4004, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (100, 1, 1, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (101, 1, 100, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (102, 1, 1000, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (103, 1, 1001, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (104, 1, 1002, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (105, 1, 1003, 1, null, 0, '2019-10-26 22:16:19', null);
INSERT INTO sys_role_permission (id, role_id, permission_id, state, remark, version, create_time, update_time) VALUES (106, 1, 1004, 1, null, 0, '2019-10-26 22:16:19', null);

