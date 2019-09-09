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
# 快速安装maven shell脚本
#
# author: chenkun
# date: 2019-08-30
#======================================================================

hasMaven(){
    MAVEN_VERSION=$(mvn -version)
    echo "${MAVEN_VERSION}"
    if [[ ! $MAVEN_VERSION ]]
    then
        return 0;
    fi
    return 1;
}

hasMaven

if [ $? != 1 ]
then
    echo "Not Found maven"
    echo "Installing maven..."

    # 下载 maven aliyun mirror settings.xml
    wget -O settings.xml https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/config/settings.xml

    yum install -y maven

    hasMaven
    if [ $? == 1 ]
    then
      echo "Config Aliyun Maven Mirror..."
      rm -rf /etc/maven/settings.xml
      cp settings.xml /etc/maven/
      mvn -version
    else
      echo "Install maven Fail"
    fi
fi

echo ""
