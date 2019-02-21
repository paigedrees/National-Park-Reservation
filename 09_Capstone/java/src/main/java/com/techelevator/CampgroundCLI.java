package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.CampgroundDao;
import com.techelevator.model.JdbcCampgroundDao;
import com.techelevator.model.JdbcParkDao;
import com.techelevator.model.JdbcReservationDao;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;
import com.techelevator.model.ReservationDao;
import com.techelevator.model.SiteDao;
import com.techelevator.view.Menu;

public class CampgroundCLI {
	
	private Menu menu;
	private ParkDao parkDao;
	private CampgroundDao campgroundDao;
	private SiteDao siteDao;
	private ReservationDao reservationDao;
	
	private static final String ACADIA = "Acadia";
	private static final String ARCHES = "Arches";
	private static final String CUYAHOGA = "Cuyahoga Valley National Park";
	private static final String QUIT = "Quit";
	private static final String[] MAIN_MENU_CHOICES = {ACADIA, ARCHES, CUYAHOGA, QUIT };
	
	private static final String VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String RESERVATION = "Search for Reservation";
	private static final String RETURN = "Return to Previous Screen";
	private static final String[] PARK_OPTIONS = {VIEW_CAMPGROUNDS, RESERVATION, RETURN};
	

	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		menu = new Menu(System.in, System.out);
		parkDao = new JdbcParkDao(datasource);
		//campgroundDao = new JdbcCampgroundDao(datasource);
		//reservationDao = new JdbcReservationDao(datasource);
	}
	 
	public void run() {
		boolean readyToExit = false;
		while (readyToExit == false) {
			System.out.println("Select a Park for Further Details:");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_CHOICES);
			
			if (choice.equals(ACADIA)) {
				parkDao.getParkInfoById(1);
			} else if (choice.equals(ARCHES)) {
				parkDao.getParkInfoById(2);
			} else if (choice.equals(CUYAHOGA)) {
				parkDao.getParkInfoById(3);
			} else if (choice.equals(QUIT)) {
				System.out.println("Thank you for using the National Park Campsite Reservation System!");
				readyToExit = true;
			}
			if (readyToExit == false) {
				displayParkOptionsMenu();
			} 
		}
	}
	
	public void displayParkOptionsMenu() {
		boolean returnToPreviousScreen = false;
		while (returnToPreviousScreen == false) {
			System.out.println("Select a Command: ");
			String choice = (String)menu.getChoiceFromOptions(PARK_OPTIONS);
			
			if (choice.equals(VIEW_CAMPGROUNDS)) {
				//display list of campgrounds
			} else if (choice.equals(RESERVATION)) {
				//reservation method
			} else if (choice.equals(RETURN)) {
				returnToPreviousScreen = true;
			}
		}
	}
		
	/*private void listAllParks() {
		List<Park> allParks = parkDao.getAllParks();
		listParks(allParks);
	}
	
	private void listParks(List<Park> parks) {
		System.out.println();
		if (parks.size() > 0) {
			for (Park park : parks) {
				System.out.println(park.getId(), park.getParkName());
			}
		} else {
			System.out.println("\nNo results.");
		}
	} */
	
	
}

