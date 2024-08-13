package thi.nguyen.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thi.nguyen.command.data.BookRepository;
import thi.nguyen.command.data.Books;

@Component
public class BookEventsHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event){
        Books book = new Books();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUdpateEvent event){
        Books book = bookRepository.getById(event.getBookId());
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setReady(event.getReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(DeleteBookEvent event){
        bookRepository.deleteById(event.getBookId());
    }
}
