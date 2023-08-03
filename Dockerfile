FROM openjdk:8
ADD target/*.jar app.jar
RUN echo "Asia/Shanghai" > /etc/timezone
ENV JAVA_OPTS="-Xms512m -Xmx512m"
ENTRYPOINT ["/bin/sh", "-c","java -jar ${JAVA_OPTS} app.jar"]
