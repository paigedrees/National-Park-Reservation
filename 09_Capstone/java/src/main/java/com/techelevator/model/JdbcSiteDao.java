package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcSiteDao implements SiteDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcSiteDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Site> isReservationAvailable(String campground, String arrivalDate, String departureDate) {
		int campgroundId = Integer.valueOf(campground);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String [] parts = arrivalDate.split("/");
		String arrival = parts[2] + "-" + parts[0] + "-" + parts[1];
		String [] parts2 = departureDate.split("/");
		String departure = parts2[2] + "-" + parts2[0] + "-" + parts2[1];
		
		LocalDate requestedStart = LocalDate.parse(arrival, dateFormat);
		LocalDate requestedEnd = LocalDate.parse(departure, dateFormat); 
		
		String sqlGetReservationAvailability = "SELECT * FROM site WHERE site.campground_id = ? AND site.site_id NOT IN "+ 
												"(SELECT site.site_id FROM site JOIN reservation ON reservation.site_id = site.site_id WHERE reservation.from_date >= ? AND reservation.to_date <= ?) LIMIT 5";
		
		
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationAvailability, campgroundId, requestedStart, requestedEnd);
		
		List<Site> resultList = new ArrayList<>();
		
		while (results.next()) {
			Site newSite = new Site();
			newSite.setSiteId(results.getInt("site_id"));
			newSite.setCampgroundId(results.getInt("campground_id"));
			newSite.setSiteNumber(results.getInt("site_number"));
			newSite.setMaxOccupancy(results.getInt("max_occupancy"));
			newSite.setAccessibility(results.getBoolean("accessible"));
			newSite.setMaxRvLength(results.getInt("max_rv_length"));
			newSite.setUtilities(results.getBoolean("utilities"));
			resultList.add(newSite);
		
		}
		
		return resultList;
	}
	
	public int returnSiteId(int siteNo, int campgroundId) {
		Site site = null;
		String sqlReturnSiteId = "SELECT site_id FROM site WHERE site_number = ? AND campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlReturnSiteId, siteNo, campgroundId);
		if (result.next()) {
			site = mapRowToSite(result);
		}
		
		return site.getSiteId();
	}
	
	public Site mapRowToSite(SqlRowSet results) {
		Site newSite = new Site();
		newSite.setSiteId(results.getInt("site_id"));
		return newSite;
	}
	

}
