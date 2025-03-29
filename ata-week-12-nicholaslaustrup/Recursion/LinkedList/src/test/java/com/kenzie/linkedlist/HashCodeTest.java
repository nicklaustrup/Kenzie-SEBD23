package com.kenzie.linkedlist;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HashCodeTest {

    @Test
    public void hashCode_emptyLists_equalHashCode() {
        // GIVEN
        LinkedList expectedList = new LinkedList();
        LinkedList actualList = new LinkedList();
        int expectedHashCode = expectedList.hashCode();

        // WHEN
        int actualHashCode = actualList.hashCode();

        // THEN
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void hashCode_equalObjects_equalHashCode() {
        // GIVEN
        LinkedList expectedList = new LinkedList();
        LinkedList actualList = new LinkedList();
        List<Double> dataList = ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7);
        expectedList.addAll(dataList);
        actualList.addAll(dataList);
        int expectedHashCode = expectedList.hashCode();

        // WHEN
        int actualHashCode = actualList.hashCode();

        // THEN
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    public void hashCode_differentLengthLists_unequalHashCode() {
        // GIVEN
        LinkedList otherList = new LinkedList();
        otherList.addAll(ImmutableList.of(1.4, 2.2, 800.3));
        LinkedList actualList = new LinkedList();
        actualList.addAll(ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7));
        int unexpectedHashCode = otherList.hashCode();

        // WHEN
        int actualHashCode = actualList.hashCode();

        // THEN
        assertNotEquals(unexpectedHashCode, actualHashCode);
    }

    @Test
    public void hashCode_listsWithDifferentValues_unequalHashCode() {
        // GIVEN
        LinkedList otherList = new LinkedList();
        otherList.addAll(ImmutableList.of(1.4, 2.2, 805.7, 999.0, -45.7));
        LinkedList actualList = new LinkedList();
        actualList.addAll(ImmutableList.of(1.4, 2.2, 800.3, 999.0, -45.7));
        int unexpectedHashCode = otherList.hashCode();

        // WHEN
        int actualHashCode = actualList.hashCode();

        // THEN
        assertNotEquals(unexpectedHashCode, actualHashCode);
    }
}
