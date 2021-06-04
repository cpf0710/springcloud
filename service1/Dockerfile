#这是基础镜像
FROM java:8
VOLUME /tmp
#复制jar包到镜像中，并且将名字改成app.jar
ADD /target/*.jar app.jar
#在容器启动的时候运行命令，来启动我们的项目（这其实就是一段Linux命令）
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
