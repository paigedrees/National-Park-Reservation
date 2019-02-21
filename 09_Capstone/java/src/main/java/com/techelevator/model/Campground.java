package com.techelevator.model;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;

public class Campground {
	
	private int campgroundId;
	private int parkId;
	private String campgroundName;
	private String openMonth;
	private String closeMonth;
	private BigDecimal dailyFee;
	
	public int getCampgroundId() {
		return campgroundId;
	}
	
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public int getParkId() {
		return parkId;
	}
	
	public String getCampgroundName() {
		return campgroundName;
	}
	
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	
	public String getOpenMonth() {
		return openMonth;
	}
	
	public void setOpenMonth(String openMonth) {
		this.openMonth = openMonth;
	}
	
	public String getCloseMonth() {
		return closeMonth;
	}
	
	public void setCloseMonth(String closeMonth) {
		this.closeMonth = closeMonth;
	}
	
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
	public void setDailyFee (BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	public String makeOpenMonthName(String openMonth) {
		return new DateFormatSymbols().getMonths()[Integer.parseInt(openMonth)-1];
	}
	
	public String makeCloseMonthName(String closeMonth) {
		return new DateFormatSymbols().getMonths()[Integer.parseInt(closeMonth)-1];
	}



}
