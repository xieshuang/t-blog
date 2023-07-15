# 个人博客
[![](https://img.shields.io/badge/JDK-1.8-green.svg "JDK 1.8")]()
[![](https://img.shields.io/badge/spring--boot-2.7x--release-green.svg)]()  

相信每个开发者都希望拥有一个属于自己的博客，该项目足够的简单，搭建环境很简单，只需要一个数据库和jdk环境(如果不想安装redis，可以直接把logback配置改成生成文件)。有问题可以给我留言。
### 1.涉及技术及工具

- 核心框架：SpringBoot
- ORM 框架：MyBatis
- MyBatis 工具：MyBatis通用Mapper插件
- MVC 框架：Spring MVC
- 模板引擎：Thymeleaf
- Markdown 编辑器：Editor.md
- 数据库：MySQL、Liquibase、redis

### 2.部署事宜

- 1、服务器安装mysql并创建空的数据库blog
- 2、安装redis数据库
- 3、项目打成jar包上传到服务器上
- 4、执行 `nohup java -jar blog.jar &`(启动项目后会自动初始化相关表和用户数据)
- 5、浏览器访问 ip:8080即可访问   
     管理后台地址为 ip:8080/admin 默认用户admin 密码123456
     
- 6、可以配置nginx进行代理访问

### 3.其他碎碎念
该博客参考了[My blog](https://github.com/ZHENFENG13/My-Blog)。  
在原博客的基础上，做了两点修改，虽然对博客系统用处不大，属于个人的一种尝试，希望以后的项目中能够用上。  
1、引入了`liquibase`数据动态迁移，方便数据库的持续集成。  
2、引入了`redisAppender`，方便日志集中处理。  

加入的这两点其实也蛮有意思的，首先是`liquibase`,原项目中使用的是容器化技术，通过脚本的形式去执行初始sql语句，容器技术我只是稍微了解，还没有去尝试写过dockfile，暂时不用这种方式，然后我想起来之前项目中接触过liquibase,但是那次没有本地没有用成功，这次我就想自己用一次，看了一下[官方文档](http://www.liquibase.org))，终于配置进来了，还是很方便好用的。  

第二点修改也是有原因的，原项目中使用的是log4j2,个人觉得并不好，springboot中日志配置默认的就是logback，为什么要退而求其次呢？，于是我又去掉了log42,改回了默认配置,但是logback生成的文件路径需要写成绝对路径，相对路径一直报错，没有得到解决，于是我想干脆不生成日志文件了吧，看看 [spring blog](https://spring.io/blog)中是怎么解决了，结果我就看到了这篇文章[Logging to Redis using Spring Boot and Logback](https://www.javacodegeeks.com/2015/01/logging-to-redis-using-spring-boot-and-logback.html),非常的nice！如果是多台服务器，将日志通过这种形式集中起来管理将会大大降低管理的难度，后面我又继续看了几篇文章，了解了ElasticSearch, logstash, kibana。我突然意识到看的这些其实就是`ELK`框架，很尴尬的是小白表示以前以为ELK是一个工具，而不是一套的缩写，哈哈哈哈！
最终我部署在了腾讯的centos云服务器上了，感觉还不错~。欢迎访问！http://139.199.125.93/

【更新记录】
##### 2023-07
1. 更新MySQL驱动版本到8x
2. 更新springboot版本到2.7x
3. 更新liquibase版本到1.4x
4. 更新分页插件版本