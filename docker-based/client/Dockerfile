FROM java:8-jre-alpine

COPY ./service-entrypoint.sh /
RUN chmod +x /service-entrypoint.sh

# MUST BE IN CONTEXT PATH SO DOCKERFILE HAS TO BE MOVED TO PROJECT FOLDER
# COPY ../java-based/client/target/client-0.0.1-SNAPSHOT.jar /usr/share/

ENTRYPOINT ["/entrypoint.sh"]