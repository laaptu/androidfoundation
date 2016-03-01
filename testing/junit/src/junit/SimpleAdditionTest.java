package junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimpleAdditionTest {
	SimpleAddition simpleAddition;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Rule
	public MyOwnCustomRule myOwnCustomRule = new MyOwnCustomRule();

	// everything we write with annotation @Test becomes our testcase
	// here there are two test cases test() and secondTest()

	@Test
	public void test() {
		// SimpleAddition simpleAddition = new SimpleAddition();
		System.out.println("First test()");
		assertEquals(5, simpleAddition.addTwoNum(2, 3));
		assertEquals(10, simpleAddition.addTwoNum(-10, 20));
	}

	@Test
	public void secondTest() {
		// SimpleAddition simpleAddition = new SimpleAddition();
		System.out.println("Second test()");
		assertEquals(15, simpleAddition.multiplyNum(5, 3));
		assertTrue(simpleAddition.multiplyNum(10, 2) == 20);
		assertFalse(simpleAddition.addTwoNum(10, 3) == 23);
	}

	@Test
	public void arraySortTest() {
		System.out.println("arraySortTest()");
		int[] unsortedArray = { 5, 4, 3, 2, 1 };
		int[] sortedArray = { 1, 2, 3, 4, 5 };
		assertArrayEquals(sortedArray, simpleAddition.sortArrays(unsortedArray));
	}

	// making test case with exception
	@Test(expected = Exception.class)
	public void nullArrayTest() {
		System.out.println("nullArrayTest()");
		simpleAddition.sortArrays(null);
	}

	// testing exception with rules
	@Test
	public void nullArrayTest1() {
		System.out.println("nullArrayTest1()");
		expectedException.expect(NullPointerException.class);
		//expectedException.expectMessage("You are checking against null");
		simpleAddition.sortArrays(null);
	}

	// make the test case run with given time
	// meaning if the method takes more than time out time, it fails the test
	// but if the method takes less time than the allocated time, then it will
	// pass the test
	// it is in ms
	@Test(timeout = 200)
	public void timeOutTest() {
		System.out.println("timeOutTest()");
		for (int i = 0; i < 1000000; i++) {
			int[] dummyArr = { i, i + 1, i - 2 };
			simpleAddition.sortArrays(dummyArr);
		}
	}

	// logically first one test runs, then follows by another test
	@Before
	public void beforeCall() {
		simpleAddition = new SimpleAddition();
		System.out.println("########### Before any test call() ################");
	}

	@After
	public void afterCall() {
		simpleAddition = null;
		System.out.println("*********** After any test call() ********************");
	}

	// this is called before this class i.e. before any first test call
	@BeforeClass
	public static void beforeClass() {
		System.out.println("BEFORE CLASS");
	}

	// this is called after this class i.e after all test case have been run
	@AfterClass
	public static void afterClass() {
		System.out.println("AFTER CLASS");
	}
}
