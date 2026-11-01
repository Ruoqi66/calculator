# Build stage: use official Maven image to compile and package the Java backend project
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory inside the build container
WORKDIR /app

# Copy pom.xml first to take advantage of Docker layer caching for Maven dependencies
COPY pom.xml .

# Copy all source code into the build environment
COPY src ./src

# Package the application; skip test compilation and execution to avoid JUnit dependency issues
RUN mvn clean package -Dmaven.test.skip=true

# Runtime stage: use lightweight JRE image to reduce the size of the final image
FROM eclipse-temurin:21-jre

# Set the working directory for the runtime container
WORKDIR /app

# Copy the packaged jar file from the builder stage into the runtime image
COPY --from=builder /app/target/calculator-1.0.0.jar app.jar

# Expose port 8080 which the Spring Boot application listens on
EXPOSE 8080

# Define the entry point command to start the backend service
ENTRYPOINT ["java", "-jar", "app.jar"]
