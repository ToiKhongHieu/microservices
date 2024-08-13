package thi.nguyen.employeeservice.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import thi.nguyen.employeeservice.command.command.CreateEmployeeCommand;
import thi.nguyen.employeeservice.command.command.DeleteCommand;
import thi.nguyen.employeeservice.command.command.UpdateEmployeeCommand;
import thi.nguyen.employeeservice.command.model.EmployeeRequestModel;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@EnableBinding(Source.class)
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private MessageChannel output;

    public EmployeeCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addEmployee(@RequestBody EmployeeRequestModel model) {
        CreateEmployeeCommand command =
                new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), false);
        commandGateway.sendAndWait(command);
        return "employee added";
    }

    @PutMapping
    public String updateEmployee(@RequestBody EmployeeRequestModel model) {
        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(model.getEmployeeId(), model.getFirstName(), model.getLastName(), model.getKin(), model.getDiscriplined());
        commandGateway.sendAndWait(command);
        return "employee updated";
    }

    @DeleteMapping()
    public String addEmployee(@RequestParam String employeeId) {
        DeleteCommand command = new DeleteCommand(employeeId);
        commandGateway.sendAndWait(command);
        return "employee deleted";
    }

    @PostMapping("/sendMessage")
    public void SendMessage(@RequestBody String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(message);
            output.send(MessageBuilder.withPayload(json).build());
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @PostMapping("/sendMessage2")
    public void SendMessage2(@RequestBody String message2) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(message2);
            output.send(MessageBuilder.withPayload(json).build());
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
