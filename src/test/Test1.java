package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.*;


class Test1 {

	@Test
	void testAutoPark() {
		AutoPark ap = new AutoPark(2.5 , 20);
		
		assertSame(20 , ap.getCapacity());
		assertSame(0 , ap.getCurrentVehicleSize());
	}
	@Test
	void testSearchVehicle() {
		//tests if a subscribed vehicle could be found
		AutoPark ap = new AutoPark(2.5 , 3); 
		Date begin = new Date(16,5,2019);
		Date end = new Date(28,5,2019);
		Subscription aSub = new Subscription(begin, end, "34BTC9999");
		ap.addVehicle(aSub.getVehicle());
		
		assertSame(aSub.getVehicle()   , ap.searchVehicle("34BTC9999"));
	}

	@Test
	void testIsParked() {
		//tests the finding of parked vehicles
		AutoPark ap = new AutoPark(2.5 , 3);
		ap.vehicleEnters("35KSK1234", new Time(12,50), false);
		
		assertTrue( ap.isParked("35KSK1234") );
	}

	@Test
	void testAddVehicle() {
		//tests if the same vehicle can be added two times
		AutoPark ap = new AutoPark(2.5 , 3); 
		Date begin = new Date(16,5,2019);
		Date end = new Date(17,5,2019);
		Subscription aSub = new Subscription(begin, end, "35AP4242");
		ap.addVehicle(aSub.getVehicle());
		
		assertFalse( ap.addVehicle(aSub.getVehicle()) );
		
	}
	
	@Test
	void testVehicleEnters() {
		//test if a new vehicle is added when parking capacity is full
		AutoPark ap = new AutoPark(2.5 , 2);  //capacity of auto park is 2 
		ap.vehicleEnters("35AA1234", new Time(12,50), false);
		ap.vehicleEnters("45BB1234", new Time(13,50), true);
		
		assertFalse( ap.vehicleEnters("55CC1234", new Time(14,50), false) );
	}

	@Test
	void testVehicleExits() {
		AutoPark ap = new AutoPark(2.5 , 2);
		ap.vehicleEnters("35AA1234", new Time(12,50), false);
		
		assertTrue( ap.vehicleExits("35AA1234", new Time(14,50)) );
		assertSame(0,ap.getCurrentVehicleSize()); //autopark is empty
		
	}



}
