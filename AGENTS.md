# AGENTS.md - T-Blog 开发指南

本文档为 AI Agent 提供项目开发规范和操作指南。

## 1. 项目概述

- **项目类型**: Spring Boot 2.7.9 + MyBatis 个人博客系统
- **Java 版本**: 1.8
- **主要依赖**: Spring Boot, MyBatis, Thymeleaf, MySQL, Redis, Liquibase
- **包结构**: `com.xsh.blog.*`

## 2. 构建与运行命令

```bash
# 编译项目
mvn clean compile

# 打包 (生成 target/blog.jar)
mvn clean package -DskipTests

# 运行项目
mvn spring-boot:run

# 运行测试
mvn test

# 运行单个测试类
mvn test -Dtest=TestClassName

# 运行单个测试方法
mvn test -Dtest=TestClassName#testMethodName

# 跳过测试打包
mvn clean package -DskipTests
```

## 3. 代码风格规范

### 3.1 命名规范

| 类型 | 规则 | 示例 |
|------|------|------|
| 包名 | 全小写 | `com.xsh.blog.controller` |
| 类名 | PascalCase | `IndexController` |
| 接口名 | I开头 | `IContentService` |
| 方法/变量 | camelCase | `getContents` |
| 常量 | 全大写+下划线 | `MAX_PAGE` |

### 3.2 分层结构

```
src/main/java/com/xsh/blog/
├── controller/     # 控制层 (@Controller)
├── service/        # 业务层 (I接口 + impl实现)
├── dao/            # MyBatis Mapper
├── model/Vo/       # 实体对象
├── model/Bo/       # 业务对象
├── dto/            # 数据传输对象
├── constant/      # 常量
├── exception/     # 异常处理
└── utils/         # 工具类
```

### 3.3 注解使用

```java
@Controller
@Slf4j
public class IndexController extends BaseController {
    @Resource
    private IContentService contentService;
}

@Service
@Slf4j
public class ContentServiceImpl implements IContentService {
    @Resource
    private ContentVoMapper contentDao;
}
```

- Controller: `@Controller` (页面返回视图名)
- Service: `@Service`
- 依赖注入: `@Resource` (javax.annotation)
- 日志: Lombok `@Slf4j`
- 事务: `@Transactional`

### 3.4 返回值规范

- 页面请求: 返回视图名字符串 `return "index"`
- AJAX请求: 使用 `RestResponseBo` 返回 JSON

```java
@GetMapping(value = "/")
public String index(HttpServletRequest request) {
    return this.render("index");
}

@PostMapping(value = "comment")
@ResponseBody
public RestResponseBo comment(...) {
    return RestResponseBo.ok();
    // 或返回错误
    return RestResponseBo.fail("错误信息");
}
```

### 3.5 异常处理

```java
// 抛出业务异常
throw new BusinessException("错误信息");

// 全局异常处理
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public String businessException(Exception e) {
        LOGGER.error("find exception:e={}", e.getMessage());
        return "comm/error_500";
    }
}
```

### 3.6 数据库操作

```java
// 分页查询
PageHelper.startPage(page, limit);
List<ContentVo> data = contentDao.selectByExampleWithBLOBs(example);
PageInfo<ContentVo> pageInfo = new PageInfo<>(data);

// 条件查询
ContentVoExample example = new ContentVoExample();
example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
```

### 3.7 导入顺序

1. Java/Javax 包
2. Spring 框架包
3. 第三方库 (按字母顺序)
4. 项目内部包

### 3.8 代码格式

- 大括号: 左括号不换行
- 缩进: 4 空格
- 行长度: 建议不超过 120 字符
- 注释: 类和方法使用中文 Javadoc

## 4. 模板与资源

- 模板: `src/main/resources/templates/`
- 静态资源: `src/main/resources/static/`
- 数据库迁移: `src/main/resources/liquibase/`

## 5. 开发注意事项

1. **数据库**: 修改 `application.yml` 中的连接信息
2. **Liquibase**: 首次启动自动执行数据库迁移
3. **Redis**: 可选，不配置不影响运行
4. **后台入口**: `/admin` (默认账号: admin, 密码: 123456)
