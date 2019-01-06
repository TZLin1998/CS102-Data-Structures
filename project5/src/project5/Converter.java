package project5;

/**
 * This class contains various methods for converting numbers written using different number systems: binary, decimal, hexadecimal. The decimal numbers are represented using int type. 
 * The binary and hexadecimal numbers are represented using binary and hexadecimal strings.
 * The valid strings representing binary numbers are formatted as:
 * "0bBB...BB" 
 * where BB...BB is a sequence of 1 to 31 binary characters. Binary characters are 0, 1.
 * The valid strings representing hexadecimal numbers are formatted as:
 * "0xHH...HH" 
 * where HH...HH is a sequence of 1 to 8 hexadecimal characters. 
 * Hexadecimal characters are 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F.
 * @author Tianze Lin 
 * @version 11/19/2018
 */

public class Converter extends Object{
	
	/**
     * A string array that stores 4-character binary representations from 1 to 16.
     */
	private static String binary[] = {"0000","0001","0010","0011","0100","0101","0110","0111",
			"1000","1001","1010","1011","1100","1101","1110","1111"};
	/**
     * A char array that stores hexadecimal representations from 1 to 16.
     */
	private static char hexChar[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C',
			'D','E','F'};	
	
	/**
     * Converts binary strings to decimal numbers.
     * More specifically given a valid string representing a binary number 
     * returns a non-negative decimal integer with the same value.
     * @param binary the binary string to be converted
     * @return the decimal number equal in value to the binary string passed as the parameter 
     * @throws IllegalArgumentException if the binary string passed to the function is invalid
     */
	public static int binaryToDecimal​(String binary) throws IllegalArgumentException{
		//Check the input string. Throw IllegalArgumentException if invalid.
		if(binary == null || binary.length()<3 || binary.length()>33){
			throw new IllegalArgumentException("Binary string is invalid.");
		}
		if(binary.charAt(0) != '0' || binary.charAt(1) != 'b') {
			throw new IllegalArgumentException("Binary string is invalid.");
		}
		for(int i=2; i<binary.length(); i++) {
			if(binary.charAt(i) != '0' && binary.charAt(i) != '1') {
				throw new IllegalArgumentException("Binary string is invalid.");
			}
		}
		
		/* Call the recursive function. The second parameter is 2 
		 * since we are converting a binary string
		 */
		return anyBaseToDecimal(binary.substring(2), 2, binary.length()-2);
	}

	/**
     * Converts binary strings to hexadecimal strings. 
     * More specifically given a valid string representing a binary number 
     * returns the string representing the hexadecimal number with the same value.
     * @param binary the binary string to be converted
     * @return the hexadecimal string equal in value to the binary string passed 
     * as the parameter or null if the binary string is not valid 
     */
	public static String binaryToHex(String binary) {
		//Check the input binary sequence; if it's invalid, return null.
		if(binary == null || binary.length()<3 || binary.length()>33){
			return null;
		}
		if(binary.charAt(0) != '0' || binary.charAt(1) != 'b') {
			return null;
		}
		for(int i=2; i<binary.length(); i++) {
			if(binary.charAt(i) != '0' && binary.charAt(i) != '1') {
				return null;
			}
		}
		
		//pad the binary sequence with zeros
		String str = "";
		if(binary.length()-2 > 4) {
			for(int i=0; i < 4-(binary.length()-2) % 4; i++) {
				str += "0";
			}
		}else {
			for(int i=0; i < 4-(binary.length()-2); i++) {
				str += "0";
			}
		}
		str += binary.substring(2);
		
		//call the recursive function and compute number of leading zeros
		String result = binaryToHex(str, str.length());
		int counter = 0;
		for(int i=0; i<result.length();i++) {
			if(result.charAt(i) != '0') {
				break;
			}
			counter++;
		}
		
		//if string is zero, then return "0b0"
		if(counter == result.length()) {
			return "0x0";
		}
		//Add prefix in the front and return the result without leading zeros
		return "0x" + result.substring(counter);
	}
	
	/**
     * Converts decimal numbers to binary strings. 
     * More specifically given a non-negative decimal integer 
     * returns the string representing the binary number with the same value.
     * @param decimal the decimal number to be converted
     * @return the binary string equal in value to the decimal number passed as the parameter 
     * or null if the decimal is negative
     */
	public static String decimalToBinary(int decimal) {
		//return null if the decimal is negative
		if(decimal < 0) {
			return null;
		}
		/* Add the prefix and call the recursive function
		 * The second parameter is 2 because we want a binary string
		 */
		return "0b"+decimalToAnyBase(decimal, 2);
	}

	/**
     * Converts hexadecimal strings to binary strings.
     * More specifically given a valid string representing a hexadecimal number 
     * returns the string representing the binary number with the same value.
     * @param hex the hexadecimal string to be converted
     * @return the binary string equal in value to the binary string passed 
     * as the parameter or null if the binary string is not valid 
     */
	public static String hexToBinary(String hex) {
		//Check the input. Return null if invalid.
		if(hex == null || hex.length()<3 || hex.length()>10){
			return null;
		}
		if(hex.charAt(0) != '0' || hex.charAt(1) != 'x') {
			return null;
		}
		for(int i=2; i<hex.length(); i++) {
			if( hex.charAt(i)<48 || hex.charAt(i)>71 || (hex.charAt(i)>57 && hex.charAt(i)<65)) {
				return null;
			}
		}
		
		//Call the recursive function and obtain the binary string
		String result = hexToBinary(hex.substring(2),hex.length()-2);
		
		//Count number of leading zeros in the binary string
		int counter = 0;
		for(int i=0; i<result.length();i++) {
			if(result.charAt(i) != '0') {
				break;
			}
			counter++;
		}
		
		//if string is zero, then return "0b0"
		if(counter == result.length()) {
			return "0b0";
		}
		//return the binary string without leading zeros and add prefix in the front 
		return "0b" + result.substring(counter);
	}
	
	/**
     * A private method that converts a number of any base to decimal numbers.
     * More specifically given a valid string representing a number with any base 
     * returns a decimal integer with the same value.
     * @param value the original string of any base
     * @param base the base of the original string
     * @param valueLength the length of the original string
     * @return the decimal number equal in value to the string passed as the parameter 
     */
	private static int anyBaseToDecimal(String value, int base, int valueLength) {
		//Base Case: if string length is 0, return 0;
	    if (value.length() < 1) {
	        return 0;
	    }
	    
	    /* Call the function recursively without last character and 
	     * add the return value with the appropriate decimal representation of last character.
	     */
	    if(value.charAt(value.length()-1) > 57) {
	    	return anyBaseToDecimal(value.substring(0, value.length() - 1), base, valueLength) + 
	    		(value.charAt(value.length()-1)-55)*(int) Math.pow(base, valueLength-value.length());
	    }else {
	    	return anyBaseToDecimal(value.substring(0, value.length() - 1), base, valueLength) + 
		    		(value.charAt(value.length()-1)-48)*(int) Math.pow(base, valueLength-value.length());
	    }
	}

	/**
     * A private method that converts binary strings to hexadecimal strings. 
     * More specifically given a valid string representing a binary number 
     * returns the string representing the hexadecimal number with the same value.
     * @param binary the binary string to be converted
     * @param length the length of the binary string
     * @return the hexadecimal string equal in value to the binary string passed 
     * as the parameter. 
     */
	private static String binaryToHex(String binary, int length) {
		/* Base case is in the end because the function 
		 * will not be called at the base case.
		 */
		
		String str = ""; 
		int counter = 0;
		
		/* First, convert the rightmost four symbols to a string.
		 * Each character of this string is the product of
		 * each symbol and 2 to the power of the position of this symbol.
		 * For example, we can convert "1010" to "8020". 
		 * The leftmost char is 8 since 1*(2^3) = 8
		 */
		
		for(int i=binary.length()-4; i<binary.length() ;i++) {
			str += (binary.charAt(i) - 48) * (int) Math.pow(2, 3-counter);
			counter++;
		}
		
		//Next, compute the sum of this string.
		
		counter = 0;
		for(int i=0; i<4; i++) {
			counter += str.charAt(i) - 48;
		}
		
		/* Finally, convert this string to a hexadecimal character.
		 * Base Case: If this is the last four symbols of the original binary sequence, 
		 * then return the appropriate hexadecimal character and stop the recursion.  
		 * Otherwise, call the function recursively and 
		 * add the appropriate hexadecimal character in the end.
		 */
		
		if(counter>9 && length !=4) {
			return binaryToHex(binary.substring(0,binary.length()-4), length-4) + (char) (counter+55);
		}else if(counter>9 && length == 4){
			return "" + (char) (counter + 55);
		}else if(counter<=9 && length !=4) {
			return binaryToHex(binary.substring(0,binary.length()-4), length-4) + (char) (counter+48);
		}else {
			return "" + (char) (counter+48);
		}
	}
	
	/**
     * A private method that converts decimal numbers to number of any base. 
     * More specifically given a non-negative decimal integer 
     * returns the string representing the number of required base with the same value.
     * @param num the decimal number to be converted
     * @param base the required base of the new number
     * @return the string representation of the number of any base
     * equal in value to the decimal number passed as the parameter 
     */
	private static String decimalToAnyBase(int num,int base) {       
		//Base Case 1: if value is smaller than base and 10, return it 
	    if(num<base && num<10) {
	        return num+"";
	    }
	    /* Base Case 2: if value is smaller than base and greater than 10, 
	     * return its hexadecimal representation.
	     */
	    if(num<base && num>=10) {
	        return (char)(num%base-10+'A')+"";
	    }
	    
	    int rem = num % base;
	    /* Call the function recursively with quotient of value and base as first parameter;
	     * Add the appropriate representation of remainder in the end
	     */
	    return decimalToAnyBase(num/base, base) +
	    		((rem>=10)? (char)(rem-10+'A')+"":rem);
	}
	
	/**
     * A private method that converts hexadecimal strings to binary strings.
     * More specifically given a valid string representing a hexadecimal number 
     * returns the string representing the binary number with the same value.
     * @param hex the hexadecimal string to be converted
     * @param hexLength the length of the hexadecimal string
     * @return the binary string equal in value to the binary string passed as the parameter  
     */
	private static String hexToBinary(String hex, int hexLength) {
		/* Base case is in the end since at the base case, we won't call this function.
		 * First, compute the position of last character of the hexadecimal string.
		 */
		int counter;
		for(counter=0; counter<hexChar.length;counter++) {
			if(hex.charAt(hex.length()-1) == hexChar[counter]) {
				break;
			}
		}
		/* Base Case: If this is the last character, then return its binary representation.
		 * Otherwise, call the function recursively without last character and 
		 * add the binary representation of last character in the end.
		 */
		if(hexLength == 1) {
			return binary[counter];
		}else {
			return hexToBinary(hex.substring(0,hex.length()-1),hexLength-1) + binary[counter];
		}
	}
	
}
