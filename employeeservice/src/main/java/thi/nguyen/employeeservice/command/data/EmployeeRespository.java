package thi.nguyen.employeeservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee, String> {
}
