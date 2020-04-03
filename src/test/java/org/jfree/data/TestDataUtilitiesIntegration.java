package org.jfree.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;


import org.junit.Test;
import org.mockito.Mockito;

public class TestDataUtilitiesIntegration {

	@Test
	public void TestLowercalculateColumnTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0),0.00001);
	}
	
	@Test
	public void TestUppercalculateColumnTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(4.0, DataUtilities.calculateColumnTotal(arr, 1),0.00001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestMoreThanUppercalculateColumnTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(4.0, DataUtilities.calculateColumnTotal(arr, 2),0.00001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestLessThanLowercalculateColumnTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(4.0, DataUtilities.calculateColumnTotal(arr, -1),0.00001);
	}
	
	
	
	@Test(expected = NullPointerException.class)
	public void TestNullRowscalculateColumnTotal() {
		int[] rows = null;
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows));
	}
	
	@Test
	public void TestRowMorecalculateColumnTotal() {
		int[] rows = {2};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestFirstRowcalculateColumnTotal() {
		int[] rows = {0};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(1.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestLastRowcalculateColumnTotal() {
		int[] rows = {1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(2.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestRowLesscalculateColumnTotal() {
		int[] rows = {-1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestAllRowscalculateColumnTotal() {
		int[] rows = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestColLowercalculateColumnTotal() {
		int[] rows = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, -1, rows),0.000001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestColMorecalculateColumnTotal() {
		int[] rows = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(1.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 2, rows),0.000001);
	}
	
	@Test
	public void TestLowercalculateRowTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(3.0, DataUtilities.calculateRowTotal(arr, 0),0.00001);
	}
	
	@Test
	public void TestUppercalculateRowTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(4.0, DataUtilities.calculateRowTotal(arr, 1),0.00001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestMoreThanUppercalculateRowTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(4.0, DataUtilities.calculateRowTotal(arr, 2),0.00001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestLessThanLowercalculateRowTotal() {
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(2.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(2.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(4.0, DataUtilities.calculateRowTotal(arr, -1),0.00001);
	}
	
	@Test(expected = NullPointerException.class)
	public void TestNullColscalculateRowTotal() {
		int[] cols = null;
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols));
	}
	
	@Test
	public void TestColMorecalculateRowTotal() {
		int[] cols = {2};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestFirstColcalculateRowTotal() {
		int[] cols = {0};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(1.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestLastColcalculateRowTotal() {
		int[] col = {1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(2.0, DataUtilities.calculateRowTotal(arr, 0, col),0.000001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestColLesscalculateRowTotal() {
		int[] cols = {-1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestAllColscalculateRowTotal() {
		int[] cols = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestRowLowercalculateRowTotal() {
		int[] cols = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, -1, cols),0.000001);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestRowMorecalculateRowTotal() {
		int[] cols = {0,1};
		DefaultKeyedValues2D arr = new DefaultKeyedValues2D();
		arr.addValue(1.0, (Comparable)0, (Comparable)0);
		arr.addValue(2.0, (Comparable)0, (Comparable)1);
		arr.addValue(1.0, (Comparable)1, (Comparable)0);
		arr.addValue(2.0, (Comparable)1, (Comparable)1);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 2, cols),0.000001);
	}
	
	@Test
	public void TestOneValuegetCumulativePercentages() {
		KeyedValues keyedValues = Mockito.mock(KeyedValues.class);
		when(keyedValues.getItemCount()).thenReturn(4);
		when(keyedValues.getValue(0)).thenReturn(2);
		when(keyedValues.getValue(1)).thenReturn(2);
		when(keyedValues.getValue(2)).thenReturn(2);
		when(keyedValues.getValue(3)).thenReturn(2);
		when(keyedValues.getKey(0)).thenReturn(2);
		when(keyedValues.getKey(1)).thenReturn(3);
		when(keyedValues.getKey(2)).thenReturn(4);
		when(keyedValues.getKey(3)).thenReturn(5);
		
		KeyedValues keyedValues2 = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals(0.25, keyedValues2.getValue((Comparable)2));
	}
	
	@Test
	public void TestAllgetCumulativePercentages() {
		KeyedValues keyedValues = Mockito.mock(KeyedValues.class);
		when(keyedValues.getItemCount()).thenReturn(4);
		when(keyedValues.getValue(0)).thenReturn(2);
		when(keyedValues.getValue(1)).thenReturn(2);
		when(keyedValues.getValue(2)).thenReturn(2);
		when(keyedValues.getValue(3)).thenReturn(2);
		when(keyedValues.getKey(0)).thenReturn(2);
		when(keyedValues.getKey(1)).thenReturn(3);
		when(keyedValues.getKey(2)).thenReturn(4);
		when(keyedValues.getKey(3)).thenReturn(5);
		
		KeyedValues keyedValues2 = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals(1.00, keyedValues2.getValue((Comparable)5));
	}
	
	@Test
	public void TestNegativegetCumulativePercentages() {
		KeyedValues keyedValues = Mockito.mock(KeyedValues.class);
		when(keyedValues.getItemCount()).thenReturn(4);
		when(keyedValues.getValue(0)).thenReturn(-2);
		when(keyedValues.getValue(1)).thenReturn(-2);
		when(keyedValues.getValue(2)).thenReturn(-2);
		when(keyedValues.getValue(3)).thenReturn(-2);
		when(keyedValues.getKey(0)).thenReturn(2);
		when(keyedValues.getKey(1)).thenReturn(3);
		when(keyedValues.getKey(2)).thenReturn(4);
		when(keyedValues.getKey(3)).thenReturn(5);
		
		KeyedValues keyedValues2 = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals(0.25, keyedValues2.getValue((Comparable)2));
	}

}
