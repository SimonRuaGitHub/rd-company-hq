#Setting up stage to generate RD-STOCK JAR package
FROM maven:3.9.6-amazoncorretto-11
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
#Running Jar and exposing 8080 port
EXPOSE 8080
ENTRYPOINT java -Dspring.profiles.active=$SPRING_PROFILE -jar target/rd-stock-1.0.jar