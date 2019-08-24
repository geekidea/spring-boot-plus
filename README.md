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
    <img alt="spring boot version" src="https://img.shields.io/badge/spring%20boot-2.1.6.RELEASE-brightgreen">
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

## DOCS
#### [GITHUB](https://github.com/geekidea/spring-boot-plus) | [GITEE](https://gitee.com/geekidea/spring-boot-plus)

#### Website：[springboot.plus](http://springboot.plus "springboot.plus")

# Architecture
![spring-boot-plus-architecture.png](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-architecture.png)


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

## Quick Start
[Quick Start](https://springboot.plus/guide/quick-start.html)

## Documentation
 [https://springboot.plus](https://springboot.plus)

## Contact
- Gmail: [springbootplus@gmail.com](mailto:springbootplus@gmail.com)
- QQ Group

![spring-boot-plus QQ Group](https://raw.githubusercontent.com/geekidea/spring-boot-plus/master/docs/img/spring-boot-plus-qq-group.png)


## License
spring-boot-plus is under the Apache 2.0 license. See the [LICENSE](https://github.com/geekidea/spring-boot-plus/blob/master/LICENSE) file for details.