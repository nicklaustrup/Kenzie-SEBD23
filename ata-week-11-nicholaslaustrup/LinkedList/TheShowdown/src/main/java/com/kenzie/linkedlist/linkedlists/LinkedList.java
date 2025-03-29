package com.kenzie.linkedlist.linkedlists;


import com.kenzie.linkedlist.theshowdown.ProfileableList;

import java.util.Collection;

/**
 * PARTICIPANTS: This is a partial implementation of the AtaLinkedList.
 *
 * Feel free to continue building off of this or use it as a guide for your
 * own implementation.
 */
public class LinkedList implements ProfileableList {
    private int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public String getListImplementationType() {
        return "AtaLinkedList";
    }

    @Override
    public void addFirst(final Double data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double getFirst() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addLast(final Double data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addAll(final Collection<Double> collection) {
        return;
    }

    @Override
    public Double getMiddle() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }


    public int size() {
        return this.size;
    }

}
