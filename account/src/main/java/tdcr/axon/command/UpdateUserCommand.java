package tdcr.axon.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class UpdateUserCommand {

    @TargetAggregateIdentifier
    String userId;
    String name;


    public UpdateUserCommand() {

    }

    public UpdateUserCommand(String userId, String name) {
        this.userId = userId;
        this.name = name;
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
