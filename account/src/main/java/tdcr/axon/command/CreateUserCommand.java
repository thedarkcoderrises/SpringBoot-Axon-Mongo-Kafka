package tdcr.axon.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class CreateUserCommand {

    @TargetAggregateIdentifier
    String userId;

    public CreateUserCommand(String userId) {
        this.userId = userId;
    }

    public CreateUserCommand() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
