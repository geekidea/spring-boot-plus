<p align="center">
  <a href="https://github.com/geekidea/spring-boot-plus">
   <img alt="spring-boot-plus logo" src="https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/logo.png">
  </a>
</p>
<p align="center">
  Everyone can develop projects independently, quickly and efficiently！
</p>

<p align="center">  
  <a href="https://gitee.com/geekidea/spring-boot-plus">
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-2.7.12-blue">
  </a>
  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.7.12-brightgreen">
  </a>
  <a href="https://cn.vuejs.org/">
    <img alt="spring boot version" src="https://img.shields.io/badge/vue-3.2-darkgreen">
  </a>
  <a href="https://gitee.com/geekidea/spring-boot-plus/blob/master/LICENSE">
    <img alt="code style" src="https://img.shields.io/badge/license-MIT-green">
  </a>
</p>

### spring-boot-plus是一套集成spring boot常用开发组件的后台快速开发框架
> Spring-Boot-Plus是易于使用，快速，高效，功能丰富，开源的spring boot 脚手架.

> 前后端分离,更专注于后端服务

## 目标
> 每个人都可以独立、快速、高效地开发项目！

#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

#### [springboot.plus](http://springboot.plus)

### 主要特性
- 集成spring boot 常用开发组件集、公共配置、AOP日志等
- Maven单模块架构，更快更简单
- 集成mybatis plus快速dao操作
- 快速生成后台代码: entity/dto/query/vo/controller/service/mapper/xml
- 集成Swagger/Knife4j，可自动生成api文档
- 集成Redis缓存
- 集成HikariCP连接池，JDBC性能和慢查询检测

## 项目结构
```text
spring-boot-plus
├── main
│ ├── java
│ │ └── io
│ └── resources
│     ├── application-dev.yml
│     ├── application-prod.yml
│     ├── application-test.yml
│     ├── application.yml
│     ├── banner.txt
│     ├── mapper
│     └── static
└── test
    ├── java
    │ └── io
    └── resources
        └── templates
```

## 源代码目录结构
```text
src/main/java/io/geekidea/boot
├── SpringBootPlusApplication.java
├── auth
├── config
├── framework
└── system
```

### 项目环境 
名称 | 版本 |  备注
-|-|-
JDK | 1.8+ | JDK1.8及以上 |
MySQL | 5.7+ | 5.7及以上 |
Redis | 3.2+ |  |

### 技术选型 
技术 | 版本 |  备注
-|-|-
spring boot | 2.7.12 |
mybatis-plus | 3.5.3.1 | mybatis增强框架 |
fastjson2 | 2.0.33 | JSON处理工具集 |
Swagger2 | V3 | Swagger文档 |
knife4j | 4.1.0 | api文档生成工具 |
commons-lang3 | 3.12.0 | 常用工具包 |
commons-io | 2.11.0 | IO工具包 |
commons-codec | 1.15 | 加密解密等工具包 |
commons-collections4 | 4.4.4 | 集合工具包 |
hibernate-validator | 6.2.5.Final | 后台参数校验注解 |
hutool-all | 5.8.16 | 常用工具集 |
lombok | 1.18.26 | 注解生成Java Bean等工具 |

### 项目链路图
![项目调用链路图](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/spring-boot-plus-link-diagram.jpg)

### [CHANGELOG](https://github.com/geekidea/spring-boot-plus/blob/master/CHANGELOG.md)

## 快速开始
### 克隆 spring-boot-plus
```bash
git clone https://gitee.com/geekidea/spring-boot-plus.git
cd spring-boot-plus
```

### Maven 构建
> 默认使用dev环境,对应配置文件：application-dev.yml

```bash
mvn clean package -Pdev
```


## 5分钟完成增删改查

### 1. 创建数据库表
```sql
-- ----------------------------
-- Table structure for foo_bar
-- ----------------------------
DROP TABLE IF EXISTS `foo_bar`;
create table foo_bar
(
    id          bigint                               not null comment '主键'
        primary key,
    name        varchar(20)                          not null comment '名称',
    foo         varchar(100)                         null comment 'Foo',
    bar         varchar(100)                         null comment 'Bar',
    remark      varchar(200)                         null comment '备注',
    status      tinyint(1) default 1                 not null comment '状态，0：禁用，1：启用',
    create_time timestamp  default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                            null comment '修改时间'
)
    comment 'FooBar';

-- ----------------------------
-- Records of foo_bar
-- ----------------------------
INSERT INTO foo_bar (id, name, foo, bar, remark, status, create_time, update_time) VALUES (1, 'FooBar', 'Foo', 'Bar', null, 1, '2023-07-01 21:01:10', null);

```


### 2.使用代码生成器生成增删改查代码
> 代码生成入口类，在generator模块中

```text
src/test/java/io/geekidea/boot/generator/Generator.java
```

```java
/**
 * Spring Boot Plus 代码生成器
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

#### 生成的代码结构

```text
├── controller
│ └── FooBarController.java
├── dto
│ ├── FooBarAddDto.java
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
    ├── FooBarInfoVo.java
    └── FooBarVo.java

resources
└── mapper
    └── foobar
        └── FooBarMapper.xml    
    
```


#### 代码生成模版
> 使用Velocity模版生成代码，可自定义修改代码生成模版

```text
src/test/resources
└── templates
    ├── addDto.java.vm          添加DTO代码生成模版
    ├── controller.java.vm      控制器代码生成模版
    ├── entity.java.vm          实体类代码生成模版
    ├── infoVo.java.vm          详情VO代码生成模版
    ├── mapper.java.vm          Mapper代码生成模版
    ├── mapper.xml.vm           Mapper xml 代码生成模版
    ├── query.java.vm           分页参数代码生成模版
    ├── service.java.vm         服务接口代码生成模版
    ├── serviceImpl.java.vm     服务实现代码生成模版
    ├── updateDto.java.vm       修改DTO代码生成模版
    └── vo.java.vm              列表VO代码生成模版
```


### 3. 启动项目
> 项目入口类: SpringBootPlusApplication  [http://localhost:8888](http://localhost:8888)

```text
src/main/java/io/geekidea/boot/SpringBootPlusApplication.java
```

```java
/**
 * 启动类
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

### 4. 访问项目Swagger文档
[http://localhost:8888/swagger-ui/index.html](http://localhost:8888/swagger-ui/index.html)
![swagger-ui.png](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/swagger-ui.png)

### 5. 访问Knife4j文档
[http://localhost:8888/doc.html](http://localhost:8888/doc.html)
![knife4j.png](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/knife4j.png)

## spring-boot-plus Views

### spring-boot-plus IDEA Sources Views

![spring-boot-plus-idea](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/idea.png)

## spring-boot-plus-vue 前端项目
### [GITHUB-REPO](https://github.com/geekidea/spring-boot-plus-vue)
### [VUE演示地址](http://localhost/)
#### VUE主页
![VUE主页](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/springbootplusvue.png)
#### 系统用户列表
![系统用户列表](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/springbootplusvue-1.png)
#### 系统角色列表
![系统角色模块](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/springbootplusvue-2.png)
#### 系统菜单列表
![系统菜单列表](https://geekidea.oss-cn-chengdu.aliyuncs.com/spring-boot-plus/img/springbootplusvue-3.png)

## spring-boot-plus 视频  :movie_camera:

## 联系
QQ 625301326| 微信公众号 geekideaio|  今日头条 GeekIdea
-|-|-
![spring-boot-plus QQ Group](https://spring-boot-plus.gitee.io/img/spring-boot-plus-qq-group.png) | ![Wechat Official Account](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/geekidea-wechat-official.jpg) | ![toutiao](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/geekidea-toutiao.jpeg) |

## 赞赏
请作者喝咖啡，让代码飞一会儿！
                          
![geekidea-wechat-donate](https://geekidea.oss-cn-chengdu.aliyuncs.com/geekidea/geekidea-wechat-donate.jpeg)

## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.

