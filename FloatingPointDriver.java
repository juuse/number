package numbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

/* Driver to build and run FloatingPointParser on input readers */
public final class FloatingPointDriver {
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		FloatingPointDriver driver = new FloatingPointDriver();
		Optional<Double> result = driver.runFloatingPointParser(input);
		printOutput(result);
	}

	// Prints out the given Double (or "Invalid Input" if given empty result)
	public final static void printOutput(Optional<Double> result) {
		System.out.println(result.isPresent() ? result.get() : "Invalid Input");
	}

	// Retrieves input from the given BufferedReader and parses it to a Double
	public final Optional<Double> runFloatingPointParser(BufferedReader input) throws IOException {
		FloatingPointParser parser = getFloatingPointParser(input);
		return parser.isValidInput() ? Optional.of(parser.parseDouble()) : Optional.empty();
	}

	//helper method that converts line to uppercase
	private static String toUpperCase(String line){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < line.length(); i++){
			if(!Character.isUpperCase(line.charAt(i)))
				builder.append(Character.toUpperCase(line.charAt(i)));
			else
				builder.append(line.charAt(i));
		}
		
		return builder.toString();
	}
	
	private static String removeWhiteSpace(String line){
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < line.length(); i++){
			if(!Character.isWhitespace(line.charAt(i)))
				builder.append(line.charAt(i));
		}
		return builder.toString();
	}
	
	// Reads from the input reader and builds a parser
	private final FloatingPointParser getFloatingPointParser(BufferedReader input) throws IOException {

		if(input == null)
			throw new NullPointerException("No input received.");
		
		String line = removeWhiteSpace(input.readLine());
		
		if (line.length() != 0) {
			//convert to uppercase
			return FloatingPointParser.build(toUpperCase(line));
		}
			//input is all whitespace
		return FloatingPointParser.build("bad input");
	}

	class TestHook {
		
		public String toUpperCase(String line){
			return FloatingPointDriver.toUpperCase(line);
		}

		public String removeWhiteSpace(String line){
			return FloatingPointDriver.removeWhiteSpace(line);
		}
	}
}
