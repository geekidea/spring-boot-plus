#! /bin/shell

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
