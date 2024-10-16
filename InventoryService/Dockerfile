FROM eclipse-temurin:17-jdk-alpine


ADD target/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar


ENTRYPOINT java -javaagent:/opentelemetry-javaagent.jar -jar /app.jar









