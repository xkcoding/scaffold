# 变更记录

## 发行版本
### [1.0.1] - 2018-03-11
#### 修改
- **scaffold-common**
移除 MyBatis-Plus 通用配置
- **scaffold-web**
新增 MyBatis-Plus 通用配置
- **scaffold-log**
重命名默认日志实现类、登录信息实现类

### [1.0.0] - 2018-03-08
#### 添加
- **scaffold-launcher**
通用启动器，方便设置环境配置
- **scaffold-common**
通用工具类，包含Controller层返回，分页查询条件，Mybatis-Plus通用配置，以及logback等配置等等
- **scaffold-test**
通用单元测试，方便为单元测试类设置环境配置
- **scaffold-log**
日志包，集成操作日志，异常日志处理，用户可自定义实现，便于将操作记录、异常日志记录保存到第三方存储等操作
- **scaffold-swagger**
swagger配置，封装了[Swagger-Bootstrap-UI](https://github.com/xiaoymin/Swagger-Bootstrap-UI)
- **scaffold-web**
通用web配置，包含异步线程池配置，redis序列化配置，jackson配置等等，并且提供了开发环境下的请求监控插件
