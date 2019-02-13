package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserUpdatedEvent;

@Component
public class UserUpdatedEventListener {
    @Autowired
    KafkaTemplate kafkaTemplate;


    @EventHandler
    public void on(UserUpdatedEvent userUpdatedEvent){
        kafkaTemplate.send("demo",""+System.currentTimeMillis(), userUpdatedEvent);
        System.out.println("To save in DB : "+ userUpdatedEvent.toString());
    }

}
