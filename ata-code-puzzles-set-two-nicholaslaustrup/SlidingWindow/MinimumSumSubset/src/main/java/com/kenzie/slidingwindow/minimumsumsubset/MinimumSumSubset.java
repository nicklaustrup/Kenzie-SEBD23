package com.kenzie.slidingwindow.minimumsumsubset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains a problem that can be solved using the Sliding Window Technique.
 */
public class MinimumSumSubset {

    /**
     * Given a list of integers, find the contiguous sublist of given length k that has the minimum sum value.
     * Return the minimum sum value.
     *
     * Example:
     *   input = [1, 3, 4, 2, 8, 5, 3]
     *   k = 3
     *
     *   result = 8
     *
     * @param input - the list of integers from which to identify the minimum sum value, size >= k
     * @param k - the size of sublists that should be considered when calculating the minimum sum value, k >= 1
     * @return the lowest sum value of all sums calculated for each contiguous sublist
     */
    public static int findMinimumSum(List<Integer> input, int k) {
        // TODO: Implement an algorithm that utilizes the sliding window technique
        //credit: Zach Caraballo-Balboa
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < input.size()- k +1 ; i++){
            int currentSum = 0;
            for (int j = 0; j< k; j++){
                currentSum = currentSum+ input.get(i + j);
            }
            if (currentSum < minSum){
                minSum = currentSum;
            }
        }
        return minSum;
    }

}
