FROM openjdk:8-alpine

ADD slip-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
