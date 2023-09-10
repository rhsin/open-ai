FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dspring.datasource.url=${H2_URL} -Dspring.datasource.username=${H2_USER} -Dspring.datasource.password=${H2_PASSWORD} -jar /app.jar"]