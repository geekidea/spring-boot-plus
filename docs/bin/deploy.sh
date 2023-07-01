#! /bin/shell

# 当前时间
NOW=$(date --date='0 days ago' "+%Y-%m-%d-%H-%M-%S")
echo "${NOW}"
# 变量配置
# 代码更新结果
PULL_RESULT=""
IS_UPDATE=0
# 服务路径，注意需在服务器上创建如下服务目录，然后将deploy.sh、start.sh、shutdown.sh放到其目录下
SERVER_PATH="/home/spring-boot-plus-server"
# GIT项目名称
PROJECT_NAME="spring-boot-plus"
# 版本库路径
REPO_URL="git@gitee.com:geekidea/spring-boot-plus.git"
# jar包名称
JAR_NAME="spring-boot-plus.jar"
# 日志文件名称
LOG_NAME="nohup.log"
# 备份jar目录
BACK_JAR_DIR="back-jar"
# 备份jar目录
BACK_LOG_DIR="back-log"
# 发布的GIT分支名称
DEPLOY_BRANCH=master
# 发布的Maven Profile
ACTIVE_PROFILE=test

# 进入到服务目录
cd ${SERVER_PATH}
pwd

# 1. 下载或更新版本库
# 先判断当前目录下是否有源代码目录
# 如果有，则执行git pull
# 如果没有，则clone
if [ ! -d "${PROJECT_NAME}" ]; then
  git clone ${REPO_URL}
  cd ${PROJECT_NAME}
else
  cd ${PROJECT_NAME}
  # 拉取代码，并获取结果判断，是否有新的代码更新，如果有，则备份之前的server，否则替换
  PULL_RESULT=$(git pull)
  echo "${PULL_RESULT}"

  if [[ ! $PULL_RESULT == *up-to-date* ]]
  then
    echo "update code..."
    IS_UPDATE=1
  fi
fi

pwd

git checkout ${DEPLOY_BRANCH}
git branch

# 2. maven打包
mvn clean install -P${ACTIVE_PROFILE}

pwd
# 判断是否生成成功
if [ ! -f "target/${JAR_NAME}" ]; then
  echo "maven build fail"
  exit
fi
echo "maven build success"

# 3. 停服
cd ${SERVER_PATH}
pwd

sh shutdown.sh

# 4. 复制jar和日志文件到back目录下
if [ ! -d "${BACK_JAR_DIR}" ]; then
  mkdir ${BACK_JAR_DIR}
fi
if [ ! -d "${BACK_LOG_DIR}" ]; then
  mkdir ${BACK_LOG_DIR}
fi

# 备份jar包
if [ -f "${JAR_NAME}" ]; then
  echo "back ${JAR_NAME}..."
  mv ${JAR_NAME} ${BACK_JAR_DIR}/${JAR_NAME}"-${NOW}"
  echo "back ${JAR_NAME} success"
fi

# 备份日志
if [ -f "${LOG_NAME}" ]; then
  echo "back ${LOG_NAME}..."
  mv ${LOG_NAME} ${BACK_LOG_DIR}/${LOG_NAME}"-${NOW}"
  echo "back ${LOG_NAME} success"
fi

# 5. 复制打包后的jar到运行目录
echo "copy ${SERVER_PATH}/${PROJECT_NAME}/target/${JAR_NAME}..."
# 复制到项目同级目录，如果有，则覆盖
cp -r -f ${PROJECT_NAME}/target/${JAR_NAME} .
echo "copy ${JAR_NAME} success"

pwd
# 6. 运行jar
sh start.sh

