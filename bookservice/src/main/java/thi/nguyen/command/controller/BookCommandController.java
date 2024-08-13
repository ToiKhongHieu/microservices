package thi.nguyen.command.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thi.nguyen.command.command.CreateBookCommand;
import thi.nguyen.command.command.DeleteBookCommand;
import thi.nguyen.command.command.UpdateBookCommand;
import thi.nguyen.command.model.BookRequestModel;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

    @Autowired
     private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel model){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(),model.getName(),model.getAuthor(),"true");
        commandGateway.sendAndWait(command);
        return "added Book";
    }

    @PutMapping
    public String udpateBook(@RequestBody BookRequestModel model){
        UpdateBookCommand command = new UpdateBookCommand(model.getBookId(),model.getName(),model.getAuthor(),model.getIsReady());
        commandGateway.sendAndWait(command);
        return "update Book";
    }

    @DeleteMapping("")
    public String deleteBook(@RequestParam String bookId){
        commandGateway.sendAndWait(new DeleteBookCommand(bookId));
        return "deleted Book";
    }
}
