FROM java:8-jre

ADD poule-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]


#COPY ./docker-entrypoint.sh /
#RUN chmod +x /docker-entrypoint.sh

# COPY ./target/poule-0.0.1-SNAPSHOT.jar /usr/share/

# ENTRYPOINT "/docker-entrypoint.sh"
