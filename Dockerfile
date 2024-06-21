FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY ./build/libs/elemental_concept-0.0.1-SNAPSHOT.jar /app/elemental.jar

EXPOSE 8080

CMD ["java", "-jar", "elemental.jar"]



