package com.mhcs.logan;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for the Orientation class.
 * @author Logan Sales
 */
public class OrientationTest {

	/**
	 * Test method for {@link com.mhcs.logan.Orientation#equalsName()}.
	 */
	@Test
	public void testEqualsName() {
		assertTrue(Orientation.UPRIGHT.equalsName("upright"));
		assertFalse(Orientation.UPRIGHT.equalsName("one turn"));
		assertFalse(Orientation.UPRIGHT.equalsName("two turns"));

		assertTrue(Orientation.ONETURN.equalsName("one turn"));
		assertFalse(Orientation.ONETURN.equalsName("upright"));
		assertFalse(Orientation.ONETURN.equalsName("two turns"));
		
		assertTrue(Orientation.TWOTURNS.equalsName("two turns"));
		assertFalse(Orientation.TWOTURNS.equalsName("upright"));
		assertFalse(Orientation.TWOTURNS.equalsName("one turn"));
	}

	/**
	 * Test method for {@link com.mhcs.logan.Orientation#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(Orientation.UPRIGHT.toString().equals("upright"));
		assertTrue(Orientation.ONETURN.toString().equals("one turn"));
		assertTrue(Orientation.TWOTURNS.toString().equals("two turns"));
	}

	/**
	 * Test method for {@link com.mhcs.logan.Orientation#getOrientation(String)}.
	 */
	@Test
	public void testGetOrientationString() {
		assertTrue(Orientation.getOrientation("upright") == Orientation.UPRIGHT);
		assertTrue(Orientation.getOrientation("one turn") == Orientation.ONETURN);
		assertTrue(Orientation.getOrientation("two turns") == Orientation.TWOTURNS);
	}

	/**
	 * Test method for {@link com.mhcs.logan.Orientation#getOrientation(int)}.
	 */
	@Test
	public void testGetOrientationInt() {
		assertTrue(Orientation.getOrientation(0) == Orientation.UPRIGHT);
		assertTrue(Orientation.getOrientation(1) == Orientation.ONETURN);
		assertTrue(Orientation.getOrientation(2) == Orientation.TWOTURNS);
		
		assertTrue(Orientation.getOrientation(-1) == null);
		assertTrue(Orientation.getOrientation(3) == null);
	}

	/**
	 * Test method for {@link com.mhcs.logan.Orientation#toStorageValue(Orientation)}.
	 */
	@Test
	public void testToStorageValue() {
		assertTrue(Orientation.toStorageValue(Orientation.UPRIGHT) == 0);
		assertTrue(Orientation.toStorageValue(Orientation.ONETURN) == 1);
		assertTrue(Orientation.toStorageValue(Orientation.TWOTURNS) == 2);
	}
	
}
