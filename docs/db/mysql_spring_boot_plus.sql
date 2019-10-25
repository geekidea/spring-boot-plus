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

-- ----------------------------
-- Create Database for spring_boot_plus
-- ----------------------------
create database if not exists spring_boot_plus character set utf8mb4;

-- ----------------------------
-- Table structure for ip
-- ----------------------------
DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip`  (
  `ip_start` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip_end` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `operator` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip_start_num` bigint(20) NOT NULL,
  `ip_end_num` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 526718 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'IP地址'  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(18) NOT NULL COMMENT '主键',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_id` bigint(18) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1060438746056376321, 0, 'A', 100000, '2018-11-08 15:41:58');
INSERT INTO `sys_log` VALUES (1060438788502732802, 0, 'B', 100000, '2018-11-08 15:42:08');
INSERT INTO `sys_log` VALUES (1060438799600861185, 0, 'C', 100000, '2018-11-08 15:42:10');
INSERT INTO `sys_log` VALUES (1060438809495224322, 0, 'D', 100000, '2018-11-08 15:42:13');

CREATE TABLE `sys_department` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `name` varchar(32) NOT NULL COMMENT '部门名称',
    `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
    `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_department_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门'


CREATE TABLE `sys_permission` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
    `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
    `url` varchar(200) DEFAULT NULL COMMENT '路径',
    `code` varchar(100) NOT NULL COMMENT '唯一编码',
    `icon` varchar(100) DEFAULT NULL COMMENT '图标',
    `type` int(11) NOT NULL COMMENT '类型，1：菜单，2：按钮',
    `level` int(11) NOT NULL COMMENT '层级，1：第一级，2：第二级，N：第N级',
    `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_permission_code_uindex` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统权限'

CREATE TABLE `sys_role` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `name` varchar(32) NOT NULL COMMENT '角色名称',
    `code` varchar(100) DEFAULT NULL COMMENT '角色唯一编码',
    `type` int(11) DEFAULT NULL COMMENT '角色类型',
    `state` int(11) NOT NULL DEFAULT '1' COMMENT '角色状态，0：禁用，1：启用',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_role_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统角色'

CREATE TABLE `sys_role_permission` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    `permission_id` bigint(20) NOT NULL COMMENT '权限id',
    `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关系'

CREATE TABLE `sys_user` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `username` varchar(20) NOT NULL COMMENT '用户名',
    `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
    `password` varchar(64) NOT NULL COMMENT '密码',
    `salt` varchar(32) DEFAULT NULL COMMENT '盐值',
    `phone` varchar(20) NOT NULL COMMENT '手机号码',
    `gender` int(11) NOT NULL DEFAULT '1' COMMENT '性别，0：女，1：男，默认1',
    `remark` varchar(200) DEFAULT NULL COMMENT 'remark',
    `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用，2：锁定',
    `department_id` bigint(20) NOT NULL COMMENT '部门id',
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    `deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0：未删除，1：已删除',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `sys_user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户'

