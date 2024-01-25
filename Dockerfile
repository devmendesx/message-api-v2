# FROM openjdk:17-oracle

# RUN ./gradlew clean buildDependents
# RUN mkdir /app
# #COPY build/libs/message-api-v2-0.0.1-SNAPSHOT-plain.jar message-api-v2-0.0.1.jar
# #ENTRYPOINT ["java","-jar","message-api-v2-0.0.1.jar"]
# #
# #RUN mkdir /app
# COPY build/libs/*.jar /app/app.jar

# EXPOSE 9090
# ENTRYPOINT ["java","-jar","/app/app.jar", "-Dspring.profiles.active=dev"]



# temp container to build using gradle
FROM gradle AS BUILD
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    
# actual container
FROM openjdk:17-oracle
ENV ARTIFACT_NAME=app.jar
ENV APP=/app
ENV GRADLE=/usr/app
    
WORKDIR $APP
COPY --from=BUILD $GRADLE/build/libs/*.jar $APP/$ARTIFACT_NAME
    
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} -Dspring.profiles.active=dev