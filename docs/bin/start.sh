#! /bin/shell

# 服务路径
SERVER_PATH="/home/spring-boot-plus-server"
# jar包名称
JAR_NAME="spring-boot-plus.jar"
# 日志文件名称
LOG_NAME="nohup.log"
cd ${SERVER_PATH}
nohup java -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -XX:-OmitStackTraceInFastThrow -jar ${JAR_NAME} > ${LOG_NAME} 2>&1 &
echo "${JAR_NAME}启动成功"
tail -f -n 500 ${LOG_NAME}