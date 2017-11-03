FROM maven:3-jdk-8-alpine AS BUILD_IMAGE
ENV APP_HOME=/root/dev/poule/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY . .
RUN mvn package

FROM openjdk:8-jre
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/dev/poule/target/poule-1.jar .
EXPOSE 8080
CMD ["java","-jar","poule-1.jar"]