---
show: step
version: 1.0
enable_checker: false
---

# Spring Security 登陆与退出

## 一、实验介绍

#### 1.1 实验内容

我们将在这一节学习使用 Spring Security 进行登陆与退出的验证。

#### 1.2 实验知识点

- 

#### 1.3 实验环境

- JDK 1.8
- Spring Boot2
- Maven 3.2+
- Mybatis

#### 1.4 目录结构



#### 1.5 代码获取



## 二、实验步骤

接下来我们将正式开始学习 Spring Security。非会员无法联网，请按照下面命令下载依赖包。否则后续实验会报错。

**由于 maven 下载包需要联网，这里先将 maven 包下载下来，放到本地仓库便可以解决联网的问题(非会员用户一定要运行这一步，否则无法进行实验)**

```bash
wget http://labfile.oss.aliyuncs.com/courses/1152/m2.zip
unzip m2.zip
mv .m2 /home/shiyanlou/
```

> 关于在线环境的使用遇到任何问题请查阅使用指南：[https://www.shiyanlou.com/questions/89126](https://www.shiyanlou.com/questions/89126)

### 2.1 数据库准备

需要先登录 mysql（确保 mysql 已经启动，使用`sudo service mysql start`命令启动）

登入数据库

```sql
mysql -u root
```

![图片描述](https://doc.shiyanlou.com/courses/uid987099-20190603-1559531386945/wm)

然后建立相应的数据库和表。

```sql
CREATE DATABASE shiyanlou;
USE shiyanlou;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT '' COMMENT '昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

![图片描述](https://doc.shiyanlou.com/courses/uid987099-20190603-1559531681214/wm)

### 2.2 手动构建 Spring Boot 项目

首先我们需要创建一个普通的 maven 项目 `springboot`

```bash
mvn archetype:generate -DgroupId=com.shiyanlou -DartifactId=security -DarchetypeArtifactId=maven-archetype-quickstart
```

创建过程中会提示输入版本号等，直接使用默认即可。接着输入`Y`确定创建

创建完成后切换工作空间到`security`目录，接着建立目录`src/main/resources`，然后在 `resources` 目录下建立 `templates` 目录和 `application.properties` 文件。

添加 maven 依赖，修改 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.shiyanlou</groupId>
	<artifactId>security</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>security</name>
	<description>Demo project for Spring Boot</description>

    <!--设置父模块 这样就可以继承父模块中的配置信息-->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.3.RELEASE</version>
		<relativePath/>
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
    <!--添加web依赖-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
	</dependencies>

	<build>
		<plugins>
        <!--spirng Boot maven插件-->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

### 2.3 实体类

