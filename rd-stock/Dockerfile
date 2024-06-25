FROM openjdk:11
EXPOSE 8080
COPY target/rd-stock-1.0.jar rd-stock.jar
ENTRYPOINT java -Dspring.profiles.active=$SPRING_PROFILE -jar /rd-stock.jar