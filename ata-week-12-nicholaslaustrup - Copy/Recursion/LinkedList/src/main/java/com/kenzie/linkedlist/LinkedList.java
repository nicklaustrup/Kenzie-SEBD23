package com.kenzie.linkedlist;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A bare-bones singly-linked list implementation.
 */
public class LinkedList {
    private Node head;
    private Node tail;

    /**
     * Constructs a new, empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Add an entry as the new first element in the list.
     *
     * @param data The new value to add at the head of the list
     */
    public void addFirst(final Double data) {
        Node newHead = new Node(data, head);
        this.head = newHead;
        if (this.tail == null) {
            this.tail = newHead;
        }
    }

    /**
     * Add an entry as the new last element in the list.
     *
     * @param data The new value to add at the tail of the list
     */
    public void addLast(final Double data) {
        Node node = new Node(data, null);

        if (this.head == null) {
            this.head = node;
            this.tail = head;
        } else {
            this.tail.setNext(node);
            this.tail = this.tail.getNext();
        }
    }

    /**
     * Returns the value of the first element in the list.
     * Throws IndexOutOfBoundsException if the list is empty.
     *
     * @return the first value, or null if list. isempty
     */
    public Double getFirst() {

        return get(0);
    }

    /**
     * Returns the value of the Nth element in the list (zero-indexed).
     * Throws IndexOutOfBoundsException if n is size of list or
     * greater, or if n is negative.
     * <p>
     * We normally would not want to call this method often on a linked
     * list because it is O(n), but this might be helpful for your
     * reverse() unit tests.
     *
     * @param n the index of the element to return data from
     * @return The value at that element, if the element exists
     */
    @VisibleForTesting
    public Double get(int n) {
        Node node = this.head;
        for (int i = 0; i < n; i++) {
            if (node == null) {
                break;
            }
            node = node.getNext();
        }
        if (node == null) {
            throw new IndexOutOfBoundsException("n was too big for this list: " + n);
        }

        return node.getData();
    }

    /**
     * Add the collection's data values to the end of the list.
     *
     * @param collection The collection of Double to add to the list
     */
    public void addAll(final Collection<Double> collection) {
        for (Double data : collection) {
            addLast(data);
        }
    }

    /**
     * Computes and returns the sum of all Double elements in the list.
     *
     * @return The sum of all elements in the list
     */
    public Double sum() {
        return sumRecursive(head);
    }

    private Double sumRecursive(Node node) {
        //base case
        if (node == null) {
            return 0.0;
        } else {
            return node.getData() + sumRecursive(node.getNext());
        }
    }

    /**
     * Creates a new LinkedList that has all of the values of this
     * LinkedList in reverse order.
     *
     * @return a new reverse order list
     */
    public LinkedList reverse() {
        LinkedList list = new LinkedList();
        return reverseRecursive(list, head);
    }

    private LinkedList reverseRecursive(LinkedList list, Node nextNode) {
        //base case
        if (nextNode == null) {
            return list;
        }
        list.addFirst(nextNode.getData());
        return reverseRecursive(list, nextNode.getNext());
    }

    /**
     * Calculates the size of the linked list. The number of nodes
     * with data.
     *
     * @return size of the linked list
     */
    public int size() {
        int counter = 0;
        Node node = this.head;
        while (node != null) {
            counter++;
            node = node.getNext();
        }
        return counter;
    }


    /**
     * Determines if the list contains a node with a double equal
     * to the number specified.
     *
     * @param number to check for in the list
     * @return true, if the list contains the number.
     */
    public boolean contains(Double number) {
        Node node = this.head;
        if (node == null) {
            return false;
        }
        while (node != null) {
            if (node.getData().equals(number)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Computes and returns the max of all Double elements in the list.
     *
     * @return The max double in the list.
     */
    public Double max() {
        double maxValue = 0.0;
        Node node = this.head;
        if (node == null) {
            return null;
        }
        while (node != null) {
            if (node.getData() > maxValue) {
                maxValue = node.getData();
            }
            node = node.getNext();
        }
        return maxValue;
    }

    // EXTENSION


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkedList other = (LinkedList) o;
        Node thisNode = this.head;
        Node otherNode = other.head;
        while (thisNode != null && otherNode != null) {
            if (!thisNode.getData().equals(otherNode.getData())) {
                return false;
            }
            thisNode = thisNode.getNext();
            otherNode = otherNode.getNext();
        }
        return thisNode == null && otherNode == null;
    }


    @Override
    public int hashCode() {
        Node node = this.head;
        if (node == null) {
            return Objects.hash();
        }
        int finalHashValue = Objects.hashCode(node.getData());

        while (node != null) {
            node = node.getNext();

            if (node != null) {
                finalHashValue = Objects.hash(finalHashValue, node.getData());
            }
        }
        return finalHashValue;
    }



        @Override
        public String toString() {
            Node node = head;
            StringBuilder stringBuilder = new StringBuilder("[");

            while (node != null) {
                stringBuilder.append(node.getData())
                        .append(", ");
                node = node.getNext();
            }
            stringBuilder.append("]");

            return stringBuilder.toString();
        }
    }

