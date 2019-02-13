package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserCreatedEvent;

import javax.validation.constraints.Null;

@Component
public class UserCreatedEventListener {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
        kafkaTemplate.send("demo",""+System.currentTimeMillis(), userCreatedEvent);
        System.out.println("To save in DB : "+ userCreatedEvent.getUserId());
    }

}
