package tdcr.axon.command.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOG = LoggerFactory.getLogger(UpdateUserCommand.class);

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand) {
        validateUserName(updateUserCommand);
        apply(new UserUpdatedEvent(updateUserCommand.getUserId(), updateUserCommand.getName()));
        LOG.info("UpdateUserCommand..");
    }

    private void validateUserName(UpdateUserCommand updateUserCommand) {
        User u = repository.load(updateUserCommand.getUserId()).getWrappedAggregate().getAggregateRoot();
        if (u.getName() != null) {
            throw new RuntimeException("invalid update");
        }
        LOG.info("Loading existing data..");
    }
}
