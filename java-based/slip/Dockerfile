FROM java:8-jre-alpine

COPY ./docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

COPY ./target/slip-0.0.1-SNAPSHOT.jar /usr/share/

EXPOSE 8080

ENTRYPOINT ["/docker-entrypoint.sh"]