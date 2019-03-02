#clear
#cp /Users/javabrain/Documents/MyWorkspace/AxonEcart/dockercompose.sh /Users/javabrain/Documents/Docker/

docker rm -f ecart
docker rm -f mongo
docker rm -f kafka
docker rm -f zookeeper

#rm -rf /Users/javabrain/Documents/Docker/logs/*

#rm -rf /Users/javabrain/Documents/Docker/kafka/logs/*

docker network create bunit

docker run -d -p 27017:27017 -v /home/ec2-user/mongodb:/data/db --name mongo mongo
#docker restart mongo
docker network connect bunit mongo

docker run -d --name zookeeper -p 2181:2181 -v /home/ec2-user/kafka/ZK_Conf:/opt/zookeeper-3.4.13/conf -v /home/ec2-user/kafka/ZK_Data:/opt/zookeeper-3.4.13/data wurstmeister/zookeeper
#docker restart zookeeper
docker network connect bunit zookeeper

docker run -d --name kafka -p 9092:9092 -v /var/run/docker.sock:/var/run/docker.sock -v /home/ec2-user/kafka/logs/:/opt/kafka/logs -e KAFKA_ADVERTISED_HOST_NAME=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true --link=zookeeper wurstmeister/kafka
#docker restart kafka
docker network connect bunit kafka

docker run -d -p 8081:8080 -v /home/ec2-user/logs:/logs -e spring.data.mongodb.uri=mongodb://mongo/thedarkcoderrises -e kafka.url=kafka:9092 --name ecart --link=mongo --link=kafka ecart:1.0
docker network connect bunit ecart

docker ps
