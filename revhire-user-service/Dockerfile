# Build stage
FROM public.ecr.aws/docker/library/maven:3.8-eclipse-temurin-17 AS build
COPY src/ /home/app/src
COPY pom.xml /home/app/pom.xml
WORKDIR /home/app
RUN mvn clean package -DskipTests

# Runtime stage
FROM public.ecr.aws/docker/library/openjdk:17
COPY --from=build /home/app/target/*.jar revhire-user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/revhire-user-service.jar"]