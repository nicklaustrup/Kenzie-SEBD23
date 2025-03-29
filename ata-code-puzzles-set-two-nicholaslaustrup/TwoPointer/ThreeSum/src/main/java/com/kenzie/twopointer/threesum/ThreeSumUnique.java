package com.kenzie.twopointer.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Contains a problem that can be solved using the Two-Pointer Technique.
 */
public class ThreeSumUnique {
    /**
     * Given an unsorted integer array nums, where each element is unique, return all the triplets
     * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Example:
     *  nums = [-1,0,1,3,2,-4]
     *  result = [[-4,1,3],[-1,0,1]]
     *
     * @param nums an unsorted integer array where each element is unique.
     * @return all triplets that sum to 0
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        //credit: Hamza Malik
        List <List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);


        for (int i = 0; i< nums.length - 2; i++) {
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    if (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    if (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
    }

