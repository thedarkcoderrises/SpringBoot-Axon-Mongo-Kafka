cd /home/ec2-user/


#docker run -d -p 27017:27017 -v ${PWD}/mongodb:/data/db --name mongo mongo
docker restart mongo

#docker run -d --name zookeeper -p 2181:2181 -v ${PWD}/kafka/ZK_Conf:/opt/zookeeper-3.4.13/conf -v ${PWD}/kafka/ZK_Data:/opt/zookeeper-3.4.13/data wurstmeister/zookeeper:latest
docker restart zookeeper

#docker run -d --name kafka -p 9092:9092 -v $PWD/kafka/Kafka_data:/kafka -v /var/run/docker.sock:/var/run/docker.sock -e KAFKA_ADVERTISED_HOST_NAME=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --link=zookeeper wurstmeister/kafka:latest
docker restart kafka

docker run -p 8081:8080 -v /home/ec2-user/ecart/localmount:/tmp -v /home/ec2-user/Ecart/logs:/logs --log-driver json-file --log-opt max-size=20k --log-opt max-file=3  --name ecart --link=mongo --link=kafka axon_ecart:1.0

docker ps
