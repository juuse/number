package numbers;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* Validation class for simple decimal numbers (an integer with optional fractional chunk) */
/* Global Precondition: number is not null nor does it have leading or trailing spaces 
 */
class DecimalInput {

	private static final char DECIMAL = '.';
	private static final char PADDING = '_';
	private static final Set<Character> SIGN_SET;
	private static final Set<Integer> VALID_CHAR_SET; // int set to match chars IntStreams
	static { // Setup set of valid signs
		Set<Character> signs = new HashSet<>();
		signs.add('+');
		signs.add('-');
		SIGN_SET = Collections.unmodifiableSet(signs);
	}
	static { // Setup set of valid characters in a decimal
		Set<Character> validChars = new HashSet<>();
		validChars.add('0');
		validChars.add('1');
		validChars.add('2');
		validChars.add('3');
		validChars.add('4');
		validChars.add('5');
		validChars.add('6');
		validChars.add('7');
		validChars.add('8');
		validChars.add('9');
		validChars.add(DECIMAL);
		validChars.add(PADDING);
		VALID_CHAR_SET = Collections.unmodifiableSet(validChars
				.stream()
				.mapToInt(Character::charValue)
				.mapToObj(Integer::valueOf)
				.collect(Collectors.toSet()));
	}

	private final boolean isPositive;
	private final String number;
	
	DecimalInput (String number) {
		assert number != null : "Number given should not be null.";
		assert number.trim().equals(number) 
				: "Number given should not have leading or trailing whitespace: \""+number+"\"";
		this.isPositive = isNumberPositive(number);
		this.number = removeSign(number);
	}

	public String toString() { return (isPositive ? "+" : "-")+removePadding(number); }
	
	boolean isInteger() { return !number.isEmpty() && isNotWithinString(DECIMAL,number); }
	
	boolean isValid() { return hasValidChars() && hasValidPadding() && (hasValidDecimalPoint() || isInteger()); }

	private boolean hasValidChars() { return number.chars().allMatch(VALID_CHAR_SET::contains); }
	
	private static boolean twoValidChunks(List<String> chunks){
		if(chunks.size() != 2)
			return false;
		return !chunks.get(0).equals("") && !chunks.get(1).equals("");
	}

	/* A number is considered to have a valid decimal point if none exist, or only one
	 * exists that splits the string into two further numbers.
	 */
	private boolean hasValidDecimalPoint() {
        List<String> numbers = Arrays.asList(getAllChunks());
		
        if(numbers.size() > 2)
            return false;
		
        return numbers.size() > 0 && twoValidChunks(numbers);
	}

	/* A number is considered to have valid padding if they only appear 
	 * in the place of a comma in the leading number.
	 */
	private boolean hasValidPadding() {
		List<String> numbers = Arrays.asList(getAllChunks());
		return (numbers.size() == 2 ? isNotWithinString(PADDING,numbers.get(1)) : true) 
			&& hasValidLeadingPadding(numbers.get(0));
	}
	
	private String[] getAllChunks() { return number.split(getRegexOf(DECIMAL)); }
	
	private static boolean hasValidLeadingPadding(String leading) {
		if(leading.length() == 0)
			return true;
		return (hasNoEdgePadding(leading)) && hasValidMiddlePadding(leading);
	}
	
	private static boolean hasNoEdgePadding(String leading) {
		return leading != null && leading.charAt(0) != PADDING && leading.charAt(leading.length()-1) != PADDING;
	}
	
	private static boolean hasValidMiddlePadding(String leading) {
		/* The padding (underscores) in the middle of a decimal are valid when 
		 * followed by a multiple of three digits
		 * 
		 * ex: 1_234, 1__234 => true
		 *     12_34, _1_234 => false
		 */
		final AtomicInteger digitCount = new AtomicInteger();
		return new StringBuilder(leading)
				.reverse()
				.chars()
				.peek(i -> digitCount.incrementAndGet())
				.filter(i -> PADDING == (char)i)
				.noneMatch(i -> digitCount.get() % 3 == 0);
	}
	
	private static String removePadding(String number) { return number.replaceAll(getRegexOf(PADDING), ""); }
	
	private static String removeSign(String number) {
		return number.isEmpty() || !SIGN_SET.contains(number.charAt(0)) ? number
				: number.substring(1, number.length());
	}
	
	private static boolean isNumberPositive(String number) {
		return !number.isEmpty() && number.charAt(0) != '-';
	}
	
	private static boolean isNotWithinString (char c, String str) { return str != null && str.indexOf(c) < 0; }
	
	private static String getRegexOf(char ch) {
		if(ch == DECIMAL){
			return "\\.";
		}
		return ""+ch;
	}
	
	class TestHook {
		boolean twoValidChunks(List<String> input) { return DecimalInput.twoValidChunks(input);}
		boolean hasValidLeadingPadding(String leading) {
			return DecimalInput.hasValidLeadingPadding(leading);
		}
		boolean hasNoEdgePadding(String leading) {
			return DecimalInput.hasNoEdgePadding(leading);
		}
		boolean hasValidMiddlePadding(String leading) { 
			return DecimalInput.hasValidMiddlePadding(leading); 
		}
		String removePadding(String number) { 
			return DecimalInput.removePadding(number);
		}
		String removeSign(String number) {
			return DecimalInput.removeSign(number);
		}
		boolean isNumberPositive(String number) {
			return DecimalInput.isNumberPositive(number);
		}
		boolean isNotWithinString (char c, String str) {
			return DecimalInput.isNotWithinString(c, str);
		}
		String getRegexOf(char ch) { 
			return DecimalInput.getRegexOf(ch);
		}
	}
}
