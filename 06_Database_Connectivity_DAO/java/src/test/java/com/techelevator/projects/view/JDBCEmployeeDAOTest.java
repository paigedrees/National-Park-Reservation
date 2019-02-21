package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;

public class JDBCEmployeeDAOTest {


	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO dao;
	
	
	

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup( ) {
		dao = new JDBCEmployeeDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_all_employees_test() {

		List<Employee> results = dao.getAllEmployees();

		
		assertNotNull(results);
		
	}
	
	@Test
	public void search_employees_by_name_test() {
		Employee fakeEmployee = getEmployee((long) 1, (long) 2, "Test", "name", LocalDate.now(), 'F', LocalDate.now());
		dao.createEmployee(fakeEmployee);
		List<Employee> names = dao.searchEmployeesByName("Test", "name");
		assertNotNull(names);
		assertEquals(1, names.size());
		Employee fakeEmployee2 = names.get(0);
		assertEmployeesAreEqual(fakeEmployee, fakeEmployee2);
		
	}
	
	@Test
	public void change_employee_department_test() {
		dao.changeEmployeeDepartment((long) 2, (long) 2);
		assertEquals(dao.getEmployeesByDepartmentId(2).size(), 4);
	}
	
	private void assertEmployeesAreEqual(Employee expected, Employee actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
	}
	
	
	
	private Employee getEmployee(Long id, Long departmentId, String firstName, String lastName, LocalDate birthDay, char gender, LocalDate hireDate) {
		Employee theEmployee = new Employee();
		theEmployee.setId(id);
		theEmployee.setDepartmentId(departmentId);
		theEmployee.setFirstName(firstName);
		theEmployee.setLastName(lastName);
		theEmployee.setBirthDay(birthDay);
		theEmployee.setGender(gender);
		theEmployee.setHireDate(hireDate);
		return theEmployee;
	}
	

	

}
