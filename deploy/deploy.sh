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

NOW=$(date --date='0 days ago' "+%Y-%m-%d-%H-%M-%S")
echo "${NOW}"

# 1. 下载或更新spring-boot-plus版本库
# 先判断当前目录下是否有spring-boot-plus目录
# 如果有，则执行git pull
# 如果没有，则clone
if [ ! -d "spring-boot-plus" ]; then
  git clone https://github.com/geekidea/spring-boot-plus.git
else
  cd spring-boot-plus
  git pull
fi

# 2. maven打包
mvn clean package -Ptest

# 3. 停服
cd ..

if [ -d "spring-boot-plus-server" ]; then
  sh spring-boot-plus-server/bin/shutdown.sh
fi

# 4. 复制spring-boot-plus-server-assembly.tar.gz到项目同级目录下
# 备份之前的server

if [ ! -d "spring-boot-plus-server-back" ]; then
  mkdir spring-boot-plus-server-back
fi
mv spring-boot-plus-server spring-boot-plus-server-back/spring-boot-plus-server-back-"${NOW}"

cp spring-boot-plus/target/spring-boot-plus-server-assembly.tar.gz spring-boot-plus-server-assembly.tar.gz

# 5. 运行spring-boot-plus
tar -zxvf spring-boot-plus-server-assembly.tar.gz
cd spring-boot-plus-server/bin
sh restart.sh

# 6. 访问项目
# 输出项目日志
# http://localhost:8888/docs

