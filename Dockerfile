#Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 as Builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -X -e

#Stage 2: Run the application
FROM eclipse-temurin:21-jdk as Runner
WORKDIR /order_service
COPY --from=Builder /app/target/*.jar order_service.jar
ENTRYPOINT ["java", "-jar", "order_service.jar"]