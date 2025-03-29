package com.kenzie.linkedlist.theshowdown;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A ProfileableList implementation using a LinkedList.
 *
 * PARTICIPANTS: Implement all methods that throw UnsupportedOperationException in any order.
 */
public class ProfileableLinkedList implements ProfileableList {
    private LinkedList<Double> list = new LinkedList<>();

    @Override
    public String getListImplementationType() {
        return "LinkedList";
    }

    @Override
    public void addFirst(Double data) {
        this.list.addFirst(data);
    }

    @Override
    public void addLast(Double data) {
        this.list.addLast(data);
    }

    @Override
    public Double getFirst() {
        return this.list.get(0);
    }

    @Override
    public Double getMiddle() {
        return this.list.get(list.size()/2);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void addAll(Collection<Double> collection) {
        this.list.addAll(collection);
    }
}
