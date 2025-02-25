### 访问地址
https://localhost:8080
### 打包
```
mvn clean package
```
### 构建镜像
```
docker build -t demo:0.0.1 .
```
### Jmeter压测结果
压测配置：jmeter/demo.jmx