package daos;

import java.util.List;

import models.Employee;

public interface EmployeeDao {
	public List<Employee> getAll();
	public Employee getById(int id);
	public int addEmployee(Employee e);
}
