#getting base image
FROM java:8
VOLUME /tmp
ADD /account/target/account-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/thedarkcoderrises","-Dkafka.url=kafka:9092","-jar","/app.jar"]
RUN pwd
RUN ls -ltr