package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.ComparableObjectItem;


import org.junit.Test;

public class TestComparableObjectItem {

	@Test
	public void TestgetComparable() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(5, 2);
		assertEquals(5,comparableObjectItem.getComparable());
	}
	
	@Test 
	public void TestgetObject() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(5, 2);
		assertEquals(2, comparableObjectItem.getObject());
	}
	
	@Test
	public void TestNullgetObject() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(5, null);
		assertNull(comparableObjectItem.getObject());
	}

	@Test
	public void TestsetObject() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(5,null);
		comparableObjectItem.setObject(2);
		assertEquals(2, comparableObjectItem.getObject());
	}
	
	@Test
	public void TestNullsetObejct() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(5, 2);
		comparableObjectItem.setObject(null);
		assertNull(comparableObjectItem.getObject());
	}
	
	@Test
	public void TestGreaterThancompareTo() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		assertTrue(comparableObjectItem.compareTo(2)>0);
	}
	
	@Test
	public void TestLessThancompareTo() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(2, 3);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(3, 2);
		assertTrue(comparableObjectItem.compareTo(comparableObjectItem2)<0);
	}
	
	@Test
	public void TestEqualscompareTo() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 3);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(3, 3);
		assertTrue(comparableObjectItem.compareTo(comparableObjectItem2)==0);
	}
	
	@Test
	public void Testclone() throws CloneNotSupportedException {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		assertTrue(comparableObjectItem.clone().equals(comparableObjectItem));
	}
	
	@Test
	public void TestNullequals() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		assertFalse(comparableObjectItem.equals(null));
	}
	
	@Test
	public void TestNotEqualequals() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(2, 3);
		assertFalse(comparableObjectItem.equals(comparableObjectItem2));
	}
	
	@Test
	public void TestEqualequals() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(3, 2);
		assertTrue(comparableObjectItem.equals(comparableObjectItem2));
	}
	
	@Test
	public void TestEqualhashCode() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(3, 2);
		assertEquals(comparableObjectItem.hashCode(), comparableObjectItem2.hashCode());
	}
	
	@Test
	public void TestNotEqualhashCode() {
		ComparableObjectItem comparableObjectItem = new ComparableObjectItem(3, 2);
		ComparableObjectItem comparableObjectItem2 = new ComparableObjectItem(2, 3);
		assertNotEquals(comparableObjectItem.hashCode(), comparableObjectItem2.hashCode());
	}
	
	
}
