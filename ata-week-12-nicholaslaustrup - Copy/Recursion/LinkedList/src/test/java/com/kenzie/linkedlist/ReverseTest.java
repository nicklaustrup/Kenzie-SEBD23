package com.kenzie.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTest {
    private static final Random RANDOM = new Random();

    @Test
    public void reverse_emptyList_returnsEmptyList() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN
        LinkedList reversed = linkedList.reverse();

        // THEN
        assertEquals(new LinkedList(), reversed);
    }

    @Test
    public void reverse_singleItemList_returnsSingleItemList() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        LinkedList reversed = linkedList.reverse();

        // THEN
        assertEquals(linkedList, reversed);
    }

    @Test
    public void reverse_multiItemList_returnsValuesInReverseOrder() {
        // GIVEN
        int numberOfItemsInList = 30;
        LinkedList expected = new LinkedList();

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < numberOfItemsInList; i++) {
            double value = RANDOM.nextDouble();
            linkedList.addLast(value);
            expected.addFirst(value);
        }

        // WHEN
        LinkedList reversed = linkedList.reverse();

        // THEN
        assertEquals(expected, reversed);
    }
}
