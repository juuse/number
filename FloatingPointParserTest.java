package numbers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FloatingPointParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseDouble() {
		FloatingPointParser t1 = FloatingPointParser.build("");
		FloatingPointParser t2 = FloatingPointParser.build("198.23");
		FloatingPointParser t3 = FloatingPointParser.build("-01.23456E+003");
		FloatingPointParser t4 = FloatingPointParser.build("1234_560e-3");
		FloatingPointParser t5 = FloatingPointParser.build("12.3456e2");
		FloatingPointParser t6 = FloatingPointParser.build("00123456e-2");
		
		assertEquals(new Double(-01.23456E+003), t3.parseDouble());
		fail("Not yet implemented");
	}

	@Test
	public void testIsValidInput() {
		FloatingPointParser f1 = FloatingPointParser.build("");
		FloatingPointParser f2 = FloatingPointParser.build("e10");
		FloatingPointParser f3 = FloatingPointParser.build("6");
		FloatingPointParser f4 = FloatingPointParser.build("12__34");
		FloatingPointParser f5 = FloatingPointParser.build("hello");
		FloatingPointParser f6 = FloatingPointParser.build("5e2.3");
		
		FloatingPointParser t1 = FloatingPointParser.build("198.23");
		FloatingPointParser t2 = FloatingPointParser.build("2.3e5");
		FloatingPointParser t3 = FloatingPointParser.build("-01.23456E003");
		FloatingPointParser t4 = FloatingPointParser.build("1234_560e-3");
		FloatingPointParser t5 = FloatingPointParser.build("12.3456e2");
		FloatingPointParser t6 = FloatingPointParser.build("00123456e-2");
		
		assertFalse(f1.isValidInput());
		assertFalse(f2.isValidInput());
		assertFalse(f3.isValidInput());
		assertFalse(f4.isValidInput());
		assertFalse(f5.isValidInput());
		assertFalse(f6.isValidInput());
		
		assertTrue(t1.isValidInput());
		assertTrue(t2.isValidInput());
		assertTrue(t3.isValidInput());
		assertTrue(t4.isValidInput());
		assertTrue(t5.isValidInput());
		assertTrue(t6.isValidInput());
	}
	
	/* *
	 * Makes a new floating point parser 
	 * takes a number as a string
	 * 
	 * */
	@Test
	public void testBuild() {
		assertEquals("", FloatingPointParser.build(""));
		assertEquals("198.23", FloatingPointParser.build("198.23"));
		assertEquals("-1_182.4", FloatingPointParser.build("-1_182.4"));
	}

}
