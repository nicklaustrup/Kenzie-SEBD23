package com.kenzie.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxTest {
    private static final Random RANDOM = new Random();

    @Test
    public void max_emptyList_returnsNull() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN
        Double max = linkedList.max();

        // THEN
        assertNull(max);
    }

    @Test
    public void max_singleItemList_returnsNodeValue() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        Double max = linkedList.max();

        // THEN
        assertEquals(value, max);
    }

    @Test
    public void max_multiItemList_returnsLargestValue() {
        // GIVEN
        int numberOfItemsInList = 100;
        Double largestValue = Double.MIN_VALUE;

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < numberOfItemsInList; i++) {
            double value = RANDOM.nextDouble();
            linkedList.addLast(value);

            if (value > largestValue) {
                largestValue = value;
            }
        }

        // WHEN
        Double max = linkedList.max();

        // THEN
        assertEquals(largestValue, max);
    }
}
