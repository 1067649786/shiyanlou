---
show: step
version: 1.0
enable_checker: false
---

# Spring Security 介绍

## 一、实验介绍

#### 1.1 实验内容

Spring Security 是一个能够为基于 Spring 的企业应用系统提供声明式的安全访问控制解决方案的安全框架，是目前比较热门的一个安全框架。由于后面的实战项目中运用到了 Spring Security，所以在这次课程中将首先为大家讲解 Spring Security 的一些基本用法。

#### 1.2 前置知识点

- Spring Boot
- Maven

#### 1.3 实验知识点

- 什么是 Spring Security
- Spring Security 的优点

#### 1.4 实验环境

- JDK 1.8
- Maven
- SpringBoot2
- WEB IDE

## 二、实验步骤

接下来我们先来简单了解一下 Spring Security

### 2.1 什么是 Spring Security

> Spring Security 对 Web 安全性的支持大量地依赖于 Servlet 过滤器。这些过滤器拦截进入请求，并且在应用程序处理该请求之前进行某些安全处理。 Spring Security 提供有若干个过滤器，它们能够拦截 Servlet 请求，并将这些请求转给认证和访问决策管理器处理，从而增强安全性。根据自己的需要，可以使用适当的过滤器来保护自己的应用程序。

> 如果使用过 Servlet 过滤器且令其正常工作，就必须在 Web 应用程序的 web.xml 文件中使用 filter 和 filter-mapping 元素配置它们。虽然这样做能起作用，但是它并不适用于使用依赖注入进行的配置。

> FilterToBeanProxy 是一个特殊的 Servlet 过滤器，它本身做的工作并不多，而是将自己的工作委托给 Spring 应用程序上下文 中的一个 Bean 来完成。被委托的 Bean 几乎和其他的 Servlet 过滤器一样，实现 javax.servlet.Filter 接口，但它是在 Spring 配置文件而不是 web.xml 文件中配置的。

> 实际上，FilterToBeanProxy 代理给的那个 Bean 可以是 javax.servlet.Filter 的任意实现。这可以是 Spring Security 的任何一个过滤器，或者它可以是自己创建的一个过滤器。但是，Spring Security 要求至少配置四个而且可能一打或者更多的过滤器。

### 2.2 Spring Security 的优点

人们使用 Spring Security 有很多种原因，不过通常吸引他们的是在 J2EE Servlet 规范或 EJB 规范中找不到典型企业应用场景的解决方案。

特别要指出的是他们不能在 WAR 或 EAR 级别进行移植。

这样，如果更换服务器环境，就要，在新的目标环境进行大量的工作，对应用系统进行重新配置安全。

使用 Spring Security 解决了这些问题，也提供很多有用的，完全可以指定的其他安全特性。

可能知道，安全包括两个主要操作。

第一个被称为“认证”，是为用户建立一个他所声明的主体。主体一般是指用户，设备或可以在系统中执行动作的其他系统。

第二个叫“授权”，指的是一个用户能否在应用中执行某个操作，在到达授权判断之前，身份的主体已经由身份验证过程建立。

这些概念是通用的，不是 Spring Security 特有的。

在身份验证层面，Spring Security 广泛支持各种身份验证模式，这些验证模型绝大多数都由第三方提供，或者正在开发的有关标准机构提供的，例如 Internet Engineering Task Force.

作为补充，Spring Security 也提供了自己的一套验证功能。

Spring Security 目前支持认证一体化如下认证技术：

- HTTP BASIC authentication headers (一个基于 IEFT RFC 的标准)
- HTTP Digest authentication headers (一个基于 IEFT RFC 的标准)
- HTTP X.509 client certificate exchange (一个基于 IEFT RFC 的标准)
- LDAP (一个非常常见的跨平台认证需要做法，特别是在大环境)
- Form-based authentication (提供简单用户接口的需求)
- OpenID authentication
- Computer Associates Siteminder
- JA-SIG Central Authentication Service (CAS，这是一个流行的开源单点登录系统)
- Transparent authentication context propagation for Remote Method Invocation and HttpInvoker (一个 Spring 远程调用协议)

## 三、实验总结

本次课程介绍了 Spring Security 的相关知识，为后续的 Spring Security 课程的学习打下了基础。

## 四、参考链接

-[Spring Security](https://springcloud.cc/spring-security-zhcn.html)