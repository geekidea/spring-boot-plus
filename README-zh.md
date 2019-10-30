<p align="center">
  <a href="https://github.com/geekidea/spring-boot-plus">
   <img alt="spring-boot-plus logo" src="https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/logo.png">
  </a>
</p>
<p align="center">
  Everyone can develop projects independently, quickly and efficiently！
</p>

<p align="center">  
  <a href="https://github.com/geekidea/spring-boot-plus/">
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-1.4.0-blue">
  </a>
  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.2.0.RELEASE-brightgreen">
  </a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p>

### spring-boot-plus是一套集成spring boot常用开发组件的后台快速开发框架
> Spring-Boot-Plus是易于使用，快速，高效，功能丰富，开源的spring boot 脚手架.

> 前后端分离,专注于后端服务

## 目标
> 每个人都可以独立、快速、高效地开发项目！

## 版本库
#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

## 官网
#### [springboot.plus](http://springboot.plus)

### 主要特性
1. 集成spring boot 常用开发组件集、公共配置、AOP日志等
2. 集成mybatis plus快速dao操作
3. 快速生成后台代码: entity/param/vo/controller/service/mapper/xml
4. 集成swagger2，可自动生成api文档
5. 集成jwt、shiro/spring security权限控制
6. 集成redis、spring cache、ehcache缓存
7. 集成rabbit/rocket/kafka mq消息队列
8. 集成druid连接池，JDBC性能和慢查询检测
9. 集成spring boot admin，实时检测项目运行情况
10. 使用assembly maven插件进行不同环境打包部署,包含启动、重启命令，配置文件提取到外部config目录

## 项目架构
![spring-boot-plus-architecture.jpg](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.jpg)

### 项目环境 
中间件 | 版本 |  备注
-|-|-
JDK | 1.8+ | JDK1.8及以上 |
MySQL | 5.7+ | 5.7及以上 |
Redis | 3.2+ |  |

### 技术选型 
技术 | 版本 |  备注
-|-|-
Spring Boot | 2.2.0.RELEASE | 最新发布稳定版 |
Spring Framework | 5.2.0.RELEASE | 最新发布稳定版 |
Mybatis | 3.5.2 | 持久层框架 |
Mybatis Plus | 3.2.0 | mybatis增强框架 |
Alibaba Druid | 1.1.20 | 数据源 |
Fastjson | 1.2.62 | JSON处理工具集 |
swagger2 | 2.6.1 | api文档生成工具 |
commons-lang3 | 3.9 | 常用工具包 |
commons-io | 2.6 | IO工具包 |
commons-codec | 1.13 | 加密解密等工具包 |
commons-collections4 | 4.4 | 集合工具包 |
reflections | 0.9.11 | 反射工具包 |
hibernate-validator | 6.0.17.Final | 后台参数校验注解 |
Shiro | 1.4.1 | 权限控制 |
JWT | 3.8.3 | JSON WEB TOKEN |
hutool-all | 5.0.3 | 常用工具集 |
lombok | 1.18.10 | 注解生成Java Bean等工具 |
mapstruct | 1.3.1.Final | 对象属性复制工具 |

## CHANGELOG
#### [CHANGELOG.md](https://github.com/geekidea/spring-boot-plus/blob/master/CHANGELOG.md)

## Java Docs
#### [Java Api Docs](https://apidoc.gitee.com/geekidea/spring-boot-plus/)

## 使用
### 克隆 spring-boot-plus
```bash
git clone https://github.com/geekidea/spring-boot-plus.git
cd spring-boot-plus
```

### Maven 构建
> 默认使用local环境,对应配置文件：application-local.yml

```bash
mvn clean package -Plocal
```


## 5分钟完成增删改查

### 1. 创建数据库表
```sql

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
drop table if exists `sys_user`;
create table sys_user
(
    id          bigint                              not null comment '主键'
        primary key,
    username    varchar(20)                         not null comment '用户名',
    nickname    varchar(20)                         null comment '昵称',
    password    varchar(64)                         not null comment '密码',
    salt        varchar(32)                         null comment '盐值',
    remark      varchar(200)                        null comment 'remark',
    status      int       default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间',
    constraint sys_user_username_uindex
        unique (username)
)
    comment '系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, state, create_time, update_time) 
    VALUES (1, 'admin', '管理员', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', 'e4cc3292e3ebc483998adb2c0e4e640e', 'Administrator Account', 1, '2019-08-26 00:52:01', null);
INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, state, create_time, update_time) 
    VALUES (2, 'test', '测试人员', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', '99952b31c18156169a26bec80fd211f6', 'Tester Account', 1, '2019-10-05 14:04:27', null);

```

### 2.使用代码生成器生成增删改查代码
> 修改数据库信息

>修改组件名称/作者/数据库表名称/主键id

```text
/src/test/java/io/geekidea/springbootplus/test/CodeGenerator.java
```

```java
/**
 * spring-boot-plus代码生成器入口类
 *
 * @author geekidea
 * @date 2019-10-22
 **/
public class SpringBootPlusGenerator {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        // 公共配置
        // 数据库配置
        codeGenerator
                .setUserName("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver")
                .setDriverUrl("jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false");

        // 包信息
        codeGenerator
                .setProjectPackagePath("io/geekidea/springbootplus")
                .setParentPackage("io.geekidea.springbootplus");

        // 组件作者等配置
        codeGenerator
                .setModuleName("system")
                .setAuthor("geekidea")
                .setPkIdColumnName("id");

        // 生成策略
        codeGenerator
                .setGeneratorStrategy(CodeGenerator.GeneratorStrategy.ALL)
                .setPageListOrder(true)
                .setParamValidation(true);

        // 生成实体映射相关代码,可用于数据库字段更新
        // 当数据库字段更新时，可自定义自动生成哪些那文件
        codeGenerator
                .setGeneratorEntity(true)
                .setGeneratorQueryParam(true)
                .setGeneratorQueryVo(true);

        // 生成业务相关代码
        codeGenerator
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true);

        // 是否生成Shiro RequiresPermissions注解
        codeGenerator.setRequiresPermissions(true);

        // 是否覆盖已有文件
        codeGenerator.setFileOverride(true);

        // 初始化公共变量
        codeGenerator.init();

        // 需要生成的表数组
        // xxx,yyy,zzz为需要生成代码的表名称
        String[] tables = {
                "xxx",
                "yyy",
                "zzz",
        };

        // 循环生成
        for (String table : tables) {
            // 设置需要生成的表名称
            codeGenerator.setTableName(table);
            // 生成代码
            codeGenerator.generator();
        }

    }

}
```

> 生成的代码结构

```text
/src/main/java/io/geekidea/springbootplus/system
```

```text
└── system
    ├── entity
    │   └── SysUser.java
    ├── mapper
    │   └── SysUserMapper.java
    ├── service
    │   ├── SysUserService.java
    │   └── impl
    │       └── SysUserServiceImpl.java
    └── web
        ├── controller
        │   └── SysUserController.java
        ├── param
        │   └── SysUserQueryParam.java
        └── vo
            └── SysUserQueryVo.java
```

> Mapper XML
```text
/src/main/resources/mapper/system/SysUserMapper.xml
```

### 3. 启动项目
> 项目入口类
```text
/src/main/java/io/geekidea/springbootplus/SpringBootPlusApplication.java
```

```java
/**
 * spring-boot-plus 项目启动入口
 * @author geekidea
 * @since 2018-11-08
 */
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAdminServer
@MapperScan({"io.geekidea.springbootplus.**.mapper"})
@SpringBootApplication
public class SpringBootPlusApplication {

    public static void main(String[] args) {
        // 启动spring-boot-plus
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootPlusApplication.class, args);
        // 打印项目信息
        PrintApplicationInfo.print(context);
    }

}
```

### 4. 访问项目swagger文档
[http://127.0.0.1:8888/swagger-ui.html](http://127.0.0.1:8888/swagger-ui.html)

### 5. 系统用户 增删改查分页Swagger
![sys_user_swagger-zh.png](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/sys_user_swagger-zh.png)

## 快速开始
[快速开始](https://springboot.plus/guide/quick-start.html)

## 详细文档
 [https://springboot.plus](https://springboot.plus)

## CentOS快速安装环境/构建/部署/启动spring-boot-plus项目
### 1. 下载安装脚本
> 安装 `jdk`, `git`, `maven`, `redis`, `mysql`

```bash
wget -O download-install-all.sh https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/download-install-all.sh
```

### 2. 运行安装脚本
```bash
sh download-install-all.sh
```

### 3. 修改MySQL密码
```bash
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Springbootplus666!';
exit
mysql -uroot -pSpringbootplus666!
```

### 4. 导入MySQL脚本
```bash
create database if not exists spring_boot_plus character set utf8mb4;
use spring_boot_plus;
source /root/mysql_spring_boot_plus.sql;
show tables;
exit
```

### 5. 下载部署脚本 `deploy.sh`
```bash
wget -O deploy.sh https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/deploy/deploy.sh
```

### 6. 执行脚本
```bash
sh deploy.sh
```

### 7.访问项目
> SpringBootAdmin管理页面

[http://47.105.159.10:8888](http://47.105.159.10:8888)

> spring-boot-plus Swagger文档页面

[http://47.105.159.10:8888/docs](http://47.105.159.10:8888/docs)

### 8. 查看项目运行日志
```bash
tail -f -n 1000 /root/spring-boot-plus-server/logs/spring-boot-plus.log
```

## spring-boot-plus 视频  :movie_camera:
- [5分钟完成增删改查](https://www.bilibili.com/video/av67401204)
- [CentOS 快速安装 JDK/Git/Maven/Redis/MySQL](https://www.bilibili.com/video/av67218836/)
- [CentOS 快速部署/构建/打包/运行项目](https://www.bilibili.com/video/av67218970/)


## 联系
- Email: [springbootplus@aliyun.com](mailto:springbootplus@aliyun.com)
- spring-boot-plus技术交流群

![spring-boot-plus QQ Group](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-qq-group.png)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.
