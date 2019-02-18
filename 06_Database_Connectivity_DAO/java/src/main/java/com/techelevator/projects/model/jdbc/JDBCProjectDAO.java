package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		ArrayList<Project> projects = new ArrayList<>();
		String sqlOngoingProjects = "SELECT * " +
									"FROM project " +
									"WHERE from_date IS NOT NULL AND to_date >= now() AND from_date <= now()";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlOngoingProjects);
		while(results.next()) {
			Project newProjects = mapRowToProject(results);
			projects.add(newProjects);
		}
		
		return projects;
	}
	
	public Project mapRowToProject(SqlRowSet results) {
		Project newProject = new Project();
		newProject.setId(results.getLong("project_id"));
		newProject.setName(results.getString("name"));
		newProject.setStartDate(results.getDate("start_date").toLocalDate());
		newProject.setEndDate(results.getDate("end_date").toLocalDate());

		return newProject;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sqlRemoveFromProject = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
		jdbcTemplate.update(sqlRemoveFromProject, projectId, employeeId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sqlAddToProject = "INSERT INTO project_employee (project_id, employee_id) VALUES (?, ?)";
		jdbcTemplate.update(sqlAddToProject, projectId, employeeId);
	}

}
