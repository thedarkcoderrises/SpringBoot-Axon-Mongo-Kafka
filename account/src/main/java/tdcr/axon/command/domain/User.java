package tdcr.axon.command.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import tdcr.axon.command.CreateUserCommand;
import tdcr.axon.command.event.UserCreatedEvent;
import tdcr.axon.command.event.UserUpdatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Aggregate
public class User {

    @AggregateIdentifier
    private String userId;
    private String name;

    public User(){}

    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        this.userId= createUserCommand.getUserId();
        apply( new UserCreatedEvent(createUserCommand.getUserId()));
        System.out.println("CreateUserCommand..");
    }

    @EventSourcingHandler
    public void on (UserCreatedEvent userCreatedEvent){
        this.userId = userCreatedEvent.getUserId();
        System.out.println("userCreatedEvent is here");
    }

    @EventSourcingHandler
    public void on (UserUpdatedEvent userUpdatedEvent){
        this.userId = userUpdatedEvent.getUserId();
        this.name = userUpdatedEvent.getName();
        System.out.println("userUpdatedEvent is here with name: "+userUpdatedEvent.getName());
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
