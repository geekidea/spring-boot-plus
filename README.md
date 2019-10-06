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
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-1.2.3--RELEASE-blue">
  </a>
  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.1.8.RELEASE-brightgreen">
  </a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p>

## What is spring-boot-plus?

### A **easy-to-use**, **high-speed**, **high-efficient**, **feature-rich**, **open source** spring boot scaffolding.
> spring-boot-plus is a background rapid development framework that integrates spring boot common development components.

> Front-end and back-end separation, focusing on back-end services

## Purpose
> Everyone can develop projects independently, quickly and efficiently！

## Repository
#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

## Website
#### [springboot.plus](http://springboot.plus)

## Features
- Integrated spring boot common development component set, common configuration, AOP log, etc
- Integrated mybatis-plus fast dao operation
- Quickly generate background code:entity/param/vo/controller/service/mapper/xml
- Integrated swagger2, automatic generation of api documents
- Integrated JWT,Shiro/Spring security permission control
- Integrated Redis、spring cache、ehcache,etc
- Integrated Rabbit/Rocket/Kafka MQ
- Integration alibaba druid connection pool, JDBC performance and slow query detection
- Integrated Spring Boot Admin, real-time detection of project operation
- Integrate maven-assembly-plugin for different environment package deployment, including startup and restart commands, and extract configuration files to external config directory

## Architecture
![spring-boot-plus-architecture.jpg](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.jpg)

### Project Environment 
Middleware | Version |  Remark
-|-|-
JDK | 1.8+ | JDK1.8 and above |
MySQL | 5.7+ | 5.7 and above |
Redis | 3.2+ |  |

### Technology stack 
Component| Version |  Remark
-|-|-
Spring Boot | 2.1.8.RELEASE | Latest release stable version |
Spring Framework | 5.1.9.RELEASE | Latest release stable version |
Mybatis | 3.5.2 | DAO Framework |
Mybatis Plus | 3.2.0 | mybatis Enhanced framework |
Alibaba Druid | 1.1.20 | Data source |
Fastjson | 1.2.60 | JSON processing toolset |
swagger2 | 2.6.1 | Api document generation tool |
commons-lang3 | 3.9 | Apache language toolkit |
commons-io | 2.6 | Apache IO Toolkit |
commons-codec | 1.13 | Apache Toolkit such as encryption and decryption |
commons-collections4 | 4.4 | Apache collections toolkit |
reflections | 0.9.11 | Reflection Toolkit  |
hibernate-validator | 6.0.17.Final | Validator toolkit |
Shiro | 1.4.1 | Permission control |
JWT | 3.8.3 | JSON WEB TOKEN |
hutool-all | 4.6.4 | Common toolset |
lombok | 1.18.8 | Automatically plugs |
mapstruct | 1.3.0.Final | Object property replication tool |

## CHANGELOG
#### [CHANGELOG.md](https://github.com/geekidea/spring-boot-plus/blob/master/CHANGELOG.md)

## Java Docs
#### [Java Api Docs](http://geekidea.io/spring-boot-plus-apidocs/)


## Getting started
### Clone spring-boot-plus
```bash
git clone https://github.com/geekidea/spring-boot-plus.git
cd spring-boot-plus
```

### Maven Build
> Local environment is used by default, The configuration file：application-local.yml
```bash
mvn clean package -Plocal
```


## 5 Minutes Finish CRUD

### 1. Create Table
```sql

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
drop table if exists `sys_user`;
create table sys_user
(
    id          bigint                              not null comment ''
        primary key,
    username    varchar(20)                         not null comment '',
    nickname    varchar(20)                         null comment '',
    password    varchar(64)                         not null comment '',
    salt        varchar(32)                         null comment '',
    remark      varchar(200)                        null comment '',
    status      int       default 1                 not null comment '',
    create_time timestamp default CURRENT_TIMESTAMP null comment '',
    update_time timestamp                           null comment '',
    constraint sys_user_username_uindex
        unique (username)
)
    comment 'SysUser';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, status, create_time, update_time) 
    VALUES (1, 'admin', 'Administrators', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', 'e4cc3292e3ebc483998adb2c0e4e640e', 'Administrator Account', 1, '2019-08-26 00:52:01', null);

INSERT INTO spring_boot_plus.sys_user (id, username, nickname, password, salt, remark, status, create_time, update_time) 
    VALUES (2, 'test', 'Testers', '751ade2f90ceb660cb2460f12cc6fe08268e628e4607bdb88a00605b3d66973c', '99952b31c18156169a26bec80fd211f6', 'Tester Account', 1, '2019-10-05 14:04:27', null);

```

### 2. Generator CRUD CODE
> Modify database info

> Modify module name / author / table name / primary key id

```text
/src/test/java/io/geekidea/springbootplus/test/CodeGenerator.java
```

```java
/**
 * spring-boot-plus Code Generator
 * @author geekidea
 * @date 2018-11-08
 */
public class CodeGenerator {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false";   
    // CODE... 
    // ############################ Config start ############################
    // Module name
    private static final String MODULE_NAME = "system";
    // Author
    private static final String AUTHOR = "geekidea";
    // Table name
    private static final String TABLE_NAME = "sys_user";
    // Primary key id
    private static final String PK_ID_COLUMN_NAME = "id";
    // Generator strategy.  true：All / false:SIMPLE
    private static final boolean GENERATOR_STRATEGY = true;
    // Pagination list query Whether to sort. true：sort/false：non
    private static final boolean PAGE_LIST_ORDER = false;
    // ############################ Config end ############################

    public static void main(String[] args) {
        // Run...
    }
}
```

> Generated code structure

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

### 3. Startup Project
> Project Main Class
```text
/src/main/java/io/geekidea/springbootplus/SpringBootPlusApplication.java
```

```java
/**
 * spring-boot-plus Project Main Class
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
        // Run spring-boot-plus
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootPlusApplication.class, args);
        // Print Project Info
        PrintApplicationInfo.print(context);
    }

}
```

### 4. Access project swagger docs
[http://127.0.0.1:8888/swagger-ui.html](http://127.0.0.1:8888/swagger-ui.html)

### 5. SysUser CRUD Swagger
![sys_user_swagger.png](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/sys_user_swagger.png)


## Quick Start
[Quick Start](https://springboot.plus/guide/quick-start.html)

## Documentation
 [https://springboot.plus](https://springboot.plus)


## CentOS Quick Installation Environment / Build / Deploy / Launch Spring-boot-plus Project

### 1. Download the installation script
> Install `jdk`, `git`, `maven`, `redis`, `mysql`

```bash
wget -O download-install-all.sh https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/bin/install/download-install-all.sh
```

### 2. Run the installation script
```bash
sh download-install-all.sh
```

### 3. Modify MySQL password
```bash
ALTER USER 'root'@'localhost' IDENTIFIED BY 'Springbootplus666!';
exit
mysql -uroot -pSpringbootplus666!
```

### 4. Import MySQL scripts
```bash
create database if not exists spring_boot_plus character set utf8mb4;
use spring_boot_plus;
source /root/mysql_spring_boot_plus.sql;
show tables;
exit
```

### 5. Download deployment script `deploy.sh`
```bash
wget -O deploy.sh https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/deploy/deploy.sh
```

### 6. Execution script
```bash
sh deploy.sh
```

### 7.Access project
> SpringBootAdmin Management page

[http://47.105.159.10:8888](http://47.105.159.10:8888)

> spring-boot-plus Swagger Document page

[http://47.105.159.10:8888/docs](http://47.105.159.10:8888/docs)

### 8. View project run log
```bash
tail -f -n 1000 /root/spring-boot-plus-server/logs/spring-boot-plus.log
```


## spring-boot-plus Videos  :movie_camera: 
- [5-Minutes-Finish-CRUD](https://www.bilibili.com/video/av67401204)
- [CentOS Quick Installation JDK/Git/Maven/Redis/MySQL](https://www.bilibili.com/video/av67218836/)
- [CentOS Quick Build / Deploy / Launch Spring-boot-plus Project](https://www.bilibili.com/video/av67218970/)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.
