package thi.nguyen.employeeservice.command.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class EmployeeDeletedEvent {

    @TargetAggregateIdentifier
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
