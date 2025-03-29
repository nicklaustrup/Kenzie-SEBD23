package com.kenzie.linkedlist.linkedlists;

/**
 * PARTICIPANTS: Feel free to use Node class directly or as a guide
 * for your own implementation.
 */
public class Node {
    private Double data;
    private Node next;

    public Node(final Double data, final Node next) {
        this.data = data;
        this.next = next;
    }

    public Double getData() {
        return data;
    }

    public void setData(final Double data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(final Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + '}';
    }
}
