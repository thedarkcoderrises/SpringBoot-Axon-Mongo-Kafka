package tdcr.axon.command.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tdcr.axon.command.UpdateUserCommand;
import tdcr.axon.command.domain.User;
import tdcr.axon.command.event.UserUpdatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Component
public class UpdateUserCommandHandler {

    @Autowired
    EventSourcingRepository<User> repository;

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand){
        Aggregate<User> a =repository.load(updateUserCommand.getUserId());
        apply(new UserUpdatedEvent(updateUserCommand.getUserId(),updateUserCommand.getName()));
        System.out.println("UpdateUserCommand..");
    }
}
