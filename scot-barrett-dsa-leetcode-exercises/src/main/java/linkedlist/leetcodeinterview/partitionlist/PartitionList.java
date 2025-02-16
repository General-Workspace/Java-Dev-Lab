package linkedlist.leetcodeinterview.partitionlist;

public class PartitionList {
    private Node head;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public PartitionList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.printf("%d ", temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
    }

    public void partitionList(int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = null;
            if (current.value < x) {
                if (beforeStart == null) {
                    beforeStart = current;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = current;
                    beforeEnd = current;
                }
            } else {
                if (afterStart == null) {
                    afterStart = current;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = current;
                    afterEnd = current;
                }
            }
            current = next;
        }

        if (beforeStart == null) {
            head = afterStart;
        } else {
            beforeEnd.next = afterStart;
            head = beforeStart;
        }
    }
}