package com.kenzie.slidingwindow.distinctelements;

import java.util.*;

/**
 * Contains a problem that can be solved using the Sliding Window Technique.
 */
public class DistinctElements {

    /**
     * Given a list of integers, find the number of distinct elements in every subset of size k.
     *
     * Example:
     *   input = [1, 3, 4, 2, 2, 5, 2]
     *   k = 3
     *
     *   result = [3, 3, 2, 2, 2]
     *
     * @param input - the list of integers from which to identify the distinct counts, size >= k
     * @param k - the size of sublists that should be considered when calculating the distinct counts, k >= 1
     * @return a list of distinct counts ordered by the sublist they relate to - the count of distinct
     *         elements for the sublist with items 0, 1, 2 should be first in the list followed by the
     *         count for items 1, 2, 3
     */
    public static List<Integer> countDistinctElements(List<Integer> input, int k) {
        //credit: Eduardo Angelo
        // TODO: Implement an algorithm that utilizes the sliding window technique
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> sets = new HashMap<>();

        for (int i = 0; i < k; i++){
            Integer num = input.get(i);
            if (sets.containsKey(num)){
                sets.put(num, sets.get(num) + 1 );
            }else{
                sets.put(num, 1);
            }
        }
        results.add(sets.size());
        for (int i = k; i < input.size(); i++) {
            Integer num = input.get(i);
            if (sets.containsKey(num)){
                sets.put(num, sets.get(num) + 1 );
            }else{
                sets.put(num, 1);
            }
            Integer dropped = input.get(i-k);
            if (sets.get(dropped) == 1){
                sets.remove(dropped);
            }else {
                sets.put(dropped, sets.get(dropped)-1);
            }
            results.add(sets.size());
        }

        return results;
    }

}
