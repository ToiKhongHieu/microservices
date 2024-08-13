package thi.nguyen.borrowingservice.command.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteBorrowingCommand {
    @TargetAggregateIdentifier
    private String id;

    public DeleteBorrowingCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
