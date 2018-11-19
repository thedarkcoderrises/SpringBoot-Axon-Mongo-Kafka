package tdcr.axon.command.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import tdcr.axon.command.CreateUserCommand;
import tdcr.axon.command.event.UserCreatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Aggregate
public class User {

    @AggregateIdentifier
    private String userId;


    @CommandHandler
    public User(CreateUserCommand createUserCommand) {
        this.userId= createUserCommand.getUserId();
        apply( new UserCreatedEvent(createUserCommand.getUserId()));
        System.out.println("CreateUserCommand..");
    }

    @EventSourcingHandler
    public void on (CreateUserCommand createUserCommand){
        this.userId = createUserCommand.getUserId();
        System.out.println("Event is here");
    }


}
