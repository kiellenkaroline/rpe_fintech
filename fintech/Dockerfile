FROM gradle:8.6.0-jdk21 AS builder
WORKDIR /app

COPY . .

RUN gradle clean bootJar --no-daemon

FROM amazoncorretto:21
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
