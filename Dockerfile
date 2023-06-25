FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dspring.datasource.url=$POSTGRES_URL -Dspring.datasource.username=$POSTGRES_USER -Dspring.datasource.password=$POSTGRES_PASSWORD -jar /app.jar"]