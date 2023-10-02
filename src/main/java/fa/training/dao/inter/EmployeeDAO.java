package fa.training.dao.inter;

import fa.training.entities.Employee;

public interface EmployeeDAO {
    public boolean addEmployee(Employee employee);

    public Employee getEmployeeByID(int id);
}
