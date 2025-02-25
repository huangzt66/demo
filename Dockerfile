# 使用OpenJDK 21作为基础镜像
FROM openjdk:21

# 设置工作目录
WORKDIR /app

# 将打包好的jar文件复制到工作目录
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]