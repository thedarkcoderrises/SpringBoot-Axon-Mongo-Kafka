docker rm -f ecart
docker rm -f mongo
docker rm -f kafka
docker rm -f zookeeper


docker run -d -p 27017:27017 -v /home/ec2-user/mongodb:/data/db --name mongo mongo
#docker restart mongo

docker run -d --name zookeeper -p 2181:2181 -v /home/ec2-user/kafka/ZK_Conf:/opt/zookeeper-3.4.13/conf -v /home/ec2-user/kafka/ZK_Data:/opt/zookeeper-3.4.13/data wurstmeister/zookeeper:latest
#docker restart zookeeper

docker run -d --name kafka -p 9092:9092 -v /home/ec2-user/kafka/Kafka_data:/kafka -v /var/run/docker.sock:/var/run/docker.sock -v /home/ec2-user/kafka/logs/:/opt/kafka/logs -e KAFKA_ADVERTISED_HOST_NAME=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --link=zookeeper wurstmeister/kafka:latest
#docker restart kafka

docker run -d -p 8081:8080 -v /home/ec2-user/logs:/logs --name ecart --link=mongo --link=kafka ecart:1.0

docker ps
