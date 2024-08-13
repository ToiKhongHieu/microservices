package thi.nguyen.borrowingservice.command.api.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import thi.nguyen.borrowingservice.command.api.command.CreateBorrowingCommand;
import thi.nguyen.borrowingservice.command.api.command.DeleteBorrowingCommand;
import thi.nguyen.borrowingservice.command.api.events.BorrowingCreateEvent;
import thi.nguyen.borrowingservice.command.api.events.BorrowingDeleteEvent;

import java.util.Date;

@Aggregate
public class BorrowingAggregate {
    @AggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingdate;
    private Date returnDate;

    private String message;
    public BorrowingAggregate(){};

    @CommandHandler
    public BorrowingAggregate(CreateBorrowingCommand command){
        BorrowingCreateEvent event = new BorrowingCreateEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BorrowingCreateEvent event){
        this.bookId = event.getBookId();
        this.borrowingdate = event.getBorrowingDate();
        this.employeeId = event.getEmployeeId();;
        this.id = event.getId();
    }

    @CommandHandler
    public void handle(DeleteBorrowingCommand command){
        BorrowingDeleteEvent event = new BorrowingDeleteEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BorrowingDeleteEvent event){
        this.id = event.getId();
    }
}
