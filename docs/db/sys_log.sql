-- Copyright 2019-2029 geekidea(https://github.com/geekidea)
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` bigint(18) NOT NULL COMMENT '主键',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_id` bigint(18) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1060438746056376321, 0, 'A', 100000, '2018-11-08 15:41:58');
INSERT INTO `sys_log` VALUES (1060438788502732802, 0, 'B', 100000, '2018-11-08 15:42:08');
INSERT INTO `sys_log` VALUES (1060438799600861185, 0, 'C', 100000, '2018-11-08 15:42:10');
INSERT INTO `sys_log` VALUES (1060438809495224322, 0, 'D', 100000, '2018-11-08 15:42:13');
INSERT INTO `sys_log` VALUES (1060438823319650306, 0, 'E', 100000, '2018-11-08 15:42:16');
INSERT INTO `sys_log` VALUES (1060438833750884353, 0, 'F', 100000, '2018-11-08 15:42:18');
INSERT INTO `sys_log` VALUES (1060439062743166977, 0, '1111111111', 100000, '2018-11-08 15:43:13');
INSERT INTO `sys_log` VALUES (1060439085228830721, 1, 'test redis lock ffbb79f6-9efe-4608-b204-fde5279b107f', 100000, '2018-11-16 16:46:35');
INSERT INTO `sys_log` VALUES (1068528405778444290, NULL, NULL, NULL, '2018-11-30 23:33:21');
INSERT INTO `sys_log` VALUES (1068528405778444291, NULL, NULL, NULL, '2018-11-30 23:33:21');
