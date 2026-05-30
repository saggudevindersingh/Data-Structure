package com.saggu.data.structure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility to find the length of the longest substring without repeating characters.
 *
 * <p>This class provides a sliding-window implementation in {@link #lengthOfLongestSubstring(String)}.
 * The method expands a right pointer across the string while tracking character counts in a map.
 * When a duplicate is detected the left pointer is advanced until the window becomes valid again.
 *
 * <p>Example: for input "abcdab" the longest substring without repeating characters is
 * "abcd" and the method returns 4.
 *
 * <p>Notes:
 * - The current implementation uses a character count map and a helper {@link #isValid(Map)}
 *   that checks for counts &gt; 1. This is straightforward to understand but not optimal
 *   (it may re-scan the map repeatedly). A more efficient O(n) variant uses a map of last-seen
 *   indices to jump the left pointer directly.
 * - The implementation intentionally preserves the original algorithm structure; see method
 *   level docs for a brief explanation of the algorithm and complexity.
 */
public class LongestUniqueSubString {

    /**
     * Empty main so the class can be run; nothing executed by default.
     * You can add quick manual tests here if required.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String args[]) {
        // Quick manual tests for lengthOfLongestSubstring
        LongestUniqueSubString solver = new LongestUniqueSubString();
        String[] inputs = {
            "abcdab",
            "abcabcbb",
            "bbbbb",
            "",
            "pwwkew",
            " ",
            "au",
            "aab"
        };
        int[] expected = {4, 3, 1, 0, 3, 1, 2, 2};

        for (int i = 0; i < inputs.length; i++) {
            String s = inputs[i];
            int exp = expected[i];
            int actual = solver.lengthOfLongestSubstring(s);
            System.out.printf("Input: \"%s\" | Expected: %d | Actual: %d%n", s, exp, actual);
        }

    }

    /**
     * Returns the length of the longest substring of {@code str} that contains only unique
     * (non-repeating) characters.
     *
     * <p>Algorithm (sliding window):
     * - Use two indices (fp = left, sp = right) that define a window [fp, sp].
     * - Move the right pointer {@code sp} step-by-step and increment the count for the
     *   new character in a map.
     * - While the window contains any duplicate (checked with {@link #isValid(Map)}),
     *   move the left pointer {@code fp} forward and decrement counts to restore validity.
     * - Track the maximum window length encountered.
     *
     * Complexity:
     * - Time: not strictly O(n) in this exact implementation, because {@link #isValid(Map)}
     *   scans the map and may be called repeatedly; an optimal variant achieves O(n).
     * - Space: O(k) where k is the number of distinct characters present in the current window
     *   (bounded by the character set size).
     *
     * @param str the input string to search
     * @return length of the longest substring containing unique characters; 0 for null/empty input
     */
    public int lengthOfLongestSubstring(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        int fp = 0;
        int sp = 0;

        int maxLength = 0;

        Map<Character, Integer> map = new HashMap<>();

        int length = str.length();
        while (sp < length) {
            char character = str.charAt(sp);
            map.put(character, map.getOrDefault(character, 0) + 1);

            while (fp < sp && !isValid(map)) {
                // decrement count of the character at the left pointer as we shrink the window
                char characterAtFp = str.charAt(fp);
                map.put(characterAtFp, map.get(characterAtFp) - 1);
                fp++;
            }
            maxLength = Math.max(maxLength, sp - fp + 1);
            sp++;
        }
        return maxLength;
    }

    /**
     * Checks whether the current character count map represents a valid window
     * (i.e. no character has count &gt; 1).
     *
     * @param map character &rarr; count map for current window
     * @return true if all counts are 1 (no duplicates), false otherwise
     */
    private boolean isValid(Map<Character, Integer> map) {
        for (char charcater : map.keySet()) {
            if (map.get(charcater) > 1) {
                return false;
            }
        }
        return true;
    }

}