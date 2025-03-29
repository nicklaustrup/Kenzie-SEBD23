package com.kenzie.countdown;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecursiveMagicIndexTest {

    @Test
    void magicIndex_noMagicIndex_returnsNegative1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(-1, index, "Did not expect to find a magic index in list: " + list);
    }

    @Test
    void magicIndex_emptyList_returnsNegative1() {
        List<Integer> list = Collections.emptyList();

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(-1, index, "Did not expect to find a magic index in list: " + list);
    }

    @Test
    void magicIndex_magicIndexFirstHalf_returnsCorrectIndex() {
        List<Integer> list = Arrays.asList(-1, 1, 3, 4, 5);

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(1, index, "Expect to find a magic index, 1, in list: " + list);
    }

    @Test
    void magicIndex_magicIndexSecondHalf_returnsCorrectIndex() {
        List<Integer> list = Arrays.asList(-1, -2, -3, 1, 4, 6);

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(4, index, "Expect to find a magic index, 1, in list: " + list);

    }

    @Test
    void magicIndex_magicIndexFirst_returnsZero() {
        List<Integer> list = Arrays.asList(0, 2, 3, 4, 5);

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(0, index, "Expect to find a magic index, 0, in list: " + list);

    }

    @Test
    void magicIndex_magicIndexLast_returnsLastIndex() {
        List<Integer> list = Arrays.asList(-1, -2, -3, -4, 4);

        int index = RecursiveMagicIndex.magicIndex(list);

        assertEquals(4, index, "Expect to find a magic index, 4, in list: " + list);
    }
}
