FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

## Copy pom.xml and install dependencies (for Maven)
#COPY pom.xml .
#RUN ./mvnw dependency:go-offline

# Copy source code and build the app (for Maven)
#COPY src /app/src
RUN ./mvnw clean package

# Copy the JAR file from the target folder
COPY target/*.jar app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
