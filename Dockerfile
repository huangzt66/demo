# 使用官方的 OpenJDK 21 基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 将打包好的 JAR 文件复制到容器的工作目录
COPY target/transaction-management-app.jar /app/transaction-management-app.jar

# 暴露应用运行的端口，Spring Boot 默认端口是 8080
EXPOSE 8080

# 定义容器启动时执行的命令，运行 Spring Boot 应用
CMD ["java", "-jar", "transaction-management-app.jar"]