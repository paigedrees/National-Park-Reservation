package com.techelevator.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcReservationDao implements ReservationDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	//Triyng to get Total Cost from daily fees.
	//String sqlGetDailyFee = "SELECT daily_fee FROM campground WHERE campground_id = ?";
	//SqlRowSet feeForCost = jdbcTemplate.queryForRowSet(sqlGetDailyFee, campground);
	//int fee = Integer.parseInt(feeForCost);
	//BigDecimal cost = BigDecimal.valueOf(feeForCost * (diffDays);
	
	
//	public List<Site> generateAvailableSites(String campground, String arrivalDate, String departureDate) {
//		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//		String sqlGetSites = "SELECT site_id FROM site WHERE campground_id = ? ";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetSites, campground);
//
//		LocalDate requestedStart = LocalDate.parse(arrivalDate);
//		LocalDate requestedEnd = LocalDate.parse(departureDate);
//		LocalDate existingStart;
//		LocalDate existingEnd;
//		
//		List<Site> resultsList = new ArrayList<>();
//			
//		while (result.next()) {
//			Site newSite = new Site();
//			newSite.setSiteId(result.getInt("site_id"));
//			newSite.setSiteNumber(result.getInt("site_number"));
//			
//			resultsList.add(newSite);
//		}
//		for (Site s : resultsList) {
//			String sqlgetToDate = "SELECT to_date FROM reservation WHERE site_id = ?";
//			SqlRowSet toDateResult = jdbcTemplate.queryForRowSet()
//		}
//		
//			return resultsList;
//	}
	
	
	

}
