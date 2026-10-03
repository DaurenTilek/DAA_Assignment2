package structures;

import metrics.Metrics;

public class MyLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private final Metrics metrics;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        metrics = new Metrics();
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            tail = newNode;

            metrics.move();
            metrics.move();
        } else {
            tail.next = newNode;
            metrics.move();

            tail = newNode;
            metrics.move();
        }

        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(value);
            return;
        }

        Node newNode = new Node(value);

        if (index == 0) {
            newNode.next = head;
            metrics.move();

            head = newNode;
            metrics.move();

            if (size == 0) {
                tail = newNode;
                metrics.move();
            }
        } else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                metrics.step();
            }

            newNode.next = current.next;
            metrics.move();

            current.next = newNode;
            metrics.move();
        }

        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removedValue;

        if (index == 0) {
            removedValue = head.value;
            metrics.step();

            head = head.next;
            metrics.move();

            size--;

            if (size == 0) {
                tail = null;
                metrics.move();
            }

            return removedValue;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            metrics.step();
        }

        Node removed = current.next;

        removedValue = removed.value;
        metrics.step();

        current.next = removed.next;
        metrics.move();

        if (removed == tail) {
            tail = current;
            metrics.move();
        }

        size--;

        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            metrics.step();
        }

        metrics.step();

        return current.value;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            metrics.step();
            metrics.comparison();

            if (current.value == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}