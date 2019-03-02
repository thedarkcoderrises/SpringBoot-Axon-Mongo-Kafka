#getting base image
FROM java:8
VOLUME /tmp/ecart
ADD /account/target/account-1.0-SNAPSHOT.jar ecart.jar
EXPOSE 8080
#ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/thedarkcoderrises","-Dkafka.url=kafka:9092","-jar","/ecart.jar"]
ENTRYPOINT ["java","-jar","/ecart.jar"]
RUN pwd
RUN ls -ltr