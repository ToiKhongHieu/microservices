package thi.nguyen.commonservice.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateStatusBookEvent {
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowId;

    public UpdateStatusBookEvent(String bookId, Boolean isReady, String employeeId, String borrowId) {
        this.bookId = bookId;
        this.isReady = isReady;
        this.employeeId = employeeId;
        this.borrowId = borrowId;
    }

    public UpdateStatusBookEvent() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
}
