package tdcr.axon.command.event;

public class UserUpdatedEvent {

    private String userId;
    private String name;

    public UserUpdatedEvent(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
