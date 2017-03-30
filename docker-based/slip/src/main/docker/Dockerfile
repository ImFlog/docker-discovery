FROM openjdk:8-alpine

RUN apk add --update curl && \
    rm -rf /var/cache/apk/*

# Copy the built jar into the Docker image
COPY slip-0.0.1.jar app.jar

# Add execution rule on app.jar
RUN chmod +x app.jar

# Set optional environment variables
ENV JAVA_OPTS=""

# Expose a port outside the container
EXPOSE 8080

# Specify the command to run on startup
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]

# HEALTHCHECK OK DURING BOOT
HEALTHCHECK --interval=2s --retries=3 \
CMD exit $(/usr/bin/curl -fs http://localhost:8080/dockerHealth | 0)
