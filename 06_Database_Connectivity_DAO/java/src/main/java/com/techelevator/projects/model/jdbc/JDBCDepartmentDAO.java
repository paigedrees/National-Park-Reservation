package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		ArrayList <Department> departments = new ArrayList<>();
		String sqlFindDepartment = "SELECT * FROM departments";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindDepartment);
		while (results.next()) {
			Department currentDept = new Department();
				currentDept.setName(results.getString("name"));
				departments.add(currentDept);
		}
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		ArrayList <Department> departments = new ArrayList<>();
		String sqlFindDepartment = "SELECT name FROM department WHERE name = %?%";
		jdbcTemplate.update(sqlFindDepartment, nameSearch);
			return departments;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		String sqlFindDepartment = "UPDATE department SET name WHERE name = ?";
		jdbcTemplate.update(sqlFindDepartment, updatedDepartment);
		
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sqlCreateDepartment = "INSERT INTO department (department_id, name) VALUES (?, ?)";
		newDepartment.setId(getNextDepartmentId());
		jdbcTemplate.update(sqlCreateDepartment, newDepartment.getId(),
												 newDepartment.getName());
			return newDepartment;
	}
	
	private long getNextDepartmentId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_department_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department newDept = new Department();
		String sqlFindDepartment = "SELECT name FROM department WHERE id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindDepartment, id);
		newDept.setId(result.getLong("id"));
		newDept.setName(result.getString("name"));
		
		return newDept;
	}

}
