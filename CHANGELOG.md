# 更新日志 CHANGELOG



## [V1.4.0] 2019.11.5  🚔
> spring-boot-plus集成用户角色权限部门管理

###  ⭐️  New Features
- 两种方式获取验证码
- 用户角色权限部门功能
- 同时生成多张表代码

### ⚡️ Optimization
- 自定义是否启用Shiro权限拦截
- 优化LogAop配置
- 代码生成模板优化，三种生成策略
- 自定义生成Shiro `RequiresPermissions`注解
- `Jackson`工具类，按字段顺序格式化输出
- `BaseEnum` 枚举父接口，`EnumController`,`BaseEnumUtil`, `EnumTypeValidator` 校验/获取枚举信息
	
### 🐞  Bug Fixes
- fix #81 刷新token问题 
- fix 获取角色token问题
- fix refreshToken方法和LoginUtil工具类方法问题

### 📔  Documentation
- [RBAC用户角色权限](https://springboot.plus/guide/rbac.html)

### 🔨 Dependency Upgrades
- Upgrade to `spring-boot` 2.2.0.RELEASE
- Upgrade to `spring-boot-admin` 2.2.0-SNAPSHOT
- Upgrade to `lombok` 1.18.10
- Upgrade to `hutool` 5.0.3
- Upgrade to `mapstruct` 1.3.1.Final
- Upgrade to `hutool` 5.0.4


## [V1.3.1-RELEASE] 2019.10.15

###  ⭐️  New Features
- Xss跨站脚本工具处理
- CORS跨域配置

### ⚡️ Optimization
- 代码生成器可自定义配置生成哪些文件
- 请求路径filter配置，配置文件属性名称调整
- Aop切点优化，`Aop` JSON参数输出优化
- 可配置是否生成`Validation`验证代码
- 优化`controller`,`entity`模版生成
- 优化代码生成器 CodeGenerator
- 调整 `aop`, `filter`,`interceptor`,`controller`,`param`,`vo`代码目录结构

### 📝 Added/Modified
- Add `XssFilter`,`XssHttpServletRequestWrapper`,`XssJacksonDeserializer`,`XssJacksonSerializer`
- Add `SpringBootPlusCorsProperties`
- Update `JacksonConfig`
- Update `LogAop`,`RequestPathFilter`,`ShiroConfig`

### 🐞  Bug Fixes
- fix druid控制面板无法访问问题

### 📔  Documentation
- [https://springboot.plus/guide/xss.html](https://springboot.plus/guide/xss.html)
- [https://springboot.plus/guide/cors.html](https://springboot.plus/guide/cors.html)

### 🔨 Dependency Upgrades
- Upgrade to `spring-boot` 2.1.9.RELEASE
- Upgrade to `Fastjson` 1.2.62
- Upgrade to `hutool` 4.6.10
- Add `commons-text` 1.8

## [V1.3.0-RELEASE] 2019.10.06

###  ⭐️  New Features
- 集成Apache Shiro安全框架
- 集成JWT 跨域身份验证解决方案

### ⚡️ Optimization
- 优化代码生成模块格式
- mybatis控制台打印SQL执行语句及结果集
- Redis使用Jackson序列化 `RedisTemplateConfig` `GenericJackson2JsonRedisSerializer`
- 删除security目录	
- [修改sys_user表结构](https://github.com/geekidea/spring-boot-plus/blob/master/docs/db/mysql_spring_boot_plus.sql)
	
### 📝 Added/Modified
- Add `SpringBootPlusFilterConfig` 过滤器配置类
- Add `SpringBootPlusException`,`DaoException` 自定义异常类
- Add `ShiroConfig`, `ShiroPermissionConfig`, `ShiroProperties` Shiro配置类
- Add `JwtCredentialsMatcher`, `JwtFilter`, `JwtProperties`, `JwtRealm`, `JwtToken` JWT配置
- Add `LoginRedisService`, `LoginRedisServiceImpl` Redis登录缓存处理

### 🐞  Bug Fixes
- fix startup.sh启动jar指定logback.xml

### 📔  Documentation
- [https://springboot.plus/guide/shiro-jwt.html](https://springboot.plus/guide/shiro-jwt.html)

### 🔨 Dependency Upgrades
- Upgrade to `Fastjson` 1.2.61
- Add `shiro-spring-boot-starter` `1.4.1`
- Add `java-jwt` `3.8.3`
- Add `mapstruct`, `mapstruct-processor` `1.3.0.Final` 对象属性复制
- Add `ini4j` `0.5.4` ini格式文件处理

## [V1.2.3-RELEASE] 2019.09.09 :computer: 
> spring-boot-plusV1.2.3发布，CentOS快速安装环境/构建/部署/启动项目

###  ⭐️  New Features
- 项目运行环境安装脚本
- CentOS快速构建/部署/启动项目脚本

### ⚡️ Optimization
- 优化 `maven-assembly-plugin` 项目打包插件
	
### 📝 Added/Modified
- Add [install-jdk.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-jdk.sh) yum安装`jdk8`脚本
- Add [install-git.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-git.sh) yum安装`git`脚本
- Add [install-maven.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-maven.sh) yum安装`maven`脚本
- Add [install-redis.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-redis.sh) yum安装`redis`脚本
- Add [install-mysql.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-mysql.sh) yum安装`mysql`脚本
- Add [install-all.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/install-all.sh) 安装所有环境脚本
- Add [download-install-all.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/download-install-all.sh) 下载并安装所有环境脚本
- Add [deploy.sh](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/deploy/deploy.sh) 下载项目/构建/部署/启动项目脚本

- Add `maven-javadoc-plugin` java api docs

### 📔  Documentation
- [CentOS Quick Installation Environment / Build / Deploy / Launch Spring-boot-plus Project](https://github.com/geekidea/spring-boot-plus#centos-quick-installation-environment--build--deploy--launch-spring-boot-plus-project)
- [spring-boot-plus java docs](http://geekidea.io/spring-boot-plus-apidocs/)

### 🔨 Dependency Upgrades
- Upgrade to `springboot` 2.1.9.RELEASE
- Upgrade to `Mybatis` 3.5.2
- Upgrade to `Mybatis Plus` 3.2.0
- Upgrade to `Alibaba Druid` 1.1.20
- Upgrade to `Fastjson` 1.2.60
- Upgrade to `commons-codec` 1.13
- Upgrade to `commons-collections` 4.4
- Upgrade to `hutool-all` 4.6.4


## [V1.2.2-RELEASE] 2019.08.26 🏇 
###  ⭐️  New Features
- 拦截器启用禁用配置
- 文件上传下载安全/权限控制
- 启用 `logback.xml` 日志配置

### ⚡️ Optimization
- 更改core包目录
- 下载上传拦截器
- logback.xml显示行号
- `application.yml` 拦截器配置新增 `include-path` 拦截路径配置
	
### 📝 Added/Modified
- Add `UploadInterceptor` 文件上传全局拦截器
- Add `DownloadInterceptor` 文件下载全局拦截器
- Add `DownloadHandler` `DefaultDownloadHandler` 文件下载回调自定义处理器
- Modify `config/WebMvcConfig` --> `core/SpringBootPlusWebMvcConfig`
- Modify `ImageController` --> `ResouceController`，请求路径 `/api/resource`
- Add `SysUser` CRUD

### 🐞  Bug Fixes
- Fix 文件下载路径潜在安全漏洞，过滤 `../` 非法路径参数
- Fix 优化文件下载，Firefox 中文乱码问题

### 📔  Documentation
- [spring-boot-plus-architecture](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.jpg)
- [5 Minutes Finish CRUD](https://github.com/geekidea/spring-boot-plus#5-minutes-finish-crud)

### 🔨 Dependency Upgrades
- `pom.xml` 使用 `spring-boot-starter-validation` 替换 `hibernate-validator` 依赖


## [V1.2.1-RELEASE] 2019.08.21

###  ⭐️  New Features
- 文件上传保存到服务器指定目录
- 文件下载
- 访问上传的图片等资源
- 启用项目静态资源访问，可访问static/templates目录下资源

### ⚡️ Optimization
- static资源访问：[http://localhost:8888/static/welcome.html](http://localhost:8888/static/welcome.html)
- templates资源访问：[http://localhost:8888/templates/springbootplus.html](http://localhost:8888/templates/springbootplus.html)
- 上传swagger：[http://localhost:8888/swagger-ui.html#!/upload-controller/uploadUsingPOST](http://localhost:8888/swagger-ui.html#!/upload-controller/uploadUsingPOST)
- 上传后，图片文件访问：[http://localhost:8888//resource/201908210134467.png](http://localhost:8888//resource/201908210134467.png)
- 图片自定义控制访问：[http://localhost:8888/image/201908210134467.png](http://localhost:8888/image/201908210134467.png)

### 📝 Added/Modified
- Add `UploadController` 上传控制器
- Add `DownloadController` 下载控制器
- Add `ImageController` 图片访问控制器
- Add `ResourceInterceptor` 资源拦截器

- Add `welcome.html` 在`static`目录下
- Add `springbootplus.html` 在`templates`目录下
- Add `ContentTypeUtil` 文件类型工具
- Add `mime-type.properties` 文件类型自定义拓展配置
- Add `UploadUtil` 上传工具类，`UploadFileNameHandle` 文件名称回调接口，`DefaultUploadFileNameHandleImpl` 默认文件名称实现类
- Add `DownloadUtil` 下载工具类

- Modify `WebMvcConfig` 注册资源拦截器，项目静态资源访问配置
- Modify `SpringBootPlusConfig` 创建 `ResourceInterceptor` 资源拦截器
- Modify `SpringBootPlusInterceptorConfig` 添加 `resourceConfig` 资源拦截器配置 
- Modify `SpringBootPlusProperties`  添加 `uploadPath`,`resourceAccessPath`,`resourceAccessPatterns`,`resourceAccessUrl`属性
- Modify `application.yml`, `application-local.yml` 添加文件上传/下载配置

- Modify `mysql_spring_boot_plus.sql` 添加创建数据库语句，如果不存在，则创建

### 🐞  Bug Fixes
- 拦截器`exclude-path`,`include-path`字符串配置问题，已修改为数组接收`String[] excludePath`,`String[] includePath`

### 📔  Documentation
- [mime-type大全](https://svn.apache.org/viewvc/httpd/httpd/trunk/docs/conf/mime.types?revision=1752884&view=co)

### 🔨 Dependency Upgrades
- Upgrade to springboot 2.1.7.RELEASE



## [V1.2.0-RELEASE] 2019.08.06
### 🚀 spring-boot-plus演示地址
- 👉 [spring-boot-plus演示地址-Spring Boot Admin](http://47.105.159.10:8888)

- 👉 [spring-boot-plus演示地址-Swagger](http://47.105.159.10:8888/docs)

###  ⭐️  New Features
- 集成`maven-assembly-plugin`进行项目打包
- 启动/重启脚本
- 提取`config`配置文件到项目外部
- 可配置代码生成查询参数是否支持排序
- 可配置代码生成策略：ALL/SIMPLE
- 新增`SpringBootPlusProperties`自定义属性配置类
- 新增`SpringBootPlusConfig`项目配置类

### ⚡️ Optimization
- 优化启动命令脚本路径
- 优化配置文件，设置默认值
- 优化分页排序，使用`OrderItem`
- 优化代码生成器模板
- 完成CRUD单元测试
- 完成Swagger接口测试 
- 删除OrderEnum枚举类，使用`OrderItem`替代
- 移除Rabbit/Kafka MQ配置，将在后续版本中已模块形式体现
- 优化刷新Swagger，后台报404 NOT FOUND，设置Swagger版本为`2.6.1`
- 优化`application.yml`及`application-xxx.yml`配置文件

### 🐞  Bug Fixes
- 修复LocalDateTime日期错误问题，使用Date日期类型
- 修复SpringBootAdmin在线查日志错误问题

### 📔  Documentation
- [项目打包](https://springboot.plus/guide/pack.html)
- [运维部署](https://springboot.plus/guide/deploy.html)
- [FAQ-LocalDateTime](https://springboot.plus/guide/faq.html#localdatetime日期类使用问题)

### 🔨 Dependency Upgrades
- Upgrade to mybatis-plus 3.1.2
- Upgrade to druid 1.1.18
- Upgrade to fastjson 1.2.59


## [V1.1.0-RELEASE] 2019.08.01
###  ⭐️  New Features
- 集成spring boot admin 后台监控功能

### ⚡️ Optimization
- 细节优化
- dev和 local环境一致


### 📔  Documentation
- [ Eclipse中使用spring-boot-plus项目详细步骤](https://springboot.plus/guide/eclipse-spring-boot-plus.html)
- [集成Spring Boot Admin](https://springboot.plus/guide/springbootadmin.html)
- [FAQ](https://springboot.plus/guide/faq.html)

### 😃 QQ技术交流讨论群
![QQ技术交流讨论群](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-qq-group.png)


## [V1.0.0-RELEASE] 2019.07.10
###  ⭐️  New Features
- 集成spring boot 2.1.6.RELEASE
- 集成mybatis-plus 3.1.1
- 集成redis
- 集成druid数据库连接池
- 集成rabbit/kafka mq
- 集成maven assembly项目打包
- 启动、重启命令脚本
- 项目公共代码部分
- AOP记录请求响应日志：LogAop
- 统一响应结果处理：ApiResult
- 公共常量
- 常用validator注解约束：EmailValidator、EnumTypeValidator、IdCardValidator、PhoneValidator
- 提取公共类：BaseEntity、BaseService、BaseServiceImpl、BaseTypeStateEnum、BaseController、PageParam等
- 全局异常处理：BusinessException、GlobalErrorController、GlobalExceptionHandler
- 跨域处理Filter：CrossDomainFilter
- 请求路径处理Filter：RequestPathFilter
- 分页封装
- 公共配置部分
- 转换器配置
- FastJson、Jackson 全局配置
- kafka/rabbit mq配置
- 文件上传配置
- mybatis-plus配置
- Redis缓存配置
- swagger配置
- webmvc配置
- 获取请求信息ip及对应的地区
- system模块
- 不同环境打包不同配置文件
- logback.xml日志配置
- 常用工具类等
- 代码生成器
- 自定义代码生成模板

### 📔  Documentation
- 官网：[https://springboot.plus](https://springboot.plus)
- GITHUB：[https://github.com/geekidea/spring-boot-plus](https://github.com/geekidea/spring-boot-plus "spring-boot-plus github")
- GITEE：[https://gitee.com/geekidea/spring-boot-plus](https://gitee.com/geekidea/spring-boot-plus "spring-boot-plus gitee")

