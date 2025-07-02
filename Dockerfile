# Multi-stage build for Spring Boot application with Java 22
FROM maven:3.9.9-eclipse-temurin-22 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies (for better caching)
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:22-jre-jammy

# Create app directory
WORKDIR /app

# Copy the JAR file from build stage
COPY --from=build /app/target/food-facts-backend-*.jar app.jar

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Create non-root user for security
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
