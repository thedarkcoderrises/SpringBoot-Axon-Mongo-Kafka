package tdcr.axon.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserCreatedEvent;
import tdcr.axon.command.event.UserUpdatedEvent;

@Component
public class UserEventListerner {

    @KafkaListener(topics = "CreateUserTopic",containerFactory = "kafkaListenerCreateContainerFactory")
    public void consume(UserCreatedEvent event) {
        System.out.println("UserCreatedEvent message: " + event.toString());
    }


    @KafkaListener(topics = "UpdateUserTopic",containerFactory = "kafkaListenerUpdateContainerFactory")
    public void consume(UserUpdatedEvent event) {
        System.out.println("UserUpdatedEvent message: " + event.toString());
    }
}
