FROM amazoncorretto:11-alpine
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
EXPOSE 8082
ENTRYPOINT ["./mvnw", "spring-boot:run"]