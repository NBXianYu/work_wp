#FROM java:8
#VOLUME /tmp
##ADD spider-1.5.2.RELEASE.jar app.jar
#RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器
ADD work-1.0.5.jar app.jar
RUN bash -c 'touch /app.jar'

# 开放8081端口
EXPOSE 8081

# 配置容器启动后执行的命令
ENTRYPOINT ["java", "-jar", "/app.jar"]
