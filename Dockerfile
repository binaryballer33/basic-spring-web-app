FROM amazoncorretto:17.0.7-alpine

LABEL maintainer="https://github.com/binaryballer33"

LABEL authors="shaquillemandy"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=target/BasicSpringMVCWebAppMaven-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
