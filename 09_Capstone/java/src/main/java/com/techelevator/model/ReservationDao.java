package com.techelevator.model;

import java.time.LocalDate;

public interface ReservationDao {
	
	public void createNewReservation(int siteId, String name, LocalDate requestedStart, LocalDate requestedEnd);
	
	public int returnReservationId(LocalDate requestedStart, LocalDate requestedEnd, int siteId);
	

	

}
