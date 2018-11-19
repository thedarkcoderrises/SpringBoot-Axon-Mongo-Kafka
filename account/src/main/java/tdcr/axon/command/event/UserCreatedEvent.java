package tdcr.axon.command.event;

public class UserCreatedEvent {

    private String userId;

    public UserCreatedEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
