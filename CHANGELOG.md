# 变更记录

## 发行版本

### [1.0.9] - xxxx
#### 修改
**scaffold-codegen** 升级 velocity 依赖 1.7 --> 2.1，解决代码生成排版不美观问题

### [1.0.8] - 2018-03-27
#### 新增
**scaffold-codegen** 代码生成器，仅支持mysql数据库
#### 修改
**scaffold-swagger** 配置类移动包目录

### [1.0.7] - 2018-03-19
#### 修改
**scaffold-notification** 紧急修复 EmailSender 发件人信息的bug

### [1.0.6] - 2018-03-18
#### 新增
**scaffold-notification** 通用通知组件，支持短信、钉钉、邮箱
#### 修改
所有模块包名添加一层 `scaffold`

### [1.0.5] - 2018-03-14
#### 新增
**scaffold-code** 通用验证码组件，可以生成两种随机验证码和算术验证码
#### 修改
- **scaffold-log** 提供操作日志开关 `scaffold.log.enabled`

### [1.0.4] - 2018-03-13
#### 新增
**scaffold-bom** 统一管理scaffold模块依赖
#### 修改
- **scaffold-launcher**
类名修改 ~~ScafflodApplication~~ ScaffoldApplication
- **scaffold-common**
R类方法重构
- **scaffold-log**
移除通用web异常处理
- **scaffold-web**
新增通用web异常处理

### [1.0.3] - 2018-03-12
#### 新增
- **scaffold-common**
新增IP工具类，IP转换地址工具类，Servlet工具类，树工具类

### [1.0.2] - 2018-03-11
#### 修改
- **scaffold-web**
①重构 `RequestLogAspect` 构建成一条长 日志，避免并发下日志错乱②日志配置文件移到当前包下

### [1.0.1] - 2018-03-11
#### 修改
- **scaffold-common**
移除 `MyBatis-Plus` 通用配置
- **scaffold-web**
新增 `MyBatis-Plus` 通用配置
- **scaffold-log**
重命名默认日志实现类、登录信息实现类

### [1.0.0] - 2018-03-08
#### 添加
- **scaffold-launcher**
通用启动器，方便设置环境配置
- **scaffold-common**
通用工具类，包含`Controller`层返回，分页查询条件，`Mybatis-Plus`通用配置，以及`logback`等配置等等
- **scaffold-test**
通用单元测试，方便为单元测试类设置环境配置
- **scaffold-log**
日志包，集成操作日志，异常日志处理，用户可自定义实现，便于将操作记录、异常日志记录保存到第三方存储等操作
- **scaffold-swagger**
swagger配置，封装了[Swagger-Bootstrap-UI](https://github.com/xiaoymin/Swagger-Bootstrap-UI)
- **scaffold-web**
通用web配置，包含异步线程池配置，redis序列化配置，jackson配置等等，并且提供了开发环境下的请求监控插件

