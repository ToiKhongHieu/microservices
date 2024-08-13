package thi.nguyen.borrowingservice.command.api.events;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thi.nguyen.borrowingservice.command.api.data.Borrowing;
import thi.nguyen.borrowingservice.command.api.data.BorrowingRepository;

@Component
public class BorrowingEventsHandler {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowingCreateEvent event) {
        Borrowing model = new Borrowing();
        BeanUtils.copyProperties(event, model);
        borrowingRepository.save(model);
    }

    @EventHandler
    public void on(BorrowingDeleteEvent event) {
        if (borrowingRepository.findById(event.getId()).isPresent()) {
            borrowingRepository.deleteById(event.getId());
        }
    }

}
