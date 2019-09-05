package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Date;

class Test2 {

	@Test
	void testDate() {
		Date date = new Date(18,05,2020);
		assertSame(18, date.getDay());
	}

	@Test
	void testIsAfterThan() {
		//testing true case
		Date date1 = new Date(16,05,2019);
		Date date2 = new Date(17,05,2019);
		
		assertTrue(date2.isAfterThan(date1));
	}

	@Test
	void testIsBeforeThan() {
		//testing with same days
		Date date1 = new Date(17,05,2019);
		Date date2 = new Date(17,05,2019);
		
		assertFalse(date2.isAfterThan(date1));
	}

	@Test
	void testIsEqualsWith() {
		Date date1 = new Date(17,05,2019);
		Date date2 = new Date(17,05,2019);
		
		assertTrue(date2.isEqualsWith(date1));
	}

	@Test
	void testGetToday() {
		System.out.println( Date.getToday() );
	}

}
