# 基于哪个镜像
FROM java:8
# 维护者的信息
MAINTAINER 1419914659@qq.com
# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器
ADD work-1.1.0.jar app.jar
RUN bash -c 'touch /app.jar'

# 开放8081端口
EXPOSE 8081

# 配置容器启动后执行的命令
ENTRYPOINT ["java", "-jar", "/app.jar"]
