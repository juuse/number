package numbers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FloatingPointDriverTest {

	private static final FloatingPointDriver.TestHook hook = new FloatingPointDriver().new TestHook();
	private FloatingPointDriver test = new FloatingPointDriver();
	 
	@Test
	public void testToUpperCase(){
		assertEquals("", hook.toUpperCase(""));
		assertEquals("HI", hook.toUpperCase("HI"));
		assertEquals("HI", hook.toUpperCase("hI"));
	}

	@Test
	public void removeWhiteSpace(){
		assertEquals("", hook.removeWhiteSpace(""));
		assertEquals("hi", hook.removeWhiteSpace(" hi"));
		assertEquals("hi", hook.removeWhiteSpace("hi "));
		assertEquals("hi", hook.removeWhiteSpace("hi"));
	}

	/**test runFloatingPointParser**/
	@Test(expected=NullPointerException.class)
	public void test_null() throws IOException {
		assertEquals(Optional.empty(), test.runFloatingPointParser(null).get());
	}

	@Test (expected= NoSuchElementException.class)
	public void test_all_whitespace() throws IOException {
	    Reader inputString = new StringReader("  ");
	    BufferedReader reader = new BufferedReader(inputString);
		assertEquals("Input not valid before parsing.", test.runFloatingPointParser(reader).get());
	}

	@Test
    public void test_valid_string() throws IOException {
        Reader inputString = new StringReader("1.23");
        BufferedReader reader = new BufferedReader(inputString);
        assertEquals(new Double(1.23), test.runFloatingPointParser(reader).get());
    }
}
