# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-jdk

# Set working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/TodoApplication-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
