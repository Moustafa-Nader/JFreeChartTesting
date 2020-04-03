package org.jfree.data;
import static org.junit.Assert.*;

import java.awt.List;
import java.util.Collections;

import org.jfree.data.ComparableObjectSeries;
import org.jfree.data.general.SeriesException;
import org.jfree.data.xy.MatrixSeries;
import org.jfree.util.ObjectUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

public class TestComparableObjectSeries {

	ComparableObjectSeries Series = null;
	ComparableObjectSeries TestSeries = null;
	
	
	@Before
	public void setUp() {
		Series = new ComparableObjectSeries("Series1");
		TestSeries = new ComparableObjectSeries("Series2");
		ComparableObjectItem itema = new ComparableObjectItem(0,0);
		ComparableObjectItem itemb = new ComparableObjectItem(1,1);
		ComparableObjectItem itemc = new ComparableObjectItem(2,2);
		TestSeries.add(itema, true);
		TestSeries.add(itemb, true);
		TestSeries.add(itemc, true);
	}
	
	@Test
	public void TestComparableObjectSeriesConstructorNull() {
		boolean changed = false;
		try {
		ComparableObjectSeries Series1 = new ComparableObjectSeries(null) ;
		
		}
		catch (IllegalArgumentException e) {
			// TODO: handle exception
			changed = true;
		}
		assertTrue(changed);
	}
	
	@Test 
	public void TestgetAutoSort() {
	assertEquals(true, Series.getAutoSort());	
	}
	
	@Test 
	public void TestgetAllowDuplicateXValues() {
	assertEquals(true,Series.getAllowDuplicateXValues());
	}
	
	@Test
	public void TestgetItemCount() {
		assertEquals(0, Series.getItemCount());
	}
	
	@Test
	public void TestgetMaximumItemCount() {
	int Max = Integer.MAX_VALUE;
	assertEquals(Max, Series.getMaximumItemCount());
	assertNotEquals(Max+1,Series.getMaximumItemCount());
	assertNotEquals(Max-1,Series.getMaximumItemCount());
	}

	@Test
	public void TestsetMaximumItemCount() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		Series.setMaximumItemCount(2);
		assertEquals(Series.data.size(), 2);
		assertEquals(Series.getMaximumItemCount(),2);
		assertEquals(Series.data.get(0),item2);
	}
	@Test
	public void TestsetMaximumItemCountBegin() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		Series.setMaximumItemCount(0);
		assertEquals(Series.getMaximumItemCount(), 0);
		assertEquals(Series.data.size(), 0);
		
	}
	@Test
	public void TestsetMaximumItemCountEnd() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		Series.setMaximumItemCount(Integer.MAX_VALUE);
		assertEquals(Series.getMaximumItemCount(), Integer.MAX_VALUE);
		
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestsetMaximumItemCountoutNegative() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		Series.setMaximumItemCount(-1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestsetMaximumItemCountoutPositive() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		Series.setMaximumItemCount(Integer.MAX_VALUE + 1);
	}
	@Test
	public void TestsetMaximumItemCountfireSeriesCalled()
	{
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectItem item2 = new ComparableObjectItem(3,4);
		ComparableObjectItem item3 = new ComparableObjectItem(5,6);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.add(item3, true);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.setMaximumItemCount(2);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
	}
	
	@Test
	public void Testadd2paramCall() {
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		mockedSeries.add(5,8);
		Mockito.verify(mockedSeries, Mockito.times(1)).add(5,8,true);
	}
	@Test
	public void Testadd2param() {
	ComparableObjectItem item = new ComparableObjectItem(1, 2);
	Series.add(1, 2);
	assertEquals(1, Series.data.size());
	assertEquals(item, Series.data.get(0));
	}
	@Test
	public void TestaddNullX2param() {
		boolean changed = false;	
		try {
			Series.add(null, 2);
			} catch (IllegalArgumentException e) {
				changed = true;
				// TODO: handle exception
			}
		assertTrue(changed);
	}
	@Test
	public void TestaddNullY2param() {
		boolean changed = false;
		
		try {
			Series.add(2, null);
			} catch (IllegalArgumentException e) {
				changed = true;
				// TODO: handle exception
			}
		assertFalse(changed);
	}
	@Test

	public void Testadd2paramitemfireSeriesCalled() {
		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.add(1,1);
		mockedSeries.add(2,2);
		mockedSeries.add(3,3);
		Mockito.verify(mockedSeries, Mockito.times(3)).fireSeriesChanged();
	}
	@Test(expected = SeriesException.class)
	public void Testadd2paramDuplicateX() {
		ComparableObjectSeries Seriesdup = new ComparableObjectSeries("Series",true,false);
		Seriesdup.add(1,2);
		Seriesdup.add(1,3);
		}
	
	@Test
	
	public void Testadd3param() {
	ComparableObjectItem item = new ComparableObjectItem(1, 2);
	Series.add(1, 2,true);
	assertEquals(1, Series.data.size());
	assertEquals(item, Series.data.get(0));
	}
	@Test
	public void TestaddNullX3param() {
		boolean changed = false;	
		try {
			Series.add(null, 2,true);
			} catch (IllegalArgumentException e) {
				changed = true;
				// TODO: handle exception
			}
		assertTrue(changed);
	}
	@Test
	public void TestaddNullY3param() {
		boolean changed = false;
		
		try {
			Series.add(2, null,true);
			} catch (IllegalArgumentException e) {
				changed = true;
				// TODO: handle exception
			}
		assertFalse(changed);
	}
	@Test

	public void Testadd3paramitemfireSeriesCalled() {

		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.add(1,1, true);
		mockedSeries.add(2,2, true);
		mockedSeries.add(3,3, true);
		Mockito.verify(mockedSeries, Mockito.times(3)).fireSeriesChanged();
	}
	@Test(expected = SeriesException.class)
	public void Testadd3paramDuplicateX() {
		ComparableObjectSeries Seriesdup = new ComparableObjectSeries("Series",true,false);
		Seriesdup.add(1,2, true);
		Seriesdup.add(1,3, true);
		}
		
	@Test

	public void TestadditemfireSeriesCalled() {
		ComparableObjectSeries Series1 = new ComparableObjectSeries("Series",false,false);
		ComparableObjectItem mockedItem = Mockito.mock(ComparableObjectItem.class);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series1);
		//Collections mockedCollections = Mockito.mock(Collections.class);
		Mockito.when(mockedItem.getComparable()).thenReturn(1);
		Mockito.when(mockedSeries.indexOf(1)).thenReturn(-1);
		mockedSeries.add(mockedItem, true);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
	}
	@Test(expected = SeriesException.class)
	public void TestaddItemDuplicateX() {
		ComparableObjectSeries Series1 = new ComparableObjectSeries("Series",false,false);
		ComparableObjectItem mockedItem1 = Mockito.mock(ComparableObjectItem.class);
		ComparableObjectItem mockedItem2 = Mockito.mock(ComparableObjectItem.class);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series1);
		//Collections mockedCollections = Mockito.mock(Collections.class);
		Mockito.when(mockedItem1.getComparable()).thenReturn(1);
		Mockito.when(mockedItem2.getComparable()).thenReturn(1);
		Mockito.when(mockedSeries.indexOf(1)).thenReturn(0);
		Series1.add(mockedItem1, true);
		Series1.add(mockedItem2, true);
		}

	@Test
	public void TestaddItem() {
		ComparableObjectSeries Series1 = new ComparableObjectSeries("Series",false,false);
		ComparableObjectItem mockedItem = Mockito.mock(ComparableObjectItem.class);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series1);
		//Collections mockedCollections = Mockito.mock(Collections.class);
		Mockito.when(mockedItem.getComparable()).thenReturn(1);
		Mockito.when(mockedSeries.indexOf(1)).thenReturn(0);
		Series1.add(mockedItem, 1);
		assertEquals(1, Series1.data.size());		
	//	ComparableObjectItem item = new ComparableObjectItem(1, 2);
		//Series.add(item, true);
		//assertEquals(1, Series.data.size());
		//assertEquals(item, Series.data.get(0));
	}
	@Test(expected = IllegalArgumentException.class)
	public void TestaddItemNull() {
		ComparableObjectItem item = null;
		Series.add(item, true);
	}
	
	@Test
	public void TestindexOfnotExist() {
		Series.add(2, 3);
		Series.add(5, 6);
		assertTrue(Series.indexOf(3)<0);
	
	}
	@Test
	public void TestindexOfExist() {
		Series.add(2, 3);
		Series.add(5, 6);
		assertEquals(0,Series.indexOf(2));
		
	}
	
	@Test(expected = SeriesException.class)
	public void TestupdateoutNegative() {
		int x = 5;
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		Mockito.when(mockedSeries.indexOf(x)).thenReturn(-1);
		mockedSeries.update(x, 3);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestupdateoutPositive() {
		int x = 5;
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		Mockito.when(mockedSeries.indexOf(x)).thenReturn(6);
		mockedSeries.update(x, 3);
	}
	@Test
	public void TestupdateExist() {
		int x = 5;
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.add(item1, true);
		Mockito.when(mockedSeries.indexOf(0)).thenReturn(0);
		Mockito.when(mockedSeries.getDataItem(0)).thenReturn(item1);
		mockedSeries.update(2, 5);
		assertEquals(new ComparableObjectItem(2,5), mockedSeries.data.get(0));
	}
	@Test
	public void TestupdatefireSeriesCalled() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.add(item1, false);
		Mockito.when(mockedSeries.indexOf(0)).thenReturn(0);
		Mockito.when(mockedSeries.getDataItem(0)).thenReturn(item1);
		mockedSeries.update(2, 5);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
		
	}
	
	@Test
	public void TestupdateByIndexBegin() {
		TestSeries.updateByIndex(0, 3);
		assertEquals(new ComparableObjectItem(0,3), TestSeries.data.get(0));
		
	}
	@Test
	public void TestupdateByIndexEnd() {
		TestSeries.updateByIndex(2, 7);
		assertEquals(new ComparableObjectItem(2,7), TestSeries.data.get(2));
		
	
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestupdateByIndexoutNegative() {
		TestSeries.updateByIndex(-1, 7);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestupdateByIndexoutPositive() {
		TestSeries.updateByIndex(4, 7);
	}
	@Test
	public void TestupdateByIndexExist() {
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		Mockito.when(mockedSeries.getDataItem(0)).thenReturn(TestSeries.getDataItem(0));
		mockedSeries.updateByIndex(0, 5);
		assertEquals(new ComparableObjectItem(0,5), mockedSeries.data.get(0));
	}
	@Test
	public void TestupdateByIndexfireSeriesCalled() {
		ComparableObjectItem item1 = new ComparableObjectItem(2,3);
		ComparableObjectSeries mockedSeries = Mockito.spy(Series);
		mockedSeries.add(item1, false);
		Mockito.when(mockedSeries.getDataItem(0)).thenReturn(item1);
		mockedSeries.updateByIndex(0, 5);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
	}
	
	@Test
	public void TestgetDataItemBegin() {
		assertEquals(new ComparableObjectItem(0, 0), TestSeries.getDataItem(0));
	}
	@Test
	public void TestgetDataItemEnd() {
		assertEquals(new ComparableObjectItem(2, 2), TestSeries.getDataItem(2));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestgetDataItemOutNegative() {
		TestSeries.getDataItem(-1);
	}	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestgetDataItemOutPositive() {
		TestSeries.getDataItem(3);
	}
	
	@Test
	public void TestdeletefireSeriesCalled() {
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		mockedSeries.delete(0, 1);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
	}
	@Test
	public void TestdeleteBegin() {
		TestSeries.delete(0, 0);
		assertEquals(new ComparableObjectItem(1, 1), TestSeries.data.get(0));
		assertTrue(TestSeries.data.size() == 2);
	}
	@Test
	public void TestdeleteEnd() {
		TestSeries.delete(2, 2);
		assertEquals(new ComparableObjectItem(1, 1), TestSeries.data.get(1));
		assertTrue(TestSeries.data.size() == 2);
	}
	@Test
	public void TestdeleteMultipleElements() {
		TestSeries.delete(0, 1);
		assertEquals(new ComparableObjectItem(2, 2), TestSeries.data.get(0));
		assertTrue(TestSeries.data.size() == 1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	
	public void TestdeleteoutNegative() {
		TestSeries.delete(-1, -1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestdeleteoutPositive() {
		TestSeries.delete(3, 3);
	}
	
	@Test
	public void TestclearfireSeriesCalled() {
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		mockedSeries.clear();
		Mockito.verify(mockedSeries,Mockito.times(1)).fireSeriesChanged();
		
	}
	@Test
	public void Testclear() {
		TestSeries.clear();
		assertTrue(TestSeries.data.size() == 0);
	}
	
	@Test
	public void TestremovefireSeriesCalled(){
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		mockedSeries.remove(0);
		Mockito.verify(mockedSeries, Mockito.times(1)).fireSeriesChanged();
	}
	@Test
	public void TestremoveBegin() {
		assertEquals(new ComparableObjectItem(0, 0),TestSeries.remove(0));
		assertTrue(TestSeries.data.size() == 2);
	}
	@Test
	public void TestremoveEnd() {
		assertEquals(new ComparableObjectItem(2, 2),TestSeries.remove(2));
		assertTrue(TestSeries.data.size() == 2);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestremoveoutNegative()
	{
		TestSeries.remove(-1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestremoveoutPositive()
	{
		TestSeries.remove(3);
	}
	
	@Test
	
	public void TestremoveComparable() {
		ComparableObjectSeries mockedSeries = Mockito.spy(TestSeries);
		Mockito.when(mockedSeries.indexOf(0)).thenReturn(0);
		mockedSeries.remove((Comparable)0);
		Mockito.verify(mockedSeries, Mockito.times(1)).remove(0);
	}

	@Test
	public void TestequalsSameObject() {
		assertTrue(TestSeries.equals(TestSeries));
	}
	@Test
	public void TestequalsDiffType() {
		assertFalse(TestSeries.equals(new ComparableObjectItem(2, 3)));
	}
	@Test
	public void TestequalsSameData() {
		//ObjectUtilities mockedObjectUtilities = Mockito.mock(ObjectUtilities.class);
		ComparableObjectSeries mySeries = new ComparableObjectSeries("Series2");
		ComparableObjectItem item1 = new ComparableObjectItem(0,0);
		ComparableObjectItem item2 = new ComparableObjectItem(1,1);
		ComparableObjectItem item3 = new ComparableObjectItem(2,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);

		//Mockito.when(mockedObjectUtilities.equal(Mockito.isA(Object.class),Mockito.isA(Object.class))).thenReturn(true);
		assertTrue(mySeries.equals((ComparableObjectSeries)TestSeries));
	}
	@Test
	public void TestequalsDiffItems() {
		//ObjectUtilities mockedObjectUtilities = Mockito.mock(ObjectUtilities.class);
		ComparableObjectSeries mySeries = new ComparableObjectSeries("Series2");
		ComparableObjectItem item1 = new ComparableObjectItem(1,0);
		ComparableObjectItem item2 = new ComparableObjectItem(3,1);
		ComparableObjectItem item3 = new ComparableObjectItem(4,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);

		//Mockito.when(mockedObjectUtilities.equal(Mockito.isA(Object.class),Mockito.isA(Object.class))).thenReturn(true);
		assertFalse(mySeries.equals((ComparableObjectSeries)TestSeries));
	}
	@Test
	public void TestequalsDiffKeys() {
		//ObjectUtilities mockedObjectUtilities = Mockito.mock(ObjectUtilities.class);
		ComparableObjectSeries mySeries = new ComparableObjectSeries("mySeries");
		ComparableObjectItem item1 = new ComparableObjectItem(0,0);
		ComparableObjectItem item2 = new ComparableObjectItem(1,1);
		ComparableObjectItem item3 = new ComparableObjectItem(2,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);
		//Mockito.when(mockedObjectUtilities.equal(Mockito.isA(Object.class),Mockito.isA(Object.class))).thenReturn(true);
		assertFalse(mySeries.equals((ComparableObjectSeries)TestSeries));
	}
	
	@Test
	public void TesthashCodeSameData() {
		ComparableObjectSeries mySeries = new ComparableObjectSeries("Series2");
		ComparableObjectItem item1 = new ComparableObjectItem(0,0);
		ComparableObjectItem item2 = new ComparableObjectItem(1,1);
		ComparableObjectItem item3 = new ComparableObjectItem(2,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);
		assertEquals(mySeries.hashCode(),TestSeries.hashCode());
	}
	@Test
	public void TesthashCodeDiffItems() {
		//ObjectUtilities mockedObjectUtilities = Mockito.mock(ObjectUtilities.class);
		ComparableObjectSeries mySeries = new ComparableObjectSeries("Series2");
		ComparableObjectItem item1 = new ComparableObjectItem(1,0);
		ComparableObjectItem item2 = new ComparableObjectItem(3,1);
		ComparableObjectItem item3 = new ComparableObjectItem(4,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);

		//Mockito.when(mockedObjectUtilities.equal(Mockito.isA(Object.class),Mockito.isA(Object.class))).thenReturn(true);
		assertNotEquals(mySeries.hashCode(),TestSeries.hashCode());
	}
	@Test
	public void TesthashCodeDiffKeys() {
		//ObjectUtilities mockedObjectUtilities = Mockito.mock(ObjectUtilities.class);
		ComparableObjectSeries mySeries = new ComparableObjectSeries("mySeries");
		ComparableObjectItem item1 = new ComparableObjectItem(0,0);
		ComparableObjectItem item2 = new ComparableObjectItem(1,1);
		ComparableObjectItem item3 = new ComparableObjectItem(2,2);
		mySeries.add(item1, true);
		mySeries.add(item2, true);
		mySeries.add(item3, true);
		//Mockito.when(mockedObjectUtilities.equal(Mockito.isA(Object.class),Mockito.isA(Object.class))).thenReturn(true);
		assertNotEquals(mySeries.hashCode(),TestSeries.hashCode());
	}
	
}

