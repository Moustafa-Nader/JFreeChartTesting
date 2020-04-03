package org.jfree.data.general;

import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.general.SeriesChangeListener;
import org.jfree.data.xy.MatrixSeries;
import org.jfree.data.xy.MatrixSeriesCollection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeListenerProxy;
import java.beans.beancontext.BeanContextSupport;

import javax.swing.plaf.metal.MetalLabelUI;

public class TestMatrixSeriesIntegration {
		MatrixSeries testMatrixSeries;
		@Before
		public void setMatrixSeries() {
			testMatrixSeries = new MatrixSeries("series", 2, 2);
		}
		
		@Test
		public void TestAddingAndFiringSeriesChange() {
			SeriesChangeListener listener1 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener2 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener3 = spy(new MatrixSeriesCollection());
			testMatrixSeries.addChangeListener(listener1);
			testMatrixSeries.addChangeListener(listener2);
			testMatrixSeries.addChangeListener(listener3);
			testMatrixSeries.fireSeriesChanged();
			
			verify(listener1,times(1)).seriesChanged((SeriesChangeEvent)any());
			verify(listener2,times(1)).seriesChanged((SeriesChangeEvent)any());
			verify(listener3,times(1)).seriesChanged((SeriesChangeEvent)any());	
		}
		
		@Test
		public void TestAddingAndFiringSeriesChangeFalse() {
			SeriesChangeListener listener1 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener2 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener3 = spy(new MatrixSeriesCollection());
			testMatrixSeries.addChangeListener(listener1);
			testMatrixSeries.addChangeListener(listener2);
			testMatrixSeries.addChangeListener(listener3);
			testMatrixSeries.setNotify(false);
			testMatrixSeries.fireSeriesChanged();
			
			verify(listener1,times(0)).seriesChanged((SeriesChangeEvent)any());
			verify(listener2,times(0)).seriesChanged((SeriesChangeEvent)any());
			verify(listener3,times(0)).seriesChanged((SeriesChangeEvent)any());	
		}
		
		@Test
		public void TestAddingAndNotifyingListeners() {
			SeriesChangeListener listener1 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener2 = spy(new MatrixSeriesCollection());
			SeriesChangeListener listener3 = spy(new MatrixSeriesCollection());
			testMatrixSeries.addChangeListener(listener1);
			testMatrixSeries.addChangeListener(listener2);
			testMatrixSeries.addChangeListener(listener3);
			testMatrixSeries.setNotify(false);
			testMatrixSeries.notifyListeners(new SeriesChangeEvent(3));
			
			verify(listener1,times(1)).seriesChanged(new SeriesChangeEvent(15));
			verify(listener2,times(1)).seriesChanged((SeriesChangeEvent)any());
			verify(listener3,times(1)).seriesChanged((SeriesChangeEvent)any());	
		}
		
		@Test
		public void TestAddingAndFiringPropertyListeners() {
			PropertyChangeListener listener1 = spy(new MetalLabelUI());
			PropertyChangeListener listener2 = spy(new MetalLabelUI());
			PropertyChangeListener listener3 = spy(new MetalLabelUI());
			testMatrixSeries.addPropertyChangeListener(listener1);
			testMatrixSeries.addPropertyChangeListener(listener2);
			testMatrixSeries.addPropertyChangeListener(listener3);
			testMatrixSeries.firePropertyChange("property",1,10);
			
			verify(listener1,times(1)).propertyChange((PropertyChangeEvent)any());
			verify(listener2,times(1)).propertyChange((PropertyChangeEvent)any());
			verify(listener3,times(1)).propertyChange((PropertyChangeEvent)any());
		}
		
		@Test
		public void TestAddingAndFiringVetoableListeners() throws PropertyVetoException {
			VetoableChangeListener listener1 = spy(new BeanContextSupport());
			VetoableChangeListener listener2 = spy(new BeanContextSupport());
			VetoableChangeListener listener3 = spy(new BeanContextSupport());
			testMatrixSeries.addVetoableChangeListener(listener1);
			testMatrixSeries.addVetoableChangeListener(listener2);
			testMatrixSeries.addVetoableChangeListener(listener3);
			testMatrixSeries.fireVetoableChange("property",1,10);
			
			verify(listener1,times(1)).vetoableChange((PropertyChangeEvent)any());
			verify(listener2,times(1)).vetoableChange((PropertyChangeEvent)any());
			verify(listener3,times(1)).vetoableChange((PropertyChangeEvent)any());
		}
		
		@Test
		public void TestsetDescriptionIntegeration() {
			PropertyChangeListener listener = spy(new MetalLabelUI());
			testMatrixSeries.addPropertyChangeListener(listener);
			testMatrixSeries.setDescription("MyDescription");
			
			verify(listener,times(1)).propertyChange((PropertyChangeEvent)any());
		}
		
		@Test
		public void TestsetKey() throws PropertyVetoException {
			VetoableChangeListener listener = spy(new BeanContextSupport());
			testMatrixSeries.addVetoableChangeListener(listener);
			testMatrixSeries.setKey(1337);
			verify(listener,times(1)).vetoableChange((PropertyChangeEvent) any());
		}
		
}
