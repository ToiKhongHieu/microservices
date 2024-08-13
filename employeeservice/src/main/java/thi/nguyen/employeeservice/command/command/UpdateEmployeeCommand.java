package thi.nguyen.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateEmployeeCommand {
    @TargetAggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDiscriplined;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getKin() {
        return kin;
    }

    public void setKin(String kin) {
        this.kin = kin;
    }

    public Boolean getDiscriplined() {
        return isDiscriplined;
    }

    public void setDiscriplined(Boolean discriplined) {
        isDiscriplined = discriplined;
    }

    public UpdateEmployeeCommand(String employeeId, String firstName, String lastName, String kin, Boolean isDiscriplined) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.kin = kin;
        this.isDiscriplined = isDiscriplined;
    }
}
