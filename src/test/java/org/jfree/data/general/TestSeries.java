package org.jfree.data.general;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import org.jfree.data.general.Series;
import org.jfree.data.xy.MatrixSeries;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import static org.mockito.Mockito.*;

public class TestSeries {
	
	private Series series;
	
	@Before
	public void setSeries() {
		series = new MatrixSeries("series", 2, 2);
	}
	
	
	//clone function tests
	@Test
	public void TestcloneValid() throws CloneNotSupportedException {
		assertEquals(series.clone(),new MatrixSeries("series", 2, 2));
	}
	
	//firePropertyChange function test
	
	@Test
	public void TestfirePropertyChange() {
		PropertyChangeListener mockedListener = Mockito.mock(PropertyChangeListener.class);
		series.addPropertyChangeListener(mockedListener);
		series.firePropertyChange("property","old","new");
		
		verify(mockedListener,times(1)).propertyChange((PropertyChangeEvent)any());
	}
	
	//fireSeriesChanged function test
	
	@Test
	public void TestfireSeriesChanged() {
		SeriesChangeListener mockedListener = mock(SeriesChangeListener.class);
		series.addChangeListener(mockedListener);
		series.fireSeriesChanged();
		
		verify(mockedListener,times(1)).seriesChanged((SeriesChangeEvent)any());
	}
	
	
	//fireVetoableChange function test
	
	@Test
	public void TestfireVetoableChange() throws PropertyVetoException {
		VetoableChangeListener mockedListener = mock(VetoableChangeListener.class);
		series.addVetoableChangeListener(mockedListener);
		series.fireVetoableChange("property","old","new");
		
		verify(mockedListener,times(1)).vetoableChange((PropertyChangeEvent) any());
	}
	
	//getDescription function test
	@Test
	public void TestgetDescription() {
		assertEquals(null, series.getDescription());
	}
	
	//getKey function test
	@Test
	public void TestgetKey() {
		assertEquals(series.getKey(),"series");
	}
	
	//notifyListeners function test
	@Test
	public void TestgetNotify() throws PropertyVetoException {
		SeriesChangeListener mockedListener = mock(SeriesChangeListener.class);
		series.addChangeListener(mockedListener);
		series.notifyListeners((SeriesChangeEvent)any());
		
		verify(mockedListener,times(1)).seriesChanged((SeriesChangeEvent)any());
	}
	
	//
	@Test
	public void TestisEmpty() {
		assertFalse(series.isEmpty());
	}
	
	//setDescription function test
	@Test
	public void TestsetDescription() {
		PropertyChangeListener mockedListener = Mockito.mock(PropertyChangeListener.class);
		series.addPropertyChangeListener(mockedListener);
		series.setDescription((String)any());
		
		verify(mockedListener,times(1)).propertyChange((PropertyChangeEvent)any());
	}
	
	//setKey function test
	public void TestsetKey() throws PropertyVetoException {
		VetoableChangeListener mockedListener = mock(VetoableChangeListener.class);
		series.addVetoableChangeListener(mockedListener);
		series.setKey((Integer)any());
		
		verify(mockedListener,times(1)).vetoableChange((PropertyChangeEvent) any());
	}
	
	//setNotify function test
	@Test
	public void TestsetNotifyFalse() {
		//PropertyChangeListener mockedListener1 = Mockito.mock(PropertyChangeListener.class);
		//VetoableChangeListener mockedListener2 = mock(VetoableChangeListener.class);
		SeriesChangeListener mockedListener3 = mock(SeriesChangeListener.class);
		//series.addPropertyChangeListener(mockedListener1);
		//series.addVetoableChangeListener(mockedListener2);
		series.addChangeListener(mockedListener3);
		series.setNotify(false);
		series.fireSeriesChanged();
		verify(mockedListener3,times(0)).seriesChanged((SeriesChangeEvent)any());
	}

}
