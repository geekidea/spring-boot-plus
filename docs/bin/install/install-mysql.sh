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
# 快速安装MySQL
# CentOS7 中已成功验证
# 使用yum+rpm方式安装
#
# author: geekidea
# date: 2019-8-29
#======================================================================

# 下载mysql rpm
wget https://repo.mysql.com//mysql80-community-release-el7-3.noarch.rpm

# 安装rpm
rpm -Uvh mysql80-community-release-el7-3.noarch.rpm

# yum 安装mysql服务
yum install -y mysql-community-server

# 启动mysql服务
systemctl start mysqld.service

# 查看mysql服务状态
systemctl status mysqld.service

# 查看安装的mysql密码
grep 'temporary password' /var/log/mysqld.log

TEMP_PWD=$(grep 'temporary password' /var/log/mysqld.log)
PWD=${TEMP_PWD##* }
echo "${PWD}"

# 登录
mysql -uroot -p${PWD}

# 进入到mysql命令行时，修改密码
# 修改密码
# ALTER USER 'root'@'localhost' IDENTIFIED BY 'Springbootplus666!';

# 使用新密码登录
# exit
# mysql -uroot -pSpringbootplus666!

# 导入spring-boot-plus数据库脚本
# use mysql;
# source /root/mysql_spring_boot_plus.sql;
