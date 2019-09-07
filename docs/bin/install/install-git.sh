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
# 快速安装GIT
# CentOS7 中已成功验证
# 使用yum方式安装
#
# author: sy
# date: 2019-8-29
#======================================================================

hasGit(){
    GIT_VERSION=$(git --version)
    echo "${GIT_VERSION}"
    if [[ $GIT_VERSION == *version* ]]
    then
        return 1;
    fi
    return 0;
}

hasGit

if [ $? != 1 ]
then
    echo "Not Found git"
    echo "Installing Git..."
    yum install -y git
    hasGit
    if [ $? != 1 ]
    then
      echo "Install maven Fail"
    fi
fi

echo ""
