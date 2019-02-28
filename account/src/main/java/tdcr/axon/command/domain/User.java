package tdcr.axon.command.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tdcr.axon.command.CreateUserCommand;
import tdcr.axon.command.event.UserCreatedEvent;
import tdcr.axon.command.event.UserUpdatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Aggregate
public class User {

    @AggregateIdentifier
    private String userId;
    private String name;
    private static Logger LOG = LoggerFactory.getLogger(User.class);

    public User(){}

    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        this.userId= createUserCommand.getUserId();
        apply( new UserCreatedEvent(createUserCommand.getUserId()));
        LOG.info("CreateUserCommand..");
    }

    @EventSourcingHandler
    public void on (UserCreatedEvent userCreatedEvent){
        this.userId = userCreatedEvent.getUserId();
        LOG.info("userCreatedEvent is here");
    }

    @EventSourcingHandler
    public void on (UserUpdatedEvent userUpdatedEvent){
        this.userId = userUpdatedEvent.getUserId();
        this.name = userUpdatedEvent.getName();
        LOG.info("userUpdatedEvent is here with name: "+userUpdatedEvent.getName());
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
