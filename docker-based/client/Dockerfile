FROM java:8-jre-alpine

COPY ./docker-based/client/service-entrypoint.sh /
RUN chmod +x /service-entrypoint.sh

COPY ./java-based/client/target/client-0.0.1-SNAPSHOT.jar /usr/share/

ENTRYPOINT ["/service-entrypoint.sh"]