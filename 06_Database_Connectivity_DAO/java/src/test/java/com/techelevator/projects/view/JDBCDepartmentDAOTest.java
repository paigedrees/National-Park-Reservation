package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
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
	
	@Test
	public void create_department_test() {
		Department theDepartment = getDepartment("Test");
		dao.createDepartment(theDepartment);
		Department result = dao.getDepartmentById(theDepartment.getId());
		assertNotEquals(null, theDepartment.getId());
		assertDepartmentsAreEqual(theDepartment, result);
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
