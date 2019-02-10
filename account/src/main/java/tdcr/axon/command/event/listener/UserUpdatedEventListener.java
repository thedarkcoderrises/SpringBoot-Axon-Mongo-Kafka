package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserUpdatedEvent;

@Component
public class UserUpdatedEventListener {
    KafkaTemplate<String, Object> kafkaTemplate;

    public UserUpdatedEventListener(KafkaTemplate kt) {
        this.kafkaTemplate=kt;
    }

    @EventHandler
    public void on(UserUpdatedEvent userUpdatedEvent){
        kafkaTemplate.send("demo",""+System.currentTimeMillis(), userUpdatedEvent);
        System.out.println("To save in DB : "+ userUpdatedEvent.toString());
    }

}
