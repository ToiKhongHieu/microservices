package thi.nguyen.borrowingservice.command.api.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.*;
import thi.nguyen.borrowingservice.command.api.command.CreateBorrowingCommand;
import thi.nguyen.borrowingservice.command.api.command.UdpateBookReturnCommand;
import thi.nguyen.borrowingservice.command.api.model.BorrowingRequestModel;

import javax.xml.transform.Source;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowing")
@EnableBinding(Source.class)
public class BorrowingCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBookBorrowing(@RequestBody BorrowingRequestModel model) {
        try {
            CreateBorrowingCommand command =
                    new CreateBorrowingCommand(UUID.randomUUID().toString(), model.getBookId(), model.getEmployeeId(), model.getBorrowingDate());
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Book borrowing added";
    }

    @PutMapping
    public String updateBookReturn(@RequestBody BorrowingRequestModel model){
        UdpateBookReturnCommand command = new UdpateBookReturnCommand(model.getId(),model.getBookId(),model.getEmployeeId(),new Date());
        commandGateway.sendAndWait(command);
        return "Book returned";
    }
}
