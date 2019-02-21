package com.techelevator.model;

import java.time.LocalDate;

public class Park {
	
	private int id;
	private String parkName;
	private String parkLocation;
	private LocalDate establishDate;
	private int area;
	private int visitors;
	private String description;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getParkName() {
		return parkName;
	}
	
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	public String getParkLocation() {
		return parkLocation;
	}
	
	public void setLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	
	public LocalDate getEstablishDate() {
		return establishDate;
	}
	
	public void setEstablishDate(LocalDate date) {
		this.establishDate = date;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
	}
	
	public int getVisitors() {
		return visitors;
	}
	
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
