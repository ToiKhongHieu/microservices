package thi.nguyen.employeeservice.query.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thi.nguyen.employeeservice.query.model.EmployeeResponseModel;
import thi.nguyen.employeeservice.query.queries.GetAllEmployeeQuery;
import thi.nguyen.employeeservice.query.queries.GetEmployeeQuery;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployeeDetail(@PathVariable String employeeId) {
        GetEmployeeQuery getEmployeeQuery = new GetEmployeeQuery();
        getEmployeeQuery.setEmployeeId(employeeId);

        EmployeeResponseModel employeeResponseModel = queryGateway.query(getEmployeeQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();

        return employeeResponseModel;
    }

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(){
        List<EmployeeResponseModel> list = queryGateway.query(new GetAllEmployeeQuery(), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
        return list;
    }
}
