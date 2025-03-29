package com.kenzie.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeTest {
    private static final Random RANDOM = new Random();

    @Test
    public void size_emptyList_returns0() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN
        int size = linkedList.size();

        // THEN
        assertEquals(0, size);
    }

    @Test
    public void size_singleItemList_returns1() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        int size = linkedList.size();

        // THEN
        assertEquals(1, size);
    }

    @Test
    public void size_multiItemList_returnsNumberOfItems() {
        // GIVEN
        int numberOfItemsInList = 100;
        Double expectedSum = 0.0;

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < numberOfItemsInList; i++) {
            double value = RANDOM.nextDouble();
            expectedSum += value;
            linkedList.addLast(value);
        }

        // WHEN
        int size = linkedList.size();

        // THEN
        assertEquals(numberOfItemsInList, size);
    }
}
