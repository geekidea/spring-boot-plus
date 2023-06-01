#! /bin/shell

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
