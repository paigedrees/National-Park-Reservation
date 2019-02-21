package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List <Park> getAllParks(){
		ArrayList<Park> parks = new ArrayList<Park>();
		String sqlGetAllParks = "SELECT park_id, name FROM park ORDER BY name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		
		while(results.next()) {
			Park park = new Park();
			park.setId(results.getInt("park_id"));
			park.setParkName(results.getString("name"));
			parks.add(park);
		}
		
		return parks;
	}
	
	@Override 
	public Park getParkInfoById(int id) {
		Park thePark = null;
		String sqlGetParkInfo = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkInfo, id);
		if(results.next()) {
			thePark = mapRowToPark(results);
		}
		
		String name = thePark.getParkName();
		String location = thePark.getParkLocation();
		LocalDate established = thePark.getEstablishDate();
		int area = thePark.getArea();
		int visitors = thePark.getVisitors();
		String description = thePark.getDescription();
		System.out.println(String.format("%-20s\n%-20s %-10s\n%-20s %-10s\n%-20s %-10s\n%-20s %-10s\n", name + " National Park", "Location: ", location, "Established: ", established, "Area: ", area, "Annual Visitors: ", visitors));
		System.out.println(description);
		return thePark;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park thePark;
		thePark = new Park();
		thePark.setId(results.getInt("park_id"));
		thePark.setParkName(results.getString("name"));
		thePark.setLocation(results.getString("location"));
		thePark.setEstablishDate(results.getDate("establish_date").toLocalDate());
		thePark.setArea(results.getInt("area"));
		thePark.setVisitors(results.getInt("visitors"));
		thePark.setDescription(results.getString("description"));
		return thePark;
	}
	
	

}
