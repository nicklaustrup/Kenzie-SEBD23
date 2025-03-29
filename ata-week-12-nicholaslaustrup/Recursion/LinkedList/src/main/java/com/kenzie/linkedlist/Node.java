package com.kenzie.linkedlist;

/**
 * An individual node in a singly-linked LinkedList, containing a Double
 * as the data of each element.
 */
public class Node {
    private Double data;
    private Node next;

    /**
     * Constructs a new node.
     * @param data The data to be contained in the node
     * @param next Reference to the next node in the list
     */
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
