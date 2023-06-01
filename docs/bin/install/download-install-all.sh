#! /bin/shell

# 下载脚本
wget -O install-all.sh https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-all.sh
wget -O install-jdk.sh  https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-jdk.sh
wget -O install-git.sh  https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-git.sh
wget -O install-maven.sh  https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-maven.sh
wget -O install-redis.sh  https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-redis.sh
wget -O install-mysql.sh  https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-mysql.sh
wget -O mysql_spring_boot_plus.sql https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/db/mysql_spring_boot_plus.sql

# 执行安装所有
sh install-all.sh