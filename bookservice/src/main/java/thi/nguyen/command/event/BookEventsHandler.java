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
        Books books = new Books();
        BeanUtils.copyProperties(event,books);
        bookRepository.save(books);
    }
}
