<p align="center">
  <a href="https://github.com/geekidea/spring-boot-plus">
   <img alt="spring-boot-plus logo" src="https://raw.githubusercontent.com/geekidea/spring-boot-plus/dev/docs/img/logo.png">
  </a>
</p>
<p align="center">
  Everyone can develop projects independently, quickly and efficiently！
</p>

<p align="center">  
  <a href="https://github.com/geekidea/spring-boot-plus/">
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-1.2.2--RELEASE-blue">
  </a>
  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.1.7.RELEASE-brightgreen">
  </a>
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p>

## What is spring-boot-plus?

### A **easy-to-use**, **high-speed**, **high-efficient**, **feature-rich**, **open source** spring boot scaffolding.
> spring-boot-plus is a background rapid development framework that integrates spring boot common development components.

## Purpose
> Everyone can develop projects independently, quickly and efficiently！

## Repository
#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

## Website
#### [springboot.plus](http://springboot.plus "springboot.plus")

## Architecture
![spring-boot-plus-architecture.jpg](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.jpg)

## Features
- Integrated spring boot common development component set, common configuration, AOP log, etc
- Integrated mybatis-plus fast dao operation
- Quickly generate background code:entity/param/vo/controller/service/mapper/xml
- Integrated swagger2, automatic generation of api documents
- Integrated JWT, spring security permission control
- Integrated Redis、spring cache、ehcache,etc
- Integrated Rabbit/Rocket/Kafka MQ
- Integration alibaba druid connection pool, JDBC performance and slow query detection
- Integrated Spring Boot Admin, real-time detection of project operation
- Integrate maven-assembly-plugin for different environment package deployment, including startup and restart commands, and extract configuration files to external config directory

### Project Environment 
Middleware | Version |  Remark
-|-|-
JDK | 1.8+ | JDK1.8 and above |
MySQL | 5.7+ | 5.7 and above |
Redis | 3.2+ |  |

### Technology stack 
Component| Version |  Remark
-|-|-
Spring Boot | 2.1.7.RELEASE | Latest release stable version |
Spring Framework | 5.1.9.RELEASE | Latest release stable version |
Mybatis | 3.5.1 | DAO Framework |
Mybatis Plus | 3.1.2 | mybatis Enhanced framework |
Alibaba Druid | 1.1.18 | Data source |
Fastjson | 1.2.59 | JSON processing toolset |
swagger2 | 2.6.1 | Api document generation tool |
commons-lang3 | 3.9 | Apache language toolkit |
commons-io | 2.6 | Apache IO Toolkit |
commons-codec | 1.12 | Apache Toolkit such as encryption and decryption |
commons-collections | 3.2.1 | Apache collections toolkit |
reflections | 0.9.11 | Reflection Toolkit  |
hibernate-validator | 6.0.17.Final | Validator toolkit |
jwt | 0.9.1 | JSON WEB TOKEN |
hutool-all | 4.5.10 | Common toolset |
lombok | 1.18.8 | Automatically plugs |

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
create table sys_user(
    id          bigint                              not null comment 'id',
    name        varchar(20)                         null comment 'name',
    account     varchar(20)                         not null comment 'account',
    pwd         varchar(20)                         not null comment 'password',
    remark      varchar(200)                        null comment 'remark',
    create_time timestamp default CURRENT_TIMESTAMP null comment 'create time',
    update_time timestamp                           null comment 'update time',
    primary key (`id`),
    constraint sys_user_account_uindex
        unique (account)
) comment 'SystemUser';
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user (id, name, account, pwd, remark, create_time, update_time) VALUES (1, 'Administrator', 'admin', '123456', 'Administrator Account', '2019-08-26 00:52:01', null);

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

## Contact
- Gmail: [springbootplus@aliyun.com](mailto:springbootplus@aliyun.com)
- QQ Group

![spring-boot-plus QQ Group](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-qq-group.png)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.
