package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserCreatedEvent;

import javax.validation.constraints.Null;

@Component
public class UserCreatedEventListener {

    @Autowired
    KafkaTemplate kafkaTemplate;
    private static Logger LOG = LoggerFactory.getLogger(UserCreatedEventListener.class);

    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
        try{
            LOG.info("kafka.send :userCreatedEvent");
            kafkaTemplate.send("CreateUserTopic",""+System.currentTimeMillis(), userCreatedEvent);
            LOG.info("kafka.sent");
        }catch (Exception e){
            LOG.debug("kafka.send UserCreatedEvent :{}",e.getMessage());
        }

        LOG.info("To save in DB : {}", userCreatedEvent.getUserId());
    }

}
