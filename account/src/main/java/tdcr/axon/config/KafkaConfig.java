package tdcr.axon.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${kafka.url}")
    String kafkaURL;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public ConsumerFactory<String, Object> cosnumerFactory() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory(config,null,new JsonDeserializer(Object.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Object> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Object> factory =
                new ConcurrentKafkaListenerContainerFactory<String, Object>();
        factory.setConsumerFactory(cosnumerFactory());
        return factory;
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        KafkaTemplate kt = new KafkaTemplate(producerFactory());
        return kt ;
    }
}
