package com.kenzie.linkedlist;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EqualsTest {

    @Test
    public void equals_otherIsNull_returnsFalse() {
        // GIVEN
        LinkedList list = new LinkedList();
        LinkedList other = new LinkedList();

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        // GIVEN
        LinkedList list = new LinkedList();
        LinkedList other = list;

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        // GIVEN
        LinkedList list = new LinkedList();
        String other = "LinkedList";

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_emptyLists_returnsTrue() {
        // GIVEN
        LinkedList list = new LinkedList();
        LinkedList other = new LinkedList();

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_sameValues_returnTrue() {
        // GIVEN
        LinkedList list = new LinkedList();
        LinkedList other = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7);
        list.addAll(dataList);
        other.addAll(dataList);

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertTrue(isEqual);
    }

    @Test
    public void equals_differentLengthLists_returnFalse() {
        // GIVEN
        LinkedList list = new LinkedList();
        list.addAll(ImmutableList.of(1.4, 2.2, 800.3));
        LinkedList other = new LinkedList();
        other.addAll(ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7));

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertFalse(isEqual);
    }

    @Test
    public void equals_listsWithDifferentValues_returnsFalse() {
        // GIVEN
        LinkedList list = new LinkedList();
        list.addAll(ImmutableList.of(1.4, 2.2, 805.7, 999.0, -45.7));
        LinkedList other = new LinkedList();
        other.addAll(ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7));

        // WHEN
        boolean isEqual = list.equals(other);

        // THEN
        assertFalse(isEqual);
    }
}
