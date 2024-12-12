FROM maven:3.9.6-eclipse-temurin-22-jammy as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:23-jdk
COPY --from=build /target/websocket-chat-0.0.1-SNAPSHOT.jar websocket-chat.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocket-chat.jar"]