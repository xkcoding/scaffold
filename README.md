# scaffold
> scaffold is some tools just for easy and happy coding!

## 模块介绍
```
├── scaffold-common      通用工具类
├── scaffold-launcher    通用启动器
├── scaffold-log         日志包
├── scaffold-swagger     swagger配置
├── scaffold-test        通用单元测试
├── scaffold-web         通用web配置
```
 
## 安装方式
当前最新版本 [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.xkcoding/scaffold/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.xkcoding/scaffold)
```xml
<dependency>
    <groupId>com.xkcoding</groupId>
    <artifactId>scaffold-${module}</artifactId>
    <version>${scaffold.version}</version>
</dependency>
```
 
## 使用文档
https://xkcoding.com/2019/03/12/scaffold-doc.html

## 更新记录
[CHANGELOG](./CHANGELOG.md) 

## TODO
- **scaffold-cache** 通用基于redis或者ehcache的缓存配置，设计缓存key，保证全系统key统一
- **scaffold-auth** 通用认证授权配置
- **scaffold-code** 通用验证码组件
- **scaffold-notification** 通用通知组件，暂时支持 邮箱、短信、钉钉

---
Thanks [mica](https://github.com/lets-mica/mica)!