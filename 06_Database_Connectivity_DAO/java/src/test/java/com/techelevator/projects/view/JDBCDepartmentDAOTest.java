package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;
public class JDBCDepartmentDAOTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCDepartmentDAO dao;
	
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
		dao = new JDBCDepartmentDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void create_department_test() {
		Department theDepartment = getDepartment("Test");
		dao.createDepartment(theDepartment);
		Department result = dao.getDepartmentById(theDepartment.getId());
		assertNotNull(result);
		assertDepartmentsAreEqual(theDepartment, result);
	}
	
	@Test
	public void search_dept_by_name_test() {
		String testName = "Test";
		Department testDept = getDepartment(testName);
		dao.createDepartment(testDept);
		List<Department> results = dao.searchDepartmentsByName(testName);
		assertNotNull(results);
		assertEquals(1,results.size());
		Department theDepartment = results.get(0);
		assertDepartmentsAreEqual(testDept, theDepartment);	
	}
	
	@Test
	public void save_department_test() {
		Department newDept = getDepartment("Test");
		dao.createDepartment(newDept);
		newDept.setName("New Name");
		dao.saveDepartment(newDept);
		Department updatedDept = dao.getDepartmentById(newDept.getId());
		assertNotNull(updatedDept);
		assertDepartmentsAreEqual(newDept, updatedDept);
	}
	
	@Test
	public void get_all_departments_test() {
		List<Department> results = dao.getAllDepartments();
		Department theDepartment = getDepartment("Test5");
		dao.createDepartment(theDepartment);
		Department theDepartment2 = getDepartment("Test6");
		dao.createDepartment(theDepartment2);
		
		assertNotNull(results);
		assertEquals(4, results.size());
	}
	
	
	private Department getDepartment(String name) {
		Department theDepartment = new Department();
		theDepartment.setName(name);
		return theDepartment;
	}
	
	private void assertDepartmentsAreEqual(Department expected, Department actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
	}
	
	
}
