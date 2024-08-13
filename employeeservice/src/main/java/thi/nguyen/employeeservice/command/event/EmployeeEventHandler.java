package thi.nguyen.employeeservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thi.nguyen.employeeservice.command.data.Employee;
import thi.nguyen.employeeservice.command.data.EmployeeRespository;

@Component
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRespository respository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        respository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        Employee employee = respository.findById(event.getEmployeeId()).get();
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setDiscriplined(event.getDiscriplined());
        respository.save(employee);
    }

    @EventHandler
    public void on(EmployeeDeletedEvent event) {
        respository.deleteById(event.getEmployeeId());
    }

}
