<p align="center">
  <a href="https://github.com/geekidea/spring-boot-plus">
   <img alt="spring-boot-plus logo" src="https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/logo.png">
  </a>
</p>
<p align="center">
  Everyone can develop projects independently, quickly and efficiently！
</p>

<p align="center">  
  <a href="https://github.com/geekidea/spring-boot-plus/">
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-2.7.18-blue">
  </a>
  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.7.18-brightgreen">
  </a>
  <a href="https://cn.vuejs.org/">
    <img alt="spring boot version" src="https://img.shields.io/badge/vue-3.2-darkgreen">
  </a>
  <a href="https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE">
    <img alt="code style" src="https://img.shields.io/badge/license-MIT-green">
  </a>
</p>

## What is spring-boot-plus?

### A **easy-to-use**, **high-speed**, **high-efficient**, **feature-rich**, **open source** spring boot scaffolding.
> spring-boot-plus is a background rapid development framework that integrates spring boot common development components.

## Purpose
> Everyone can develop projects independently, quickly and efficiently！

## Open source License MIT-License
> Any individual or company can conduct secondary development based on this framework for commercial use without authorization!

## Vue Project  [VUE3+TS](https://gitee.com/geekidea/spring-boot-plus-vue3)

#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

#### [中文文档](https://github.com/geekidea/spring-boot-plus/blob/master/README-zh.md)

#### [springboot.plus](http://springboot.plus)

## Features
- Integrated spring boot common development component set, common configuration, AOP log, etc
- Maven Project
- Integrated mybatis-plus fast dao operation
- Quickly generate background code:entity/param/vo/controller/service/mapper/xml
- Integrated Swagger/Knife4j, automatic generation of api documents
- Integrated Redis Cache
- Integration HikariCP connection pool, A solid, high-performance, JDBC connection pool at last.

## Source code directory structure
```text
spring-boot-plus
├── docs
│ ├── bin
│ │ └── install
│ ├── config
│ ├── db
│ └── img
├── logs
└── src
    ├── main
    │ ├── java
    │ │ └── io
    │ │     └── geekidea
    │ │         └── boot
    │ │             ├── auth
    │ │             ├── common
    │ │             ├── config
    │ │             ├── demo
    │ │             ├── framework
    │ │             ├── system
    │ │             ├── user
    │ │             └── util
    │ │             └── SpringBootPlusApplication.java
    │ └── resources
    │     ├── mapper
    │     └── static
    │     ├── application-dev.yml
    │     ├── application-prod.yml
    │     ├── application-test.yml
    │     ├── application.yml
    │     ├── banner.txt
    │     ├── ip2region.xdb
    │     ├── logback-spring.xml
    └── test
        ├── java
        │ └── io
        │     └── geekidea
        │         └── boot
        │             ├── generator
        │             └── system
        └── resources
            └── templates
```

### Project Environment 
Name | Version |  Remark
-|-|-
JDK | 1.8+ | JDK1.8 and above |
MySQL | 5.7+ | 5.7 and above |
Redis | 3.2+ |  |

### Technology stack 
Component| Version |  Remark
-|-|-
Spring Boot | 2.7.18 |
Mybatis | 3.5.13 | DAO Framework |
Mybatis Plus | 3.5.4.1 | mybatis Enhanced framework |
Fastjson | 2.0.42 | JSON processing toolset |
Swagger | V3 | Api document generation tool |
Knife4j | 4.3.0 | Api document generation tool |
commons-lang3 | 3.14.0 | Apache language toolkit |
commons-io | 2.15.0 | Apache IO Toolkit |
commons-codec | 1.16.0 | Apache Toolkit such as encryption and decryption |
commons-collections4 | 4.4.4 | Apache collections toolkit |
hibernate-validator | 6.2.5.Final | Validator toolkit |
hutool-all | 5.8.23 | Common toolset |
lombok | 1.18.30 | Automatically plugs |

### Project Link Diagram
![项目调用链路图](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/system-link.jpg)

### [CHANGELOG](https://github.com/geekidea/spring-boot-plus/blob/master/CHANGELOG.md)


## Quick Start
### Clone spring-boot-plus
```bash
git clone https://github.com/geekidea/spring-boot-plus.git
cd spring-boot-plus
```

### Maven Build
> dev environment is used by default, The configuration file：application-dev.yml
```bash
mvn clean package -Pdev
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

create table foo_bar
(
    id          bigint                               not null comment 'ID'
        primary key,
    name        varchar(20)                          not null comment 'Name',
    foo         varchar(100)                         null comment 'Foo',
    bar         varchar(100)                         null comment 'Bar',
    remark      varchar(200)                         null comment 'Remark',
    status      tinyint(1) default 1                 not null comment 'Status，0：Disable，1：Enable',
    create_time timestamp  default CURRENT_TIMESTAMP null comment 'Create Time',
    update_time timestamp                            null comment 'Update Time'
)
    comment 'FooBar';

-- ----------------------------
-- Records of foo_bar
-- ----------------------------
INSERT INTO foo_bar (id, name, foo, bar, remark, status, create_time, update_time) VALUES (1, 'FooBar', 'Foo', 'Bar', null, 1, '2023-07-01 21:01:10', null);

```

### 2. Generator CRUD CODE
> Code generation entry class, in the generator module

```text
src/test/java/io/geekidea/boot/generator/Generator.java
```

```java
/**
 * spring-boot-plus Code Generator Main Class
 *
 * @author geekidea
 * @date 2022/3/16
 **/
public class Generator {

    public static void main(String[] args) throws Exception {
        GeneratorConfig config = new GeneratorConfig();
        // 项目信息配置
        config.setParentPackage("io.geekidea.boot" )
                .setModuleName("foobar" )
                .setAuthor("geekidea" );
        // 表名称和需要去掉的表前缀
        config.setTableNames("foo_bar" )
                .setTablePrefix("");
        // 是否覆盖已有文件
        config.setFileOverride(true);
        // 是否只更新实体类
        config.setOnlyOverrideEntity(false);
        GenerateHandler handler = new GenerateHandler();
        handler.generator(config);
    }
}
```

#### Generated code structure

```text
├── controller
│ └── FooBarController.java
├── dto
│ ├── FooBarDto.java
│ └── FooBarUpdateDto.java
├── entity
│ └── FooBar.java
├── mapper
│ └── FooBarMapper.java
├── query
│ └── FooBarQuery.java
├── service
│ ├── FooBarService.java
│ └── impl
│     └── FooBarServiceImp.java
└── vo
    ├── FooBarVo.java
    └── FooBarVo.java

resources
└── mapper
    └── foobar
        └── FooBarMapper.xml    
```

#### Code Generator Templates
> Use Velocity template to generate code, you can customize and modify the code to generate template

```text
src/test/resources
└── templates
    ├── addDto.java.vm          Add DTO generator template
    ├── controller.java.vm      Controller generator template
    ├── entity.java.vm          Entity generator template
    ├── infoVo.java.vm          Detail VO generator template
    ├── mapper.java.vm          Mapper  generator template
    ├── mapper.xml.vm           Mapper xml  generator template
    ├── query.java.vm           Page Query  generator template
    ├── service.java.vm         Service  generator template
    ├── serviceImpl.java.vm     Service implement  generator template
    ├── updateDto.java.vm       Update DTO generator template
    └── vo.java.vm              List VO generator template
```


### 3. Startup Project
> Project Main Class: SpringBootPlusApplication  [http://localhost:8888](http://localhost:8888)

```text
src/main/java/io/geekidea/boot/SpringBootPlusApplication.java
```

```java
/**
 * spring-boot-plus Project Main Class
 *
 * @author geekidea
 * @date 2022-3-16
 */
@EnableAsync
@SpringBootApplication
public class SpringBootPlusApplication {

    private static final String BACKSLASH = "/";

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootPlusApplication.class, args);
        // 打印项目信息
        printlnProjectInfo(context);
        System.out.println("  _____ _______       _____ _______    _____ _    _  _____ _____ ______  _____ _____ \n" +
                " / ____|__   __|/\\   |  __ \\__   __|  / ____| |  | |/ ____/ ____|  ____|/ ____/ ____|\n" +
                "| (___    | |  /  \\  | |__) | | |    | (___ | |  | | |   | |    | |__  | (___| (___  \n" +
                " \\___ \\   | | / /\\ \\ |  _  /  | |     \\___ \\| |  | | |   | |    |  __|  \\___ \\\\___ \\ \n" +
                " ____) |  | |/ ____ \\| | \\ \\  | |     ____) | |__| | |___| |____| |____ ____) |___) |\n" +
                "|_____/   |_/_/    \\_\\_|  \\_\\ |_|    |_____/ \\____/ \\_____\\_____|______|_____/_____/ \n");
    }
}

```

### 4. Access Swagger Docs
[http://localhost:8888/swagger-ui/index.html](http://localhost:8888/swagger-ui/index.html)
![swagger-ui.png](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/swagger-v3.png)

### 5. Access Knife4j Docs 
[http://localhost:8888/doc.html](http://localhost:8888/doc.html)
![knife4j.png](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/knife4j-doc.png)


## spring-boot-plus-vue Vue3 Project
### [GITEE-REPO](https://gitee.com/geekidea/spring-boot-plus-vue3)
#### System User List
![System User List](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/user-manager.png)
#### System Role List
![System Role List](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/role-manager.png)
#### System Menu List
![System Menu List](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/menu-manager.png)
#### System Department
![System Department List](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/dept-manager-dark.png)
#### System Log List
![System Log List](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/log-manager.png)
![System Log Detail](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/log-detail.png)
#### User Profile
![User Profile](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/profile.png)

## spring-boot-plus Videos  :movie_camera: 


QQ群 625301326| 微信公众号 geekideaio|
-|-|
![spring-boot-plus QQ Group](https://spring-boot-plus.gitee.io/img/spring-boot-plus-qq-group.png) | ![Wechat Official Account](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/geekidea-wechat-official.jpg) |

微信技术交流群| 业务咨询商务合作|
-|-|
![微信群](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/company-wechat-group-small.jpeg) | ![业务咨询商务合作](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/business-small.png) |


## License
spring-boot-plus is under the MIT-License. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.

