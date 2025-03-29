package com.kenzie.linkedlist.theshowdown;

import com.kenzie.linkedlist.linkedlists.LinkedList;

public class ListProfilerRunner {
    public static void main(String[] args) {
        ListProfiler listProfiler = new ListProfiler();

        listProfiler.withListToProfile(new ProfileableArrayList());
        listProfiler.withListToProfile(new ProfileableLinkedList());
        listProfiler.profileLists();
    }
}
