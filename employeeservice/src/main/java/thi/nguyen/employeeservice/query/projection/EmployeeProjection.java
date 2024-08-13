package thi.nguyen.employeeservice.query.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thi.nguyen.employeeservice.command.data.Employee;
import thi.nguyen.employeeservice.command.data.EmployeeRespository;
import thi.nguyen.employeeservice.query.model.EmployeeResponseModel;
import thi.nguyen.employeeservice.query.queries.GetAllEmployeeQuery;
import thi.nguyen.employeeservice.query.queries.GetEmployeeQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeProjection {

    @Autowired
    private EmployeeRespository respository;

    @QueryHandler
    public EmployeeResponseModel handle(GetEmployeeQuery getEmployeeQuery) {
        EmployeeResponseModel model = new EmployeeResponseModel();
        Employee employee = respository.findById(getEmployeeQuery.getEmployeeId()).get();
        BeanUtils.copyProperties(employee, model);
        return model;
    }

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery getAllEmployeeQuery) {
        List<EmployeeResponseModel> models = new ArrayList<>();
        List<Employee> employees = respository.findAll();
        employees.stream().forEach(s -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(s, model);
            models.add(model);
        });
        return models;
    }
}
