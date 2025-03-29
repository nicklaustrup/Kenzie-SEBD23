package com.kenzie.linkedlist;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetTest {

    @Test
    public void get_singleElementList_returnsData() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        Double data = 0.2;
        linkedList.addLast(data);

        // WHEN
        Double result = linkedList.get(0);

        // THEN
        assertEquals(data, result);
    }

    @Test
    public void get_firstElement_returnsData() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.0, 2.0, 3.0);
        Double data = dataList.get(0);
        linkedList.addAll(dataList);

        // WHEN
        Double result = linkedList.get(0);

        // THEN
        assertEquals(data, result);
    }

    @Test
    public void get_middleElement_returnsData() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.0, 2.0, 3.0, 4.0, 5.0);
        int indexToCheck = 3;
        Double data = dataList.get(indexToCheck);
        linkedList.addAll(dataList);

        // WHEN
        Double result = linkedList.get(indexToCheck);

        // THEN
        assertEquals(data, result);
    }

    @Test
    public void get_LastElement_returnsData() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.0, 2.0, 3.0, 4.0, 5.0, 44.1, 98.6, 451.0);
        int indexToCheck = dataList.size() - 1;
        Double data = dataList.get(indexToCheck);
        linkedList.addAll(dataList);

        // WHEN
        Double result = linkedList.get(indexToCheck);

        // THEN
        assertEquals(data, result);
    }


    @Test
    public void get_negativeIndex_throwsException() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN + THEN
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-10));
    }

    @Test
    public void get_emptyList_throwsException() {
        // GIVEN
        LinkedList linkedList = new LinkedList();

        // WHEN + THEN
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }

    @Test
    public void get_beyondLastElement_throwsException() {
        // GIVEN
        LinkedList linkedList = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7);
        linkedList.addAll(dataList);

        // WHEN + THEN
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(dataList.size()));
    }
}
