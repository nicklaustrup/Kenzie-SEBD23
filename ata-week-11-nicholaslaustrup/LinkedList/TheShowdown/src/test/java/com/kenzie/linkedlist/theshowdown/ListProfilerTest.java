package com.kenzie.linkedlist.theshowdown;

import org.apache.commons.lang.NotImplementedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ListProfilerTest {
    @Test
    public void listProfiler_runProfiler_viewResults() {
        ListProfiler listProfiler = new ListProfiler();

        listProfiler.withListToProfile(new ProfileableArrayList());
        listProfiler.withListToProfile(new ProfileableLinkedList());
        try{
            listProfiler.profileLists();
        } catch(NotImplementedException e) {
            fail(e.getMessage());
        }
    }
}
