# Use an official Java runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/DowJones-0.0.1-SNAPSHOT.jar /app/DowJones-0.0.1-SNAPSHOT.jar

# Expose the port for the application
EXPOSE 8088

# Environment variables for H2 console (optional)
ENV H2_CONSOLE_ENABLED=true
ENV H2_CONSOLE_PATH=/h2-console

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "DowJones-0.0.1-SNAPSHOT.jar"]