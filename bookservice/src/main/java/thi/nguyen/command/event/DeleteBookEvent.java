package thi.nguyen.command.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteBookEvent {
    @TargetAggregateIdentifier
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
