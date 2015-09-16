package com.mhcs.logan;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for the Status class.
 * @author Logan Sales
 */
public class StatusTest {

	/**
	 * Test method for {@link com.mhcs.logan.Status#equalsName(String)}.
	 */
	@Test
	public void testEqualsName() {
		assertTrue(Status.UNDAMAGED.equalsName("undamaged"));
		assertFalse(Status.UNDAMAGED.equalsName("damaged"));
		assertFalse(Status.UNDAMAGED.equalsName("uncertain"));
		
		assertTrue(Status.DAMAGED.equalsName("damaged"));
		assertFalse(Status.DAMAGED.equalsName("undamaged"));
		assertFalse(Status.DAMAGED.equalsName("uncertain"));
		
		assertTrue(Status.UNCERTAIN.equalsName("uncertain"));
		assertFalse(Status.UNCERTAIN.equalsName("undamaged"));
		assertFalse(Status.UNCERTAIN.equalsName("damaged"));
	}

	/**
	 * Test method for {@link com.mhcs.logan.Status#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(Status.UNDAMAGED.toString().equals("undamaged"));
		assertTrue(Status.DAMAGED.toString().equals("damaged"));
		assertTrue(Status.UNCERTAIN.toString().equals("uncertain"));
	}

	/**
	 * Test method for {@link com.mhcs.logan.Status#getStatus(String)}
	 */
	@Test
	public void testGetStatus() {
		assertTrue(Status.UNDAMAGED == Status.getStatus("undamaged"));
		assertTrue(Status.DAMAGED == Status.getStatus("damaged"));
		assertTrue(Status.UNCERTAIN == Status.getStatus("uncertain"));
	}

}
