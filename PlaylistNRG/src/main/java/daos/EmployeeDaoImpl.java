package daos;

import java.util.List;

import org.hibernate.Session;

import models.Employee;

import com.revature.util.HibernateUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addEmployee(Employee e) {
		try (Session s = HibernateUtil.getSession()) {
			
		}
		return 0;
	}
	
}
