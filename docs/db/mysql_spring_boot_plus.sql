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
) ENGINE = InnoDB AUTO_INCREMENT = 526718 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
drop table if exists `sys_user`;
create table sys_user
(
    id          bigint                              not null comment '主键'
        primary key,
    username    varchar(20)                         not null comment '用户名',
    nickname    varchar(20)                         null comment '昵称',
    password    varchar(64)                         not null comment '密码',
    salt        varchar(32)                         null comment '盐值',
    remark      varchar(200)                        null comment 'remark',
    status      int       default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间',
    constraint sys_user_username_uindex
        unique (username)
)
    comment 'SystemUser';


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, status, create_time, update_time)
    VALUES (1, 'admin', '管理员', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', 'e4cc3292e3ebc483998adb2c0e4e640e', 'Administrator Account', 1, '2019-08-26 00:52:01', null);
INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, status, create_time, update_time)
    VALUES (2, 'test', '测试人员', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', '99952b31c18156169a26bec80fd211f6', 'Tester Account', 1, '2019-10-05 14:04:27', null);
