package com.saggu.data.structure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the maximum number of occurrences of any substring
 * under the following rules: The number of unique characters in the substring
 * must be less than or equal to maxLetters, and the substring size must be
 * between minSize and maxSize inclusive.
 * 
 * Medium:
 * https://medium.com/deluxify/leetcode-1297-maximum-number-of-occurrences-of-a-substring-69870842a05d
 * 
 */
public class FindRequiredSubstring {
	
	public static void main(String args[]) {
		
	}
	

	public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
		Map<String, Integer> mapOfSubstring = new HashMap<>();
		Map<Character, Integer> mapOfChars = new HashMap<>();
		int maxCount = 0;

		for (int left = 0, cur = 0; cur < s.length(); cur++) {
			char c = s.charAt(cur);
			mapOfChars.put(c, mapOfChars.getOrDefault(c, 0) + 1);

			while (mapOfChars.size() > maxLetters || cur - left + 1 > minSize) {
				var c1 = s.charAt(left);
				mapOfChars.put(c1, mapOfChars.get(c1) - 1);
				if (mapOfChars.get(c1) == 0) {
					mapOfChars.remove(c1);
				}
				left++;
			}

			if (cur - left + 1 == minSize) {
				String sub = s.substring(left, cur + 1);
				mapOfSubstring.put(sub, mapOfSubstring.getOrDefault(sub, 0) + 1);
				maxCount = Math.max(maxCount, mapOfSubstring.get(sub));
			}
		}

		return maxCount;
	}
}
