# Use lightweight JRE image only
FROM eclipse-temurin:17-jre-jammy

# Set work directory
WORKDIR /app

# Copy your already-built JAR (from local target folder)
COPY target/course-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","/app/app.jar"]