package stringCalculatorTDD;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	public static int add(String numbers) {
		String[] num = splitter(numbers);
		int size=num.length;
		throwExceptionIfAnyNegative(num, size);
		return findSum(num, size);
	}

	private static void throwExceptionIfAnyNegative(String[] num, int size) {
		ArrayList<String> negative = new ArrayList<String>();
		for(int i=0; i<size; i++) {
			if(toInt(num[i])<0) {
				negative.add(num[i]);
			}
		}
		if(negative.size()>0) {
			throw new RuntimeException("negatives not allowed: " + String.join(", ",negative));
		}
	}

	private static int findSum(String[] num, int size) {
		int sum=0;
		for(int i=0; i<size; i++){
			sum = sum + toInt(num[i]);
		}
		return sum;
	}

	private static String[] splitter(String numbers) {
		if(numbers.isEmpty()) {
			return new String[0];
		} else if(isCustomDelimiterString(numbers)) {
			return splitUsingCustomDelimiter(numbers);
		}
		return splitUsingCommaAndNewLine(numbers);
	}

	private static boolean isCustomDelimiterString(String numbers) {
		return numbers.startsWith("//");
	}

	private static String[] splitUsingCommaAndNewLine(String numbers) {
		String[] num=numbers.split(",|\n");
		return num;
	}

	private static String[] splitUsingCustomDelimiter(String numbers) {
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(numbers);
		m.matches();
		String customDelim = m.group(1);
		String num=m.group(2);
		return num.split(Pattern.quote(customDelim));
	}

	private static int toInt(String numbers) {
		return Integer.parseInt(numbers);
	}
}
