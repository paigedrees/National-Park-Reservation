package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		
		ArrayList <Employee> employees = new ArrayList<>();
		String sqlFindEmployees = "SELECT * FROM employee";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindEmployees);
		while (results.next()) {
			Employee newEmployee = new Employee();
				newEmployee.setFirstName(results.getString("first_name"));
				newEmployee.setLastName(results.getString("last_name"));
				newEmployee.setId(results.getLong("employee_id"));
				newEmployee.setDepartmentId(results.getLong("department_id"));
				newEmployee.setBirthDay(results.getDate("birth_date").toLocalDate());
				newEmployee.setGender(results.getString("gender").charAt(0));
				newEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
				employees.add(newEmployee);
		}
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		ArrayList <Employee> employees = new ArrayList<>();
		String sqlFindEmployees = "SELECT first_name, last_name FROM employee WHERE first_name LIKE ? OR last_name LIKE ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindEmployees, "%" + firstNameSearch + "%", "%" + lastNameSearch + "%");
		while (result.next()) {
			Employee employee = mapRowToEmployee(result);
			employees.add(employee);
			employee.setId(result.getLong("employee_id"));
		}		
		return employees;
		
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		ArrayList <Employee> employees = new ArrayList<>();
		String sqlFindEmployee = "SELECT first_name, last_name, department_id " +
				   "FROM employee "+
				   "WHERE department_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindEmployee, id);
		while(results.next()) {
			Employee newEmployee = mapRowToEmployee(results);
			employees.add(newEmployee);
		}
		return employees;
	}
	
	public Employee mapRowToEmployee(SqlRowSet results) {
		Employee newEmployee = new Employee();
		newEmployee.setFirstName(results.getString("first_name"));
		newEmployee.setLastName(results.getString("last_name"));
		newEmployee.setId(results.getLong("employee_id"));
		newEmployee.setDepartmentId(results.getLong("department_id"));
		newEmployee.setBirthDay(results.getDate("birth_date").toLocalDate());
		newEmployee.setGender(results.getString("gender").charAt(0));
		newEmployee.setHireDate(results.getDate("hire_date").toLocalDate());
		return newEmployee;
	}
	
	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		ArrayList <Employee> employees = new ArrayList<>();
		String sqlEmployeeWithoutProject = "SELECT first_name, last_name " + 
											"FROM employee " + 
											"LEFT JOIN project_employee ON project_employee.employee_id = employee.employee_id " +
											"WHERE project_employee.employee_id IS NULL";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlEmployeeWithoutProject);
		while(results.next()) {
			Employee newEmployee = mapRowToEmployee(results);
			employees.add(newEmployee);
		}
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		ArrayList <Employee> employees = new ArrayList<>();
		String sqlEmployeeWithoutProject = "SELECT first_name, last_name " + 
											"FROM employee " + 
											"JOIN project_employee ON project_employee.employee_id = employee.employee_id " +
											"WHERE project_employee.employee_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlEmployeeWithoutProject, projectId);
		while(results.next()) {
			Employee newEmployee = mapRowToEmployee(results);
			employees.add(newEmployee);
		}
		
		return employees;
	}

	@Override
	public void changeEmployeeDepartment(Long departmentId, Long employeeId) {
		String sqlChangeDepartment = "UPDATE employee SET department_id = ? WHERE employee_id = ?";
		jdbcTemplate.update(sqlChangeDepartment, departmentId, employeeId);
	}

	public Employee createEmployee(Employee newEmployee) {
		String sqlCreateEmployee = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
		newEmployee.setId(getNextEmployeeId());
		jdbcTemplate.update(sqlCreateEmployee, newEmployee.getId(), newEmployee.getDepartmentId(), newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getBirthDay(),
				newEmployee.getGender(), newEmployee.getHireDate());
			return newEmployee;
	}
	
	public long getNextEmployeeId() {
		SqlRowSet nextId = jdbcTemplate.queryForRowSet("SELECT nextval('seq_employee_id')");
		if (nextId.next()) {
			return nextId.getLong(1);
		}
		else {
			throw new RuntimeException ("Something went wrong while getting an id!");
		}
	}
	
}
