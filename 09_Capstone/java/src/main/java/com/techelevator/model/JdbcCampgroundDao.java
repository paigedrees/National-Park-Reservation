package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcCampgroundDao implements CampgroundDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcCampgroundDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campground> getCampgroundsForPark(Park park){
		String sqlGetCampgrounds = "SELECT name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetCampgrounds, park.getId());
		
		List<Campground> resultList = new ArrayList<Campground>();
		
		while(result.next()) {
			Campground newCampground = new Campground();
			newCampground.setCampgroundName(result.getString("name"));
			newCampground.setOpenMonth(result.getString("open_from_mm"));
			newCampground.setCloseMonth(result.getString("open_to_mm"));
			newCampground.setDailyFee(result.getBigDecimal("daily_fee"));
			resultList.add(newCampground);
			
			
		}
		return resultList;
	}
	
	/*@Override
	public List<String> getCampgroundList(Park park){
		List<Campground> allCampgrounds = getCampgroundsForPark(park);
		
		List<String> results = new ArrayList<String>();
		
		for (Campground c : allCampgrounds) {
			Park 
		} 
	} */

}
