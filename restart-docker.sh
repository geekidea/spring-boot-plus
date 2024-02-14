# 以下在服务器上操作
# 1. 停止容器
docker stop spring-boot-plus-container
# 2. 删除容器
docker rm spring-boot-plus-container
# 3. 删除镜像
docker rmi spring-boot-plus
# 4. 加载镜像
docker load -i spring-boot-plus.tar
# 5. 服务器启动docker，将日志打印到外部目录
# 映射外部日志目录和外部文件上传目录
# docker run -p 8888:8888 -d  -v /Users/mrliu/geekidea/spring-boot-plus/docker-logs:/spring-boot-plus/logs -v /Users/mrliu/geekidea/spring-boot-plus/docker-upload:/home/spring-boot-plus/upload  --name spring-boot-plus-container spring-boot-plus
# 直接运行
docker run -p 8888:8888 -d  --name spring-boot-plus-container spring-boot-plus
# 6. 查看运行情况
docker ps -a -f name=spring-boot-plus-container
# 7. 查看指定名称的镜像
docker images spring-boot-plus
# 8. 查看运行日志
# 实时查看最新前500行日志
docker logs -f -t --tail 500 spring-boot-plus-container
# 查看物理主机上映射的日志
#tail -fn 500 /Users/mrliu/geekidea/spring-boot-plus/docker-logs/boot-info.log
#将日志全部导出到宿主机指定物理文件
#docker logs spring-boot-plus-container >> /Users/mrliu/geekidea/spring-boot-plus/docker-logs/spring-boot-plus.log
# 9. 访问项目，本地运行docker可以用localhost访问，服务器运行docker需将localhost该成docker物理机ip
# 访问后端接口文档
# http://localhost:8888/api/doc.html#/home



