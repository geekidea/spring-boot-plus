#! /bin/shell

# 停服
APPLICATION="spring-boot-plus.jar"
PID=$(ps -ef | grep ${APPLICATION} | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo ${APPLICATION} is already stopped
else
    echo kill  ${PID}
    kill -9 ${PID}
    echo ${APPLICATION} stopped successfully
fi