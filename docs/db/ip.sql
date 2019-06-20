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
-- Table structure for ip
-- ----------------------------
DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip` (
    `ip_start` varchar(15) NOT NULL,
    `ip_end` varchar(15) NOT NULL,
    `area` varchar(100) NOT NULL,
    `operator` varchar(200) NOT NULL,
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `ip_start_num` bigint(20) NOT NULL,
    `ip_end_num` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=526718 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
