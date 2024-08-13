package thi.nguyen.employeeservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import thi.nguyen.employeeservice.command.command.CreateEmployeeCommand;
import thi.nguyen.employeeservice.command.command.DeleteCommand;
import thi.nguyen.employeeservice.command.command.UpdateEmployeeCommand;
import thi.nguyen.employeeservice.command.event.EmployeeCreatedEvent;
import thi.nguyen.employeeservice.command.event.EmployeeDeletedEvent;
import thi.nguyen.employeeservice.command.event.EmployeeUpdatedEvent;

@Aggregate
public class EmployeeAggregate {

    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDiscriplined;

    public EmployeeAggregate() {
    }

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        EmployeeCreatedEvent event = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand command) {
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }

    //    ---------------------------
    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDiscriplined = event.getDiscriplined();
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent event) {
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDiscriplined = event.getDiscriplined();
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        this.employeeId = event.getEmployeeId();
    }

}
