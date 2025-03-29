package com.kenzie.expandingwindow.minimumwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class MinimumWindowSubstring {

    /**
     * Given two strings s and t, return the minimum window substring of s such that every character in
     * t (including duplicates) is included in the window. If there is no such substring, return the
     * empty string "".
     *
     * Example:
     *   s = "ADOBECODEBANC"
     *   t = "ABC"
     *
     *   result = "BANC"
     *
     * @param s - the string from which to identify the shortest substring where each character in t appears.
     * @param t - the string containing all the characters that must appear in the substring from s.
     * @return the shortest substring of s in which each character in t appears.
     */
    public static String minimumWindowSubstring(String s, String t) {
        //credit: eduardo anguloe
        Map<Character, Integer> frequencyWordMap = new HashMap<>();
        Map<Character, Integer> frequencySubstringMap = new HashMap<>();
        int start = 0;
        int end = 0;
        String minimumString = "";
        //Frequency of letters in the search string
        for(int i = 0; i < t.length(); i++) {
            frequencyWordMap.merge(t.charAt(i), 1, Integer::sum);
        }
        while (end < s.length()) {
            frequencySubstringMap.clear();
            for(int i = start; i <= end; i++) {
                frequencySubstringMap.merge(s.charAt(i), 1, Integer::sum);
            }
            if (!contains(frequencyWordMap,frequencySubstringMap)) {
                //Expand our window by moving the end index
                end++;
            }
            else {
                if (minimumString.length() > s.substring(start,end+1).length() || minimumString.equals("")) {
                    minimumString = s.substring(start, end + 1);
                }
                //Shrink our window by moving the start index
                start++;
            }
        }
        return minimumString;
    }
    //Helper method
    public static boolean contains(Map<Character, Integer> t, Map<Character, Integer> s) {
        boolean contain = true;
        for (Map.Entry<Character, Integer> entry : t.entrySet()) {
            if (s.containsKey(entry.getKey())) {
                if (s.get(entry.getKey()) < entry.getValue()) {
                    contain = false;
                    break;
                }
            }
            else {
                contain = false;
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add();
        return contain;
    }
}
