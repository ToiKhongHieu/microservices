package thi.nguyen.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteCommand {
    @TargetAggregateIdentifier
    private String employeeId;

    public DeleteCommand(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
