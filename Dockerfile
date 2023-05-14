#FROM --platform=linux/amd64 gradle:8-jdk17-alpine AS build
#
#COPY --chown=gradle:gradle . /home/gradle/src
#
#WORKDIR /home/gradle/src
#
#RUN gradle build --no-daemon
##RUN ./gradlew build

#FROM amazoncorretto:17-alpine-jdk
#
#COPY --chown=app:app . /app
#
#WORKDIR /app
#
#RUN ./gradlew build
#
#EXPOSE 8080
#
#COPY /app/build/libs/*.jar /app/spring-boot-application.jar
#
#ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]