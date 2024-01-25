FROM openjdk:17-oracle

RUN mkdir /app
#COPY build/libs/message-api-v2-0.0.1-SNAPSHOT-plain.jar message-api-v2-0.0.1.jar
#ENTRYPOINT ["java","-jar","message-api-v2-0.0.1.jar"]
#
#RUN mkdir /app
COPY build/libs/*.jar /app/spring-boot-application.jar

EXPOSE 9090
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
