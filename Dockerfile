
# Stage 1: Build the calculator with Maven and JDK 21
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory
WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Copy source code
COPY src ./src

# Build and test the application
RUN mvn clean package

# Stage 2: Use a Java 21 runtime image
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the executable JAR from the build stage
COPY --from=builder /app/target/calculator-1.0.0.jar /app/calculator.jar

# Start the calculator
ENTRYPOINT ["java", "-jar", "/app/calculator.jar"]
