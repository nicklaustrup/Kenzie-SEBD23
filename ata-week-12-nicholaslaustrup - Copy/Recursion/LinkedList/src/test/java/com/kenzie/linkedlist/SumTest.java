package com.kenzie.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.kenzie.linkedlist.DoubleUtils.assertDoubleEquals;

public class SumTest {
    private static final Random RANDOM = new Random();

    @Test
    public void sum_emptyList_returns0() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN
        Double sum = linkedList.sum();

        // THEN
        assertDoubleEquals(0.0, sum);
    }

    @Test
    public void sum_singleItemList_returns1() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        double value = RANDOM.nextDouble();
        linkedList.addFirst(value);

        // WHEN
        Double sum = linkedList.sum();

        // THEN
        assertDoubleEquals(value, sum);
    }

    @Test
    public void sum_multiItemList_returnsSumOfDataInList() {
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
        Double sum = linkedList.sum();

        // THEN
        assertDoubleEquals(expectedSum, sum);
    }
}
