package com.techelevator.model;

import java.util.List;

public interface CampgroundDao {

	List<Campground> getCampgroundsForPark(Park park);
}
