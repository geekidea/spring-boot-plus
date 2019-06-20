## heroku免费发布spring boot 应用

### 1. 进入官网注册 https://signup.heroku.com/
> 注册时的验证码需科学上网

### 2. heroku使用教程 https://devcenter.heroku.com/articles/getting-started-with-java#set-up
1. 安装heroku cli
> 以macOS为例:
```bash
brew install heroku/brew/heroku
```
或者下载 https://cli-assets.heroku.com/heroku.pkg
2. heroku cli 登录
```bash
heroku login
```
> - 输入账号或密码
> - 如果浏览器登录，会提示跳转到浏览器进行授权登录

3. 创建项目

4. cd 本地git项目目录
```bash
cd /opt/github/spring-boot-plus
git add .
$ git commit -m "提交"
```
将本地代码提交到heroku远程仓库
```bash
heroku git:remote -a spring-boot-plus
```
```bash
git push heroku master
```



#### 查看heroku日志
```bash
heroku logs --tail
```