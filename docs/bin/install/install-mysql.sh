#! /bin/shell

# 更改mysql源，设置为mysql
yum install -y https://repo.mysql.com//mysql80-community-release-el7-7.noarch.rpm

# 安装mysql
yum install -y mysql-server

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

# 以下操作需要在mysql命令中执行，密码需自行修改！！！
# 进入到mysql命令行时，修改密码
# 修改密码
# ALTER USER 'root'@'localhost' IDENTIFIED BY 'Springbootplus666!';

# 使用新密码登录
# exit
# mysql -uroot -pSpringbootplus666!

# 导入spring-boot-plus数据库脚本
# use mysql;
# source /root/mysql_spring_boot_plus.sql;

# 修改密码
# ALTER USER USER() IDENTIFIED BY 'Springbootplus666!';
# FLUSH PRIVILEGES;
# 退出重新登录mysql

# 设置mysql外部访问
# use mysql;
# select host,user from user;
# update user set host='%' where user ='root';
# FLUSH PRIVILEGES;
# GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION;
# FLUSH PRIVILEGES;
