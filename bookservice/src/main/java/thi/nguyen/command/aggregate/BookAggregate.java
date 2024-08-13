package thi.nguyen.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import thi.nguyen.command.command.CreateBookCommand;
import thi.nguyen.command.command.DeleteBookCommand;
import thi.nguyen.command.command.UpdateBookCommand;
import thi.nguyen.command.event.BookCreatedEvent;
import thi.nguyen.command.event.BookUdpateEvent;
import thi.nguyen.command.event.DeleteBookEvent;
import thi.nguyen.commonservice.command.UpdateStatusBookCommand;
import thi.nguyen.commonservice.event.UpdateStatusBookEvent;

@Aggregate
public class BookAggregate {

    @AggregateIdentifier
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    public BookAggregate() {
    }

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand){
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(createBookCommand,bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent event){
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.isReady = event.getReady();
        this.name = event.getName();
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand){
        BookUdpateEvent bookUdpateEvent = new BookUdpateEvent();
        BeanUtils.copyProperties(updateBookCommand,bookUdpateEvent);
        AggregateLifecycle.apply(bookUdpateEvent);
    }

    @EventSourcingHandler
    public void on(BookUdpateEvent event){
        this.bookId = event.getBookId();
        this.author = event.getAuthor();
        this.isReady = event.getReady();
        this.name = event.getName();
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand){
        DeleteBookEvent deleteBookEvent = new DeleteBookEvent();
        BeanUtils.copyProperties(deleteBookCommand,deleteBookEvent);
        AggregateLifecycle.apply(deleteBookEvent);
    }

    @EventSourcingHandler
    public void on(DeleteBookEvent event){
        this.bookId = event.getBookId();
    }

    @CommandHandler
    public void handle(UpdateStatusBookCommand command){
        UpdateStatusBookEvent event = new UpdateStatusBookEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UpdateStatusBookEvent event){
        this.bookId = event.getBookId();
        this.isReady = event.getReady();
    }
}
