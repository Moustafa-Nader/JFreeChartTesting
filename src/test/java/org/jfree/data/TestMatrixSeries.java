package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.xy.MatrixSeries;
import org.junit.Test;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

public class TestMatrixSeries {
	
	
	//equal function test
	
	@Test
	public void TestequalValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.equals(new MatrixSeries("testMat", 3, 3)));
	}
	
	@Test
	public void TestequalInvalidname() {		//inequal names
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertFalse(testMat.equals(new MatrixSeries("testMat1", 3, 3)));
	}
	
	@Test
	public void TestequalInvalidrows() {		//inequal rows
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertFalse(testMat.equals(new MatrixSeries("testMat", 4, 3)));
	}
	
	@Test
	public void TestequalInvalidcols() {		//inequal columns
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertFalse(testMat.equals(new MatrixSeries("testMat", 3, 4)));
	}
	
	@Test
	public void TestequalInvalidValues() {		//inequal values
		MatrixSeries testMat1 = new MatrixSeries("testMat1", 3, 3);
		MatrixSeries testMat2 = new MatrixSeries("testMat2", 3, 3);
		testMat2.update(1, 1, 9);
		assertFalse(testMat1.equals(testMat2));
	}
	
	@Test
	public void TestequalValidNull() {			//since null permitted
		MatrixSeries testMat = new MatrixSeries("testMat1", 3, 3);
		assertFalse(testMat.equals(null));
	}
	
	//get function test
	
	@Test
	public void TestgetValidDefault() {			//Checks get function && default value is 0
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(0.0, testMat.get(1, 1), 0.0001);
	}
	
	@Test
	public void TestgetValidnonDefaultCornerCase00() {
		MatrixSeries testMat = new MatrixSeries("testMat", 2, 2);
		testMat.update(0, 0, 1.1111);
		testMat.update(0, 1, 1.2222);
		testMat.update(1, 0, 1.3333);
		testMat.update(1, 1, 1.4444);
		assertEquals(1.1111, testMat.get(0, 0), 0.0001);
	}
	
	@Test
	public void TestgetValidnonDefaultCornerCase01() {
		MatrixSeries testMat = new MatrixSeries("testMat", 2, 2);
		testMat.update(0, 0, 1.1111);
		testMat.update(0, 1, 1.2222);
		testMat.update(1, 0, 1.3333);
		testMat.update(1, 1, 1.4444);
		assertEquals(1.2222, testMat.get(0, 1), 0.0001);
	}
	
	@Test
	public void TestgetValidnonDefaultCornerCase10() {
		MatrixSeries testMat = new MatrixSeries("testMat", 2, 2);
		testMat.update(0, 0, 1.1111);
		testMat.update(0, 1, 1.2222);
		testMat.update(1, 0, 1.3333);
		testMat.update(1, 1, 1.4444);
		assertEquals(1.3333, testMat.get(1, 0), 0.0001);
	}
	
	@Test
	public void TestgetValidnonDefaultCornerCase11() {
		MatrixSeries testMat = new MatrixSeries("testMat", 2, 2);
		testMat.update(0, 0, 1.1111);
		testMat.update(0, 1, 1.2222);
		testMat.update(1, 0, 1.3333);
		testMat.update(1, 1, 1.4444);
		assertEquals(1.4443, testMat.get(1, 1), 0.0001);
	}
	
	@Test
	public void TestgetValidnonDefaultij() { 		//checks for non-corner data
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(1, 1, 1.1111);
		assertEquals(1.1111, testMat.get(1, 1), 0.0001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestgetInvalidNegativeRowParams() {			//checks for negative params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(1.1111, testMat.get(-1, 2), 0.0001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestgetInvalidNegativeColParams() {			//checks for negative params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(1.1111, testMat.get(2, -1), 0.0001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestgetInvalidOutofBoundRowParams() {			//high positive params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(1.1111, testMat.get(5, 2), 0.0001);
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void TestgetInvalidOutofBoundColParams() {			//high positive params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(1.1111, testMat.get(2, 5), 0.0001);
	}
	
	//getColumnsCount function test
	
	@Test
	public void TestgetColumnsCountValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(3,testMat.getColumnsCount());
	}
	
	
	//getitem function test
	
	@Test
	public void TestgetItemValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(1, 1, 9.9);
		assertEquals(9.9, ((Double)testMat.getItem(4)), 0.0001);
	}
	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestgetItemInvalidNegativeParams() { 		//negative params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(1, 1, 9.9);
		testMat.getItem(-9);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void TestgetItemInvalidOutOfBoundParams() { 		//high positive params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(1, 1, 9.9);
		testMat.getItem(9);
	}
	
	@Test
	public void TestgetItemInvalidLowerBoundParams() { 		//lower bound param
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(0, 0, 9.9);
		assertEquals(9.9, ((Double)testMat.getItem(0)), 0.0001);
	}
	
	@Test
	public void TestgetItemInvalidUpperBoundParams() { 		//upper bound param
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(2, 2, 9.9);
		assertEquals(9.9, ((Double)testMat.getItem(8)), 0.0001);
	}
	
	//getItemColumn function test
	
	
	@Test
	public void TestgetItemColumnValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(2, testMat.getItemColumn(8));
	}
	
	@Test
	public void TestgetItemColumnInvalidNegativeParam() {  //Negative params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemColumn(-9) <= 0);
	}
	
	@Test
	public void TestgetItemColumnInvalidOutofBoundParam() {  //high positive params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemColumn(12) >= 0);
	}
	
	@Test
	public void TestgetItemColumnValidLowerBound() {  //lower bound param
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemColumn(0) == 0);
	}
	
	@Test
	public void TestgetItemColumnValidUpperBound() {  //upper bound params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemColumn(8) >= 2);
	}
	
	//getItemCount function test
	
	
	@Test
	public void TestgetItemCountValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemCount() == 9);
	}
	
	
	//getItemRow function test
	
	
	@Test
	public void TestgetItemRowValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(1, testMat.getItemRow(3));
	}
	
	@Test
	public void TestgetItemRowInvalidNegativeParam() {	//Negative params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemRow(-9) <= 0);
	}
	
	@Test
	public void TestgetItemRowInvalidOutofBoundParam() {  //high positive params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemColumn(99) >= 0);
	}
	
	@Test
	public void TestgetItemRowValidLowerBound() {  //lower bound param
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemRow(0) == 0);
	}
	
	@Test
	public void TestgetItemRowValidUpperBound() {  //upper bound params
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertTrue(testMat.getItemRow(8) >= 2);
	}
	
	//getRowCount function test
	
	@Test
	public void TestgetRowsCountValid() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		assertEquals(3,testMat.getRowCount());
	}
	
	
	//zeroAll function test
	public void TestzeroAllValidBoundaryCornerCase00() { 
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(0, 0,99.9);
		testMat.zeroAll();
		assertTrue(testMat.get(0, 0) == 0.0);
	}
	
	
	public void TestzeroAllValidBoundaryCornerCase02() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(0, 2,99.9);
		testMat.zeroAll();
		assertTrue(testMat.get(0, 2) == 0.0);
	}
	
	public void TestzeroAllValidBoundaryCornerCase20() { 
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(2, 0,99.9);
		testMat.zeroAll();
		assertTrue(testMat.get(2, 0) == 0.0);
	}
	
	public void TestzeroAllValidBoundaryCornerCase22() {
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(2, 2,99.9);
		testMat.zeroAll();
		assertTrue(testMat.get(2, 2) == 0.0);
	}
	
	public void TestzeroAllValidBoundaryij() { // non Boundary value
		MatrixSeries testMat = new MatrixSeries("testMat", 3, 3);
		testMat.update(2, 2,99.9);
		testMat.zeroAll();
		assertTrue(testMat.get(2, 2) == 0.0);
	}
	
	
}
