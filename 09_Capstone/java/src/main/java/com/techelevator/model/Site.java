package com.techelevator.model;

public class Site {
	
	private int siteId;
	private int campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean accessibility;
	private int maxRvLength;
	private boolean utilities;
	

	public int getSiteId() {
		return siteId;
	}
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	public int getCampgroundId() {
		return campgroundId;
	}
	
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	
	public int getSiteNumber() {
		return siteNumber;
	}
	
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	
	public boolean getAccessibility() {
		return accessibility;
	}
	
	public void setAccessibility(boolean accessibility) {
		this.accessibility = accessibility;
	}
	
	public int getMaxRvLength() {
		return maxRvLength;
	}
	
	public void setMaxRvLength(int maxRvLength) {
		this.maxRvLength = maxRvLength;
	}
	
	public boolean getUtilities() {
		return utilities;
	}
	
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}

}
