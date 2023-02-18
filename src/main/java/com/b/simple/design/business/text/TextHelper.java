package com.b.simple.design.business.text;

public class TextHelper {

	public String swapLastTwoCharacters(String str) {
		int length = str.length();

		if (length < 2)
			return str;

		char lastChar = str.charAt(length - 1);
		char secondLastChar = str.charAt(length - 2);
		return str.substring(0, length - 2) + lastChar + secondLastChar;
	}


	public String truncateAInFirst2Positions(String str) {
		if (str.length() < 2)
			return str.replaceAll("A", "");

		String firstTwoChars = str.substring(0, 2).replaceAll("A", "");
		firstTwoChars = firstTwoChars.replaceAll("A", "");
		String restOfString = str.substring(2);

		return firstTwoChars + restOfString;
	}
}
