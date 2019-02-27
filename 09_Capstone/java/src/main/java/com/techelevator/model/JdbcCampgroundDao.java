package com.techelevator.model;

import java.time.LocalDate;
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
	
	public Campground getCampgroundInfoById(int id) {
		Campground theCampground = null;
		String sqlGetCampgroundInfo = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundInfo, id);
		if(results.next()) {
			theCampground = mapRowToCampground(results);
		}
		
		return theCampground;
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground theCampground;
		theCampground = new Campground();
		theCampground.setCampgroundId(results.getInt("campground_id"));
		theCampground.setCampgroundName(results.getString("name"));
		theCampground.setOpenMonth(results.getString("open_from_mm"));
		theCampground.setCloseMonth(results.getString("open_to_mm"));	
		theCampground.setDailyFee(results.getBigDecimal("daily_fee"));
		return theCampground;
	}
	

}
