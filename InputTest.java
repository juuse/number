package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.TestHook;

/** Some example tests of parser **/
public class InputTest {

	// For using hook methods that are not object-specific
	private static final TestHook hook = new DecimalInput("").new TestHook();

	/**getRegexOf tests**/

	/**hasValidLeadingPadding tests **/

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
	public void test_isValid_no_decimal(){ assertFalse(new DecimalInput("1").isValid());}

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

}
