# Use Maven to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /demo

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build image
COPY --from=build /app/target/*.jar demo.jar

# Expose the port the application runs on
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "demo.jar"]
