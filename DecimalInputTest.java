package numbers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import numbers.DecimalInput.TestHook;

public class DecimalInputTest {

	// For using hook methods that are not object-specific
	private static final TestHook hook = new DecimalInput("").new TestHook();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecimalInput() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsValid() {
		DecimalInput t1 = new DecimalInput("1234_560");
		assertTrue(t1.isValid());
	}
	
	/* *
	 * Turns a character into a string 
	 * Example: '.' -> "."
	 * */
	@Test
	public void testGetRegexOf() {
		assertEquals(".", hook.getRegexOf('.'));
		assertEquals("a", hook.getRegexOf('a'));
		assertEquals("9", hook.getRegexOf('9'));
	}
	
	/* *
	 * Checks whether a given string contains
	 * a given character
	 * Example: 'a' in "bcd" -> valid
	 * 			'9' in "a9xc1d" -> invalid
	 * */
	@Test
	public void testIsNotWithinString() {
		assertTrue(hook.isNotWithinString('a', "bcd"));
		assertTrue(hook.isNotWithinString('x', "Hello World!"));
		
		assertFalse(hook.isNotWithinString('9', "a9xc1d"));
		assertFalse(hook.isNotWithinString('n', "Write your name"));
	}
	
	/* *
	 * Checks whether a number is positive
	 * given a string
	 * Example: "+123" -> valid
	 * 			"-456" -> invalid
	 * */
	@Test
	public void testIsNumberPositive() {
		assertTrue(hook.isNumberPositive("+123"));
		assertTrue(hook.isNumberPositive("5.36"));
		
		assertFalse(hook.isNumberPositive("-456"));
		assertFalse(hook.isNumberPositive(""));
		assertFalse(hook.isNumberPositive("-32.0"));
	}
	
	/* *
	 * Removes the sign of a number
	 * given a string
	 * Example: "+123" -> "123"
	 * 			"-456" -> "456"
	 * */
	@Test
	public void testRemoveSign() {
		assertEquals("123", hook.removeSign("+123"));
		assertEquals("3.4", hook.removeSign("3.4"));
		assertEquals("456", hook.removeSign("-456"));
		assertEquals("", hook.removeSign(""));
		assertEquals("", hook.removeSign("+"));
		assertEquals("", hook.removeSign("-"));
	}
	
	/* *
	 * Removes all padding in a number
	 * given a string
	 * Example: "1__234" -> "1234"
	 * 			"-1_234" -> "-1234"
	 * */
	@Test
	public void testRemovePadding() {
		assertEquals("1234", hook.removePadding("1__234"));
		assertEquals("-1234", hook.removePadding("-1_234"));
		assertEquals("1234567", hook.removePadding("1_234_____567"));
		assertEquals("", hook.removePadding("______________"));
	}
}
