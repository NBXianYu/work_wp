**swagger访问**
http://${host}:${port}/work/doc.html
**druid访问**
http://${host}:${port}/work/druid


**项目说明** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入swagger文档支持，方便编写API接口文档


**技术选型：** 
- 核心框架：Spring Boot 2.0
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：JPA
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.1.10
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 

 **部署**
- 通过github下载源码
- 创建数据库work，数据库编码为UTF-8
- 修改application.yml，使用线上配置prod,更新application-prod.yml中的MySQL账号和密码
- 然后查看src/docker文件夹下面的readme.text文件,docker部署安装流程
- 本项目是前后端分离的，还需要部署前端，才能运行起来(否则只能访问swagger)，账号：admin，密码：pass1234
- 


## 遗留问题
1.权限还未精确到按钮,不完善
2.App权限没有完善，后续添加
3.定时器还不够牛逼，准备添加
