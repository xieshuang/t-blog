# 个人博客
[![](https://img.shields.io/badge/JDK-17-green.svg "JDK 17")]()
[![](https://img.shields.io/badge/spring--boot-3.2.x-green.svg)]()

相信每个开发者都希望拥有一个属于自己的博客，该项目足够的简单，搭建环境很简单，只需要一个数据库和jdk环境(如果不想安装redis，可以直接把logback配置改成生成文件)。有问题可以给我留言。

### 1.涉及技术及工具

- 核心框架：SpringBoot 3.2.5
- ORM 框架：MyBatis 3.0.3
- MyBatis 工具：MyBatis通用Mapper插件
- MVC 框架：Spring MVC
- 模板引擎：Thymeleaf
- Markdown 编辑器：Editor.md
- 数据库：MySQL、Liquibase

### 2.部署事宜

- 1、服务器安装mysql并创建空的数据库blog
- 2、项目打成jar包上传到服务器上
- 3、执行 `nohup java -jar blog.jar &`(启动项目后会自动初始化相关表和用户数据)
- 4、浏览器访问 ip:8080即可访问   
   管理后台地址为 ip:8080/admin 默认用户admin 密码123456
   
- 5、可以配置nginx进行代理访问

### 3.其他碎碎念
该博客参考了[My blog](https://github.com/ZHENFENG13/My-Blog)。  
在原博客的基础上，做了两点修改，虽然对博客系统用处不大，属于个人的一种尝试，希望以后的项目中能够用上。  
1、引入了`liquibase`数据动态迁移，方便数据库的持续集成。  

加入的这点其实也蛮有意思的，首先是`liquibase`,原项目中使用的是容器化技术，通过脚本的形式去执行初始sql语句，容器技术我只是稍微了解，还没有去尝试写过dockfile，暂时不用这种方式，然后我想起来之前项目中接触过liquibase,但是那次没有本地没有用成功，这次我就想自己用一次，看了一下[官方文档](http://www.liquibase.org))，终于配置进来了，还是很方便好用的。  

最终我部署在了腾讯的centos云服务器上了，感觉还不错~。欢迎访问！http://139.199.125.93/

### 4.环境要求

- JDK 17+
- MySQL 8.0+

【更新记录】

##### 2026-03
1. 升级 Spring Boot 从 2.7.9 到 3.2.5
2. 升级 Java 从 1.8 到 17
3. 升级 MyBatis 从 1.3.2 到 3.0.3
4. 升级 PageHelper 从 1.4.7 到 2.1.0
5. 升级 Druid 从 1.0.18 到 1.2.21
6. 升级 Jedis 从 2.5.2 到 5.1.0
7. 升级 Lombok 从 1.18.28 到 1.18.30
8. 移除过时的 logback-redis-appender 依赖
9. 迁移 javax.* 到 jakarta.* (Jakarta EE 9+)

##### 2023-07
1. 更新MySQL驱动版本到8x
2. 更新springboot版本到2.7x
3. 更新liquibase版本到1.4x
4. 更新分页插件版本
