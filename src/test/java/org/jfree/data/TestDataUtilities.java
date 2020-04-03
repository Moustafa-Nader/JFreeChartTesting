package org.jfree.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;

import org.junit.Test;
import org.mockito.Mockito;

public class TestDataUtilities {

	@Test
	public void TestDiffColequal() {
		double[][] arr1 = {{1.0,2.0,3.0,4.0}};
		double[][] arr2 = {{1.0,2.0,3.0}};
		assertFalse(DataUtilities.equal(arr1,arr2));
	}
	
	@Test
	public void TestDiffRowequal() {
		double[][] arr1 = {{1.0,2.0}};
		double[][] arr2 = {{1.0,2.0},{1.0,2.0}};
		assertFalse(DataUtilities.equal(arr1, arr2));
	}
	
	@Test 
	public void TestNotEqualequal() {
		double[][] arr1 = {{1.0,2.0},{3.0,4.0}};
		double[][] arr2 = {{5.0,6.0},{7.0,8.0}};
		assertFalse(DataUtilities.equal(arr1, arr2));
	}
	
	@Test
	public void TestEqualequal() {
		double[][] arr1 = {{1.0,2.0},{3.0,4.0}};
		double[][] arr2 = {{1.0,2.0},{3.0,4.0}};
		assertTrue(DataUtilities.equal(arr1, arr2));
	}
	
	@Test
	public void TestINFequal() {
		double[][] arr1 = {{1.0,2.0},{Double.POSITIVE_INFINITY,4.0}};
		double[][] arr2 = {{1.0,2.0},{Double.POSITIVE_INFINITY,4.0}};
		assertTrue(DataUtilities.equal(arr1, arr2));
	}
	
	@Test
	public void TestNANequal() {
		double[][] arr1 = {{1.0,2.0},{Double.NaN,4.0}};
		double[][] arr2 = {{1.0,2.0},{Double.NaN,4.0}};
		assertTrue(DataUtilities.equal(arr1, arr2));
	}
	
	
	
	
	
	
	
	
	@Test
	public void Testclone() {
		double[][] arr1 = {{1.0,2.0},{3.0,4.0}};
		double[][] arr2 = DataUtilities.clone(arr1);
		assertTrue(Arrays.deepEquals(arr1, arr2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullclone() {
		double[][] arr1 = null;
		double[][] arr2 = DataUtilities.clone(arr1);
		assertTrue(Arrays.deepEquals(null, arr2));
	}
	

	@Test
	public void TestLowercalculateColumnTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0),0.00001);
	}
	
	@Test
	public void TestUppercalculateColumnTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertEquals(4.0, DataUtilities.calculateColumnTotal(arr, 1),0.00001);
	}
	
	@Test
	public void TestMoreThanUppercalculateColumnTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertNotEquals(4.0, DataUtilities.calculateColumnTotal(arr, 2),0.00001);
	}
	
	@Test
	public void TestLessThanLowercalculateColumnTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertNotEquals(4.0, DataUtilities.calculateColumnTotal(arr, -1),0.00001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullcalculateColumnTotal() {
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(null, 0),0.00001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void TestNullDatacalculateColumnTotal() {
		int[] rows = {1,2,3};
		Values2D values = null;
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(values, 0, rows));
	}
	
	@Test(expected = NullPointerException.class)
	public void TestNullRowscalculateColumnTotal() {
		int[] rows = null;
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows));
	}
	
	@Test
	public void TestRowMorecalculateColumnTotal() {
		int[] rows = {2};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestFirstRowcalculateColumnTotal() {
		int[] rows = {0};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertEquals(1.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestLastRowcalculateColumnTotal() {
		int[] rows = {1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertEquals(2.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestRowLesscalculateColumnTotal() {
		int[] rows = {-1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestAllRowscalculateColumnTotal() {
		int[] rows = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertEquals(3.0, DataUtilities.calculateColumnTotal(arr, 0, rows),0.000001);
	}
	
	@Test
	public void TestColLowercalculateColumnTotal() {
		int[] rows = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, -1, rows),0.000001);
	}
	
	@Test
	public void TestColMorecalculateColumnTotal() {
		int[] rows = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateColumnTotal(arr, 2, rows),0.000001);
	}
	

	
	
	
	@Test
	public void TestLowercalculateRowTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertEquals(3.0, DataUtilities.calculateRowTotal(arr, 0),0.00001);
	}
	
	@Test
	public void TestUppercalculateRowTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertEquals(4.0, DataUtilities.calculateRowTotal(arr, 1),0.00001);
	}
	
	@Test
	public void TestMoreThanUppercalculateRowTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertNotEquals(4.0, DataUtilities.calculateRowTotal(arr, 2),0.00001);
	}
	
	@Test
	public void TestLessThanLowercalculateRowTotal() {
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		when(arr.getValue(1, 1)).thenReturn(2.0);
		
		assertNotEquals(4.0, DataUtilities.calculateRowTotal(arr, -1),0.00001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullcalculateRowTotal() {
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(null, 0),0.00001);
	}
		
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullDatacalculateRowTotal() {
		int[] cols = {1,2,3};
		Values2D values = null;
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(values, 0, cols));
	}
	
	@Test(expected = NullPointerException.class)
	public void TestNullColscalculateRowTotal() {
		int[] cols = null;
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols));
	}
	
	@Test
	public void TestColMorecalculateRowTotal() {
		int[] cols = {2};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestFirstColcalculateRowTotal() {
		int[] cols = {0};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertEquals(1.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestLastColcalculateRowTotal() {
		int[] col = {1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertEquals(2.0, DataUtilities.calculateRowTotal(arr, 0, col),0.000001);
	}
	
	@Test
	public void TestColLesscalculateRowTotal() {
		int[] cols = {-1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(1, 0)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestAllColscalculateRowTotal() {
		int[] cols = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertEquals(3.0, DataUtilities.calculateRowTotal(arr, 0, cols),0.000001);
	}
	
	@Test
	public void TestRowLowercalculateRowTotal() {
		int[] cols = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, -1, cols),0.000001);
	}
	
	@Test
	public void TestRowMorecalculateRowTotal() {
		int[] cols = {0,1};
		Values2D arr = Mockito.mock(Values2D.class);
		when(arr.getColumnCount()).thenReturn(2);
		when(arr.getRowCount()).thenReturn(2);
		when(arr.getValue(0, 0)).thenReturn(1.0);
		when(arr.getValue(0, 1)).thenReturn(2.0);
		
		assertNotEquals(3.0, DataUtilities.calculateRowTotal(arr, 2, cols),0.000001);
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullcreateNumberArray() {
		Number[] arr = DataUtilities.createNumberArray(null);
		assertNull(arr);
	}
	
	@Test
	public void TestcreateNumberArray() {
		double[] d = {1.0,2.0,3.0};
		Number[] arr = DataUtilities.createNumberArray(d);
		Number[] ans = {1.0,2.0,3.0};
		assertTrue(Arrays.equals(arr,ans));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullcreateNumberArray2D() {
		Number[][] arr = DataUtilities.createNumberArray2D(null);
		assertNull(arr);
	}
	
	@Test
	public void TestcreateNumberArray2D() {
		double[][] d = {{1.0,2.0},{3.0}};
		Number[][] arr = DataUtilities.createNumberArray2D(d);
		Number[][] ans = {{1.0,2.0},{3.0}};
		assertTrue(Arrays.deepEquals(arr, ans));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void TestNullgetCumulativePercentages() {
		assertNull(DataUtilities.getCumulativePercentages(null));
	}
}
