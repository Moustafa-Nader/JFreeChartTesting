package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;
import org.jfree.data.xy.MatrixSeriesCollection;
import org.junit.Before;
import org.junit.Test;import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.Times;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestComparableObjectSeriesIntegration {

	ComparableObjectSeries Series = null;
	ComparableObjectSeries TestSeries = null;
	SeriesChangeListener listener1 = null;
	SeriesChangeListener listener2 = null;
	
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
		listener1 = spy(new MatrixSeriesCollection());
		 listener2 = spy(new MatrixSeriesCollection());
		Series.addChangeListener(listener1);
		Series.addChangeListener(listener2);
	}

	@Test
	public void Testadd2paramIntegration() {
		
		Series.add(1, 2);
		verify(listener1,times(1)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,times(1)).seriesChanged((SeriesChangeEvent)any());
	}
	@Test
	public void Testadd3paramIntegration() {
	
		Series.add(1, 2,true);
		verify(listener1,times(1)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,times(1)).seriesChanged((SeriesChangeEvent)any());
	}
	@Test
	public void TestaddItemIntegration()
	{
		ComparableObjectItem item = new ComparableObjectItem(1, 2);
		Series.add(item, true);
		assertEquals(1, Series.data.size());
		assertEquals(item, Series.data.get(0));
	}
	@Test 
	   public  void TestAddItemSeriesChangedIntegration() {
		ComparableObjectItem item = new ComparableObjectItem(1, 2);
	
		Series.add(item, true);
		verify(listener1,times(1)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,times(1)).seriesChanged((SeriesChangeEvent)any());
	
	}
	@Test
	public void TestclearIntegration()
	{
		ComparableObjectItem item1 = new ComparableObjectItem(1, 2);
		ComparableObjectItem item2 = new ComparableObjectItem(2, 2);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.clear();
		verify(listener1,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
	
	}
	@Test 
	public void TestdeleteIntegration() {
		ComparableObjectItem item1 = new ComparableObjectItem(1, 2);
		ComparableObjectItem item2 = new ComparableObjectItem(2, 2);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.delete(0, 0);;
		verify(listener1,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
	
	}
	@Test
	public void TestRemoveComparableIntegration() {
		ComparableObjectItem item1 = new ComparableObjectItem(1, 2);
		ComparableObjectItem item2 = new ComparableObjectItem(2, 2);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.remove((Comparable)1);
		verify(listener1,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
	}
	@Test 
	public void TestremoveIndexIntegration() {
		ComparableObjectItem item1 = new ComparableObjectItem(1, 2);
		ComparableObjectItem item2 = new ComparableObjectItem(2, 2);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.remove(1);
		verify(listener1,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
	}
	@Test
	public void TestUpdateByIndexIntegration() {
		ComparableObjectItem item1 = new ComparableObjectItem(1, 2);
		ComparableObjectItem item2 = new ComparableObjectItem(2, 2);
		Series.add(item1, true);
		Series.add(item2, true);
		Series.update(1, 5);;
		verify(listener1,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
        verify(listener2,atLeast(3)).seriesChanged((SeriesChangeEvent)any());
	}
}
