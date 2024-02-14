# 1. maven打包
mvn clean package -Pdemo

# 2. 在Dockerfile文件目录下，构建docker镜像，spring-boot-plus为镜像名称
docker build -t spring-boot-plus .

# 3. 将镜像导出为tar
docker save spring-boot-plus -o spring-boot-plus.tar


