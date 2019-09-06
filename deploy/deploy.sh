#! /bin/shell

# Copyright 2019-2029 geekidea(https://github.com/geekidea)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#======================================================================
# 1. 下载或更新spring-boot-plus版本库
# 2. maven打包
# 3. 运行spring-boot-plus
# 4. 访问项目
# author: geekidea
# date: 2019-9-6
#======================================================================

# 1. 下载或更新spring-boot-plus版本库
# 先判断当前目录下是否有spring-boot-plus目录
# 如果有，则执行git pull
# 如果没有，则clone
git clone git@github.com:geekidea/spring-boot-plus.git

# 2. maven打包
mvn clean package -Ptest

# 3. 运行spring-boot-plus
cd cd spring-boot-plus/target
tar -zxvf spring-boot-plus-1.2.2.RELEASE-local.tar.gz
cd spring-boot-plus
sh bin/restart.sh

# 4. 访问项目
# 输出项目日志
# http://localhost:8888/docs

