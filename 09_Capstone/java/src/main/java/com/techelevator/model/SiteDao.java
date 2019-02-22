package com.techelevator.model;

import java.util.List;

public interface SiteDao {

	public List<Site> isReservationAvailable(String campground, String arrivalDate, String departureDate);
	
}
