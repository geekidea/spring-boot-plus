<p align="center">
  <a href="https://github.com/geekidea/spring-boot-plus">
   <img alt="spring-boot-plus logo" src="https://springboot.plus/img/logo.png">
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
![spring-boot-plus-architecture.jpg](https://springboot.plus/img/spring-boot-plus-architecture.jpg)

### Project Environment 
Middleware | Version |  Remark
-|-|-
JDK | 1.8+ | JDK1.8 and above |
MySQL | 5.7+ | 5.7 and above |
Redis | 3.2+ |  |

### Technology stack 
Component| Version |  Remark
-|-|-
Spring Boot | 2.2.0.RELEASE | Latest release stable version |
Spring Framework | 5.2.0.RELEASE | Latest release stable version |
Mybatis | 3.5.2 | DAO Framework |
Mybatis Plus | 3.2.0 | mybatis Enhanced framework |
Alibaba Druid | 1.1.20 | Data source |
Fastjson | 1.2.62 | JSON processing toolset |
swagger2 | 2.6.1 | Api document generation tool |
commons-lang3 | 3.9 | Apache language toolkit |
commons-io | 2.6 | Apache IO Toolkit |
commons-codec | 1.13 | Apache Toolkit such as encryption and decryption |
commons-collections4 | 4.4 | Apache collections toolkit |
reflections | 0.9.11 | Reflection Toolkit  |
hibernate-validator | 6.0.17.Final | Validator toolkit |
Shiro | 1.4.1 | Permission control |
JWT | 3.8.3 | JSON WEB TOKEN |
hutool-all | 5.0.3 | Common toolset |
lombok | 1.18.10 | Automatically plugs |
mapstruct | 1.3.1.Final | Object property replication tool |

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
-- Table structure for foo_bar
-- ----------------------------
DROP TABLE IF EXISTS `foo_bar`;
CREATE TABLE `foo_bar`
(
    `id`            bigint(20)  NOT NULL COMMENT 'ID',
    `name`          varchar(20) NOT NULL COMMENT 'Name',
    `foo`           varchar(20)          DEFAULT NULL COMMENT 'Foo',
    `bar`           varchar(20) NOT NULL COMMENT 'Bar',
    `remark`        varchar(200)         DEFAULT NULL COMMENT 'Remark',
    `state`         int(11)     NOT NULL DEFAULT '1' COMMENT 'State，0：Disable，1：Enable',
    `version`       int(11)     NOT NULL DEFAULT '0' COMMENT 'Version',
    `create_time`   timestamp   NULL     DEFAULT CURRENT_TIMESTAMP COMMENT 'Create Time',
    `update_time`   timestamp   NULL     DEFAULT NULL COMMENT 'Update Time',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='FooBar';

-- ----------------------------
-- Records of foo_bar
-- ----------------------------
INSERT INTO foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time) 
    VALUES (1, 'FooBar', 'foo', 'bar', 'remark...', 1, 0, '2019-11-01 14:05:14', null);
INSERT INTO foo_bar (id, name, foo, bar, remark, state, version, create_time, update_time) 
    VALUES (2, 'HelloWorld', 'hello', 'world', null, 1, 0, '2019-11-01 14:05:14', null);

```

### 2. Generator CRUD CODE
> Modify database info

> Modify module name / author / table name / primary key id

```text
/src/test/java/io/geekidea/springbootplus/test/SpringBootPlusGenerator.java
```

```java
/**
 * spring-boot-plus Code Generator
 *
 * @author geekidea
 * @date 2019-10-22
 **/
public class SpringBootPlusGenerator {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        // Common configuration
        // Database configuration
        codeGenerator
                .setUserName("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver")
                .setDriverUrl("jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false");

        // Configuration package information
        codeGenerator
                .setProjectPackagePath("io/geekidea/springbootplus")
                .setParentPackage("io.geekidea.springbootplus");

        // Configuration of component author, etc.
        codeGenerator
                .setModuleName("foobar")
                .setAuthor("geekidea")
                .setPkIdColumnName("id");

        // Generation strategy
        codeGenerator
                .setGeneratorStrategy(CodeGenerator.GeneratorStrategy.ALL)
                .setPageListOrder(true)
                .setParamValidation(true);

        // Customize which files are generated automatically
        codeGenerator
                .setGeneratorEntity(true)
                .setGeneratorQueryParam(true)
                .setGeneratorQueryVo(true);

        // Generate business related codes
        codeGenerator
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true);

        // Generated RequiresPermissions Annotation
        codeGenerator.setRequiresPermissions(false);

        // Overwrite existing file or not
        codeGenerator.setFileOverride(true);

        // Initialize common variables
        codeGenerator.init();

        // Table array to be generated
        String[] tables = {
                "foo_bar"
        };

        // Cycle generation
        for (String table : tables) {
            // Set the name of the table to be generated
            codeGenerator.setTableName(table);
            // Generate code
            codeGenerator.generator();
        }

    }

}
```

> Generated code structure

```text
/src/main/java/io/geekidea/springbootplus/foobar
```

```text
└── foobar
    ├── controller
    │   └── FooBarController.java
    ├── entity
    │   └── FooBar.java
    ├── mapper
    │   └── FooBarMapper.java
    ├── param
    │   └── FooBarQueryParam.java
    ├── service
    │   ├── FooBarService.java
    │   └── impl
    │       └── FooBarServiceImpl.java
    └── vo
        └── FooBarQueryVo.java
```

> Mapper XML
```text
/src/main/resources/mapper/foobar/FooBarMapper.xml
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
![sys_user_swagger.png](https://springboot.plus/img/sys_user_swagger.png)


## Quick Start
[Quick Start](https://springboot.plus/guide/quick-start.html)

## Documentation
 [https://springboot.plus](https://springboot.plus)


## CentOS Quick Installation Environment / Build / Deploy / Launch Spring-boot-plus Project

### 1. Download the installation script
> Install `jdk`, `git`, `maven`, `redis`, `mysql`

```bash
wget -O download-install-all.sh https://springboot.plus/bin/download-install-all.sh
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
wget -O deploy.sh https://springboot.plus/bin/deploy.sh
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


## spring-boot-plus Views

### spring-boot-plus IDEA Sources Views

![spring-boot-plus-idea](https://springboot.plus/img/home/spring-boot-plus-idea.png)

### [Spring Boot Admin Instances](http://47.105.159.10:8888/instances/e211ba082db8/details)
<p>
    <a href="http://47.105.159.10:8888/instances/e211ba082db8/details">
        <img src="https://springboot.plus/img/home/spring-boot-admin.png" alt="spring-boot-admin instances">
    </a>
</p>

### [Spring Boot Admin Statistics](http://47.105.159.10:8888/instances/e211ba082db8/details)
<p>
    <a href="http://47.105.159.10:8888/instances/e211ba082db8/details">
        <img src="https://springboot.plus/img/home/spring-boot-admin-1.png" alt="spring-boot-admin statistics">
    </a>
</p>

### [Spring Boot Admin Log](http://47.105.159.10:8888/instances/e211ba082db8/logfile)
<p>
    <a href="http://47.105.159.10:8888/instances/e211ba082db8/logfile">
        <img src="https://springboot.plus/img/home/spring-boot-admin-log.png" alt="spring-boot-admin log">
    </a>
</p>

### [spring-boot-plus Swagger文档](http://47.105.159.10:8888/swagger-ui.html)
<p>
    <a href="http://47.105.159.10:8888/swagger-ui.html">
        <img src="https://springboot.plus/img/home/spring-boot-plus-swagger.png" alt="spring-boot-plus swagger docs">
    </a>
</p>

### [spring-boot-plus Java Api Docs](http://geekidea.io/spring-boot-plus-apidocs/)
<p>
    <a href="http://geekidea.io/spring-boot-plus-apidocs/">
        <img src="https://springboot.plus/img/home/spring-boot-plus-java-apidocs.png" alt="spring-boot-plus Java Api Docs">
    </a>
</p>


## spring-boot-plus Videos  :movie_camera: 
- [5-Minutes-Finish-CRUD](https://www.bilibili.com/video/av67401204)
- [CentOS Quick Installation JDK/Git/Maven/Redis/MySQL](https://www.bilibili.com/video/av67218836/)
- [CentOS Quick Build / Deploy / Launch Spring-boot-plus Project](https://www.bilibili.com/video/av67218970/)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.
