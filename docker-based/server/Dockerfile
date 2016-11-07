FROM java:8-jre-alpine

COPY ./docker-based/server/service-entrypoint.sh /
RUN chmod +x /service-entrypoint.sh

COPY ./java-based/server/target/server-0.0.1-SNAPSHOT.jar /usr/share/

ENTRYPOINT ["/service-entrypoint.sh"]