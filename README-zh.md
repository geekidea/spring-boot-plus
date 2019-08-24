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
    <img alt="spring-boot-plus version" src="https://img.shields.io/badge/spring--boot--plus-1.2.1--RELEASE-blue">
  </a>

  <a href="https://github.com/spring-projects/spring-boot">
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.1.7.RELEASE-brightgreen">
  </a>
  
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square">
  </a>
</p>

### spring-boot-plus是一套集成spring boot常用开发组件的后台快速开发框架
> Spring-Boot-Plus是易于使用，快速，高效，功能丰富，开源的spring boot 脚手架.

## 目标
> 每个人都可以独立、快速、高效地开发项目！

## 文档
#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

#### Website：[springboot.plus](http://springboot.plus "springboot.plus")

# 项目架构
![spring-boot-plus-architecture.png](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.png)

### 主要特性
1. 集成spring boot 常用开发组件集、公共配置、AOP日志等
2. 集成mybatis plus快速dao操作
3. 快速生成后台代码: entity/param/vo/controller/service/mapper/xml
4. 集成swagger2，可自动生成api文档
5. 集成jwt、spring security权限控制
6. 集成redis、spring cache、ehcache缓存
7. 集成rabbit/rocket/kafka mq消息队列
8. 集成druid连接池，JDBC性能和慢查询检测
9. 集成spring boot admin，实时检测项目运行情况
10. 使用assembly maven插件进行不同环境打包部署,包含启动、重启命令，配置文件提取到外部config目录

### 项目环境 
中间件 | 版本 |  备注
-|-|-
JDK | 1.8+ | JDK1.8及以上 |
MySQL | 5.7+ | 5.7及以上 |
Redis | 3.2+ |  |

### 技术选型 
技术 | 版本 |  备注
-|-|-
Spring Boot | 2.1.7.RELEASE | 最新发布稳定版 |
Spring Framework | 5.1.9.RELEASE | 最新发布稳定版 |
Mybatis | 3.5.1 | 持久层框架 |
Mybatis Plus | 3.1.2 | mybatis增强框架 |
Alibaba Druid | 1.1.18 | 数据源 |
Fastjson | 1.2.59 | JSON处理工具集 |
swagger2 | 2.6.1 | api文档生成工具 |
commons-lang3 | 3.9 | 常用工具包 |
commons-io | 2.6 | IO工具包 |
commons-codec | 1.12 | 加密解密等工具包 |
commons-collections | 3.2.1 | 集合工具包 |
reflections | 0.9.11 | 反射工具包 |
hibernate-validator | 6.0.17.Final | 后台参数校验注解 |
jwt | 0.9.1 | json web token |
hutool-all | 4.5.10 | 常用工具集 |


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

### 项目入口类
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

## 快速开始
[快速开始](https://springboot.plus/guide/quick-start.html)

## 详细文档
 [https://springboot.plus](https://springboot.plus)

## 联系
- Gmail: [springbootplus@gmail.com](mailto:springbootplus@gmail.com)
- spring-boot-plus技术交流群

![spring-boot-plus QQ Group](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-qq-group.png)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.
