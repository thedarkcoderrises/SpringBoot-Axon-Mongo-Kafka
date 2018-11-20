package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserCreatedEvent;

import javax.validation.constraints.Null;

@Component
public class UserCreatedEventListener {

    KafkaTemplate<String, Object> kafkaTemplate;

    public UserCreatedEventListener(KafkaTemplate kt) {
        this.kafkaTemplate=kt;
    }

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
//        kafkaTemplate.send("demo",userCreatedEvent);
        System.out.println("To save in DB : "+ userCreatedEvent.getUserId());
    }

}
