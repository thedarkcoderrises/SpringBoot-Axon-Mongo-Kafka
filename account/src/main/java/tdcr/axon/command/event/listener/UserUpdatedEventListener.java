package tdcr.axon.command.event.listener;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tdcr.axon.command.event.UserUpdatedEvent;

@Component
public class UserUpdatedEventListener {
    @Autowired
    KafkaTemplate kafkaTemplate;
    private static Logger LOG = LoggerFactory.getLogger(UserUpdatedEventListener.class);

    @EventHandler
    public void on(UserUpdatedEvent userUpdatedEvent){
        try{
            kafkaTemplate.send("UpdateUserTopic",""+System.currentTimeMillis(), userUpdatedEvent);
        }catch (Exception e){
            LOG.debug("kafka.send UserUpdatedEvent :{}",e.getMessage());
        }

        LOG.info("To save in DB : "+ userUpdatedEvent.toString());
    }

}
