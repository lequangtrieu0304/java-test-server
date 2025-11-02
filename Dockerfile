# Giai đoạn 1: Build ứng dụng bằng Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy file pom.xml trước để cache dependency (tăng tốc khi build lại)
COPY pom.xml .

# Tải dependencies offline, tránh tải lại mỗi lần đổi code
RUN mvn dependency:go-offline -B

# Copy toàn bộ source code vào container
COPY src ./src

# Build package, bỏ test cho nhanh
RUN mvn clean package -DskipTests

# Giai đoạn 2: Chạy ứng dụng (runtime)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy file jar từ builder stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
