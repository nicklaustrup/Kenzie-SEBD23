package com.kenzie.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainsTest {
    private static final Random RANDOM = new Random();

    @Test
    public void contains_emptyList_returnsFalse() {
        // GIVEN
        Double number = 4.3;
        LinkedList linkedList = new LinkedList();

        // WHEN
        boolean contains = linkedList.contains(number);

        // THEN
        assertFalse(contains);
    }

    @Test
    public void contains_singleItemListInList_returnsTrue() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        boolean contains = linkedList.contains(value);

        // THEN
        assertTrue(contains);
    }

    @Test
    public void contains_singleItemListNotInList_returnsFalse() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        boolean contains = linkedList.contains(value + 1.0);

        // THEN
        assertFalse(contains);
    }

    @Test
    public void contains_multiItemListInList_returnsTrue() {
        // GIVEN
        int numberOfItemsInList = 100;
        double lastValue = 0.0;

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < numberOfItemsInList; i++) {
            double value = RANDOM.nextDouble();
            linkedList.addLast(value);
            lastValue = value;
        }

        // WHEN
        boolean contains = linkedList.contains(lastValue);

        // THEN
        assertTrue(contains);
    }

    @Test
    public void contains_multiItemListInList_returnsFalse() {
        // GIVEN
        int numberOfItemsInList = 100;
        double lastValue = 0.0;

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < numberOfItemsInList; i++) {
            double value = RANDOM.nextDouble();
            linkedList.addLast(value);
            lastValue = value;
        }

        // WHEN
        boolean contains = linkedList.contains(lastValue + 1.0);

        // THEN
        assertFalse(contains);
    }
}
