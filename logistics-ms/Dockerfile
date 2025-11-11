# Use official OpenJDK 21 image
FROM amazoncorretto:21.0.9

# Create app directory
WORKDIR /app

# Copy the jar from local target folder to container
COPY target/*.jar app.jar

# Expose Spring Boot port (default 8080)
EXPOSE 8081

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
