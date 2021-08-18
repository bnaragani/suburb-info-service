#Add Java 8
FROM openjdk:8-jdk-alpine
# assign JAR_FILE to the maven tagetsuburb-service-1.0.0.jar
ARG JAR_FILE=target/suburb-service-1.0.0.jar
# copy the file as app.jar
COPY ${JAR_FILE} app.jar
# run the jar
ENTRYPOINT ["java","-jar","/app.jar"]