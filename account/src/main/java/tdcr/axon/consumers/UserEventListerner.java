package tdcr.axon.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserCreatedEvent;
import tdcr.axon.command.event.UserUpdatedEvent;

@Component
public class UserEventListerner {

    @KafkaListener(topics = "demo", group = "group_id")
    public void consume(Object event) {
        System.out.println("Consumed message: " + event.toString());
    }
}
