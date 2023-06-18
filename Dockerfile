# Use a base image with Amazon Corretto JDK 17 and Gradle installed
FROM amazoncorretto:17-alpine AS resolve-dependencies

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files to the working directory
COPY build.gradle .
COPY settings.gradle .

# Copy the Gradle wrapper files to the working directory
COPY gradlew .
COPY gradle ./gradle

# Download and cache the Gradle dependencies
RUN ./gradlew --version
RUN ./gradlew dependencies --no-daemon

# Use a base image with Amazon Corretto JDK 17 and Gradle installed
FROM amazoncorretto:17-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files to the working directory
COPY build.gradle .
COPY settings.gradle .

# Copy the Gradle wrapper files to the working directory
COPY gradlew .
COPY gradle ./gradle

# Copy the application source code to the container
COPY src ./src

# Copy the resolved dependencies from the previous stage
COPY --from=resolve-dependencies /root/.gradle /root/.gradle

# Build the application
RUN ./gradlew clean build --no-daemon

# Use a base image with Amazon Corretto JDK 17
FROM amazoncorretto:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/build/libs/lr20190024-*.jar ./app.jar

# Expose the port on which the application will run
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]