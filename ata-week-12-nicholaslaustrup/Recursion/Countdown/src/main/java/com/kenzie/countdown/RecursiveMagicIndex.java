package com.kenzie.countdown;

import java.util.List;

public class RecursiveMagicIndex {

    /**
     * This recursive method accepts a sorted list of distinct values
     * and returns a magic index if one exists. A magic index is a
     * an index in a list that has the same value as the index.
     * @param list - this is the sorted list to search through for a magic index
     * @return - this is the magic index, (-1) will be returned if one cannot be found
     */
    public static int magicIndex(List<Integer> list) {
        //PARTICIPANTS: replace this placeholder line with your implementation
        if (list.size() == 0){
            return -1;
        }
        int start = 0;
        int end = list.size() -1;
        return magicIndex(list, start, end);
    }
    public static int magicIndex(List<Integer> list, int start, int end){
        if (list.size() == 0){
            return -1;
        }

        // mid
        int mid = start + (end - start)/2;


        //base case
        if (mid == list.get(mid)){
            return mid;
        }
        else if (mid > list.get(mid)){
                start = mid + 1;
                return magicIndex(list, start, end);
        } else if (mid < list.get(mid) && mid > 0){
                end = mid - 1;
               return magicIndex(list, start, end);
        } else {
            return -1;
        }
    }
}
