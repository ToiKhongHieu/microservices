package thi.nguyen.borrowingservice.command.api.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BorrowingDeleteEvent {
    private String id;

    public BorrowingDeleteEvent() {
    }

    public BorrowingDeleteEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
