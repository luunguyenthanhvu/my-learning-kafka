# Bước 1: Sử dụng Maven để build ứng dụng
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml và src
COPY pom.xml .
COPY src ./src

# Build ứng dụng bằng Maven
RUN mvn clean package -DskipTests

# Bước 2: Sử dụng OpenJDK để chạy ứng dụng
FROM openjdk:21-jdk-alpine
WORKDIR /app

# Copy file JAR đã build
COPY --from=build /app/target/*.jar app.jar

# Đặt cổng mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
