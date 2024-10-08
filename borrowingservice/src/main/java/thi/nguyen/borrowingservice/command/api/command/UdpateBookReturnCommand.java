package thi.nguyen.borrowingservice.command.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

public class UdpateBookReturnCommand {
    @TargetAggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date returnDate;

    public UdpateBookReturnCommand(String id, String bookId, String employeeId, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.employeeId = employeeId;
        this.returnDate = returnDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
