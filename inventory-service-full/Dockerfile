FROM eclipse-temurin:17-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]