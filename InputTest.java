package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.TestHook;

/** Some example tests of parser **/
public class InputTest {

	// For using hook methods that are not object-specific
	private static final TestHook hook = new DecimalInput("").new TestHook();

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
		assertFalse(hook.isNotWithinString('d', null));
	}

	/**toString tests**/
	@Test
	public void testToString(){
		assertEquals("+1234.56", new DecimalInput("1_234.56").toString());
		assertEquals("-1234.56", new DecimalInput("-1_234.56").toString());
		//no padding
		assertEquals("+12", new DecimalInput("12").toString());
	}

	/**getRegexOf tests**/
	@Test
	public void testGetRegexOf() {
		assertEquals("\\.", hook.getRegexOf('.'));
		assertEquals("a", hook.getRegexOf('a'));
		assertEquals("9", hook.getRegexOf('9'));
	}

	/**hasValidLeadingPadding tests **/
	@Test
	public void test_leading_padding_empty_string(){
		assertTrue(hook.hasValidLeadingPadding(""));
	}

	@Test
	public void test_leading_padding_has_edge_has_middle(){

	}

	@Test
	public void test_leading_padding_has_edge_no_middle(){
	}

	@Test
	public void test_leading_padding_no_edge_has_middle(){
	}

	@Test
	public void test_leading_padding_no_edge_no_middle(){
	}
	
	/**hasNoEdgePadding tests **/
	@Test
	public void test_has_padding_front(){
		assertFalse(hook.hasNoEdgePadding("_123.2"));
		assertFalse(hook.hasNoEdgePadding("__123.2"));
	}

	@Test
	public void test_has_padding_end() {
		assertFalse(hook.hasNoEdgePadding("123.2_"));
		assertFalse(hook.hasNoEdgePadding("123_"));
	}

	@Test
	public void test_has_no_edge_padding(){
		assertTrue(hook.hasNoEdgePadding("123.2"));
		assertTrue(hook.hasNoEdgePadding("1_23.2"));
		assertTrue(hook.hasNoEdgePadding("1__23"));
	}

	/** hasValidMiddlePadding tests **/
	/* Example: 1_234 -> valid */
	@Test
	public void test_padding_nominal() {
		assertTrue(hook.hasValidMiddlePadding("1_234"));
	}

	/* Example: 1__234 -> valid */
	@Test 
	public void test_padding_long_underscore() {
		assertTrue(hook.hasValidMiddlePadding("1__234"));
	}

	/* Example: 12_34 -> invalid */
	@Test
	public void test_padding_bad_underscore() {
		assertFalse(hook.hasValidMiddlePadding("12_34"));
	}

	/* Example: _1_234 -> invalid */
	@Test
	public void test_padding_leading_underscore() {
		assertFalse(hook.hasValidMiddlePadding("_1_234"));
	}

	@Test
	public void test_isValid_no_decimal(){ assertTrue(new DecimalInput("1").isValid());}

	/**isValid Tests**/
	@Test
	public void test_legal_decimal(){
		assertTrue(new DecimalInput("0.1").isValid());
	}

	@Test
	public void test_illegal_decimal(){
		assertFalse(new DecimalInput(".1").isValid());
		assertFalse(new DecimalInput("0.").isValid());
	}

	@Test
	public void testHasAllValidChars() {

	}

	@Test
	public void test_legal_decimal_illegal_padding(){

	}

	@Test
	public void test_illegal_decimal_illegal_padding(){

	}

	@Test
	public void test_legal_decimal_legal_padding(){
		assertTrue(new DecimalInput("1_234.0").isValid());
	}

}
