FROM openjdk:8-jre-alpine

# Copy the built jar into the Docker image
COPY poule-0.0.1-SNAPSHOT.jar service.jar

# Add execution rule on app.jar
RUN chmod +x service.jar

# Expose a port outside the container
EXPOSE 8080

# Specify the command to run on startup
ENTRYPOINT [ "sh", "-c", "java -jar /service.jar" ]