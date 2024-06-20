FROM openjdk:11
EXPOSE 8080
ENV HOST_DB_RD_STOCK=mariadb
ENV LOCALSTACK_HOST=localstack
COPY target/rd-stock-1.0.jar rd-stock.jar
ENTRYPOINT ["java","-jar","/rd-stock.jar"]