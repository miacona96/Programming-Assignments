package com.company;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class IDLList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList() {
        size = 0;
    }

    private class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E elem) {
        }

        public Node(E elem, Node next, Node prev) {
            this.data = elem; //element
            this.next = next; //next node
            this.prev = prev; // previous node
        }
    }

    //Adds element at position index
    public boolean add(int index, E elem) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
        // Find corresponding node
        Node prev = null;
        Node p = head;
        for (int i = 1; i <= index; i++) {
            prev = p;
            p = p.next;
        }
        // Create a new Node with index
        Node n = new Node(elem);
        // Special case for the first node
        if (index == 0) {
            n.next = head;
            head = n;
        } else {
            prev.next = n;
            n.next = p;
        }
        // If node was added at the end,update tail
        if (index == size) {
            tail = n;
        }
        //update size
        size++;
        return true;
    }

    //adds element at the head
    public boolean add(E elem) {
        Node<E> temp = new Node<E>(elem, head, null);
        if (head != null) {
            head.prev = temp;
        }
        head = temp;
        if (tail == null) {
            tail = temp;
        }
        size++;
        return true;
    }

    //adds element at the tail
    public boolean append(E elem) {
        Node<E> temp = new Node<E>(elem, null, tail);
        if (tail != null) {
            tail.next = temp;
        }
        tail = temp;
        if (head == null) {
            head = temp;
        }
        size++;
        indices.add(temp);
        return true;
    }

    //gets object at specified index
    public E get(int index) {
        Node<E> x = indices.get(index);
        return x.data;
    }

    //returns object at the head
    public E get() {
        return head.data;
    }

    //gets object at tail
    public E getLast() {
        return tail.data;
    }

    //returns returns size of the list
    public int size() {
        return size;
    }

    //Method to remove the first element of the double linked list
    public E remove() {
        if (size == 0) throw new NoSuchElementException();
        Node<E> temp = head;
        head = head.next;
        if (size == 1) head.prev = null;
        size--;
        indices.remove(0);
        return temp.data;
    }

    //removes last element of the double linked list
    public E removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node<E> temp = tail;
        tail = tail.prev;
        if (size == 1)
            tail.next = null;
        size--;
        indices.remove(size - 1);
        return temp.data;
    }

    //removes first occurrence of the element in the list, returns true,
    //false if element not present
    public boolean remove(E elem) {
        int index = indices.indexOf(elem);
        if (indices.contains(elem) == false) {
            return false;
        }
        return true;
    }

    //removes element from list at specified index
    public E remove(int index) {
        Node<E> x = indices.remove(index);

        return x.data;
    }

    //string representation of the Double linked list
    public String toString() {
        String s = "[";
        Node<E> x = head.next;
        while (x != tail) {
            s += x + " ";
            x = x.next;
        }
        s += "]";
        return s;
    }
}

