package com.kenzie.expandingwindow.krepeatingelements;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class KRepeatingElements {

    /**
     * Given a string s and an integer k, return the length of the longest substring of s such that the
     * frequency of each character in this substring is greater than or equal to k.
     *
     * Example:
     *   s = aaabb
     *   k = 3
     *
     *   result = aaa
     *
     * @param s - the string from which to identify the longest substring where each character appears
     *          at least k times. s will contain only lowercase letters.
     * @param k - the minimum frequency which each character should appear in the substring. k will be
     *          a postive number.
     * @return the length of the longest substring of s where each character appears at least k times.
     */
    public static int kRepeatingElements(String s, int k) {
        //credit: hamza malik
        int maxLength = 0;
        for (int uniqueChars = 1; uniqueChars <= 26; uniqueChars++) {
            int[] charCount = new int[26];
            int start = 0;
            int end = 0;
            int countAtLeastK = 0;
            int uniqueCount = 0;

            while (end < s.length()) {
                if (uniqueCount <= uniqueChars) {
                    int index = s.charAt(end) - 'a';
                    if (charCount[index] == 0) {
                        uniqueCount++;
                    }
                    charCount[index]++;
                    if (charCount[index] == k) {
                        countAtLeastK++;
                    }
                    end++;
                } else {
                    int index = s.charAt(start) - 'a';
                    if (charCount[index] == k) {
                        countAtLeastK--;
                    }
                    charCount[index]--;
                    if (charCount[index] == 0) {
                        uniqueCount--;
                    }
                    start++;
                }
                if (uniqueCount == uniqueChars && uniqueCount == countAtLeastK) {
                    maxLength = Math.max(maxLength, end - start);
                }
            }
        }
        return maxLength;
    }
    }

