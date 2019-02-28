package tdcr.axon.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import tdcr.axon.command.event.UserCreatedEvent;
import tdcr.axon.command.event.UserUpdatedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${kafka.url}")
    String kafkaURL;

    private static Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        LOG.info("kafka.ur: {}",kafkaURL);
        Map<String, Object> config = new HashMap();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory(config);
    }

    private Map<String, Object> consumerConfig() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return  config;
    }

    public ConsumerFactory<String, UserCreatedEvent> cosnumerCreateFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfig(),new StringDeserializer(),new JsonDeserializer(UserCreatedEvent.class));
    }

    public ConsumerFactory<String, UserUpdatedEvent> cosnumerUpdateFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfig(),new StringDeserializer(),new JsonDeserializer(UserUpdatedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,UserCreatedEvent> kafkaListenerCreateContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,UserCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent>();
        factory.setConsumerFactory(cosnumerCreateFactory());
        return factory;
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,UserUpdatedEvent> kafkaListenerUpdateContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,UserUpdatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<String, UserUpdatedEvent>();
        factory.setConsumerFactory(cosnumerUpdateFactory());
        return factory;
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        KafkaTemplate kt = new KafkaTemplate(producerFactory());
        LOG.info("Kafka initialized..");
        return kt ;
    }

//    @Bean
//    public NewTopic createUserTopic(){
//        return new NewTopic("CreateUserTopic",2,(short)1);
//    }
}
