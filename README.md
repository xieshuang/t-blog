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


### 4.环境要求

- JDK 17+
- MySQL 8.0+

【更新记录】

##### 2026-03
1. 升级 Spring Boot 从 2.7.9 到 3.2.5
2. 升级 Java 从 1.8 到 17
3. 升级 MyBatis 1.3.2 到 3.0.3
4. 升级 PageHelper 1.4.7 到 2.1.0
5. 升级 Druid 1.0.18 到 1.2.21
6. 升级 Jedis 2.5.2 到 5.1.0
7. 升级 Lombok 1.18.28 到 1.18.30
8. 移除过时的 logback-redis-appender 依赖
9. 迁移 javax.* 到 jakarta.* (Jakarta EE 9+)
10. 升级 commons-lang3 到 3.18.0 修复安全漏洞
11. 升级 liquibase 到 4.27.0
12. 升级 logstash-logback-encoder 到 8.0
13. 前端 CDN 从 bootcss.com 替换为 cdnjs.cloudflare.com/unpkg.com
14. 升级 jQuery 2.2.3 到 3.7.1
15. 升级 Bootstrap 3.3.7 到 3.4.1
16. 升级 highlight.js 9.9.0 到 11.9.0
17. 升级 SweetAlert2 6.4.1 到 11.10.0
18. 升级 Dropzone 4.3.0 到 5.9.3
19. 升级 Select2 3.4.8 到 4.0.13
20. 升级 jQuery Validate 1.15.1 到 1.19.5
21. 升级 clipboard.js 1.6.0 到 2.0.11

##### 2023-07
1. 更新MySQL驱动版本到8x
2. 更新springboot版本到2.7x
3. 更新liquibase版本到1.4x
4. 更新分页插件版本
