package linkedlist_coding_exercises;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (this.length == 0) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.length++;
    }

    public Node removeLast() {
        if (this.length == 0) return null;
        Node temp = this.head;
        Node pre = this.head;

        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }

        this.tail = pre;
        this.tail.next = null;
        length--;

        if (this.length == 0) {
            this.head = null;
            this.tail = null;
        }

        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        if (length == 1) {
            head = null;
            tail = null;
            length--;
            return null;
        }
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;

        for (int i = 0; i < index; ++i) {
            temp = temp.next;
        }

        return temp;
    }

    public boolean set(int index, int value) {
       Node temp = get(index);
       if (temp != null) {
           temp.value = value;
           return true;
       }
       return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev = get(index - 1);
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;

    }

    public void reverse() {
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        Node after = temp.next;
        Node before = null;

        for (int i = 0; i < length; ++i) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

//    public Node removeFirst() {
//        if (length == 0) return null;
//        Node temp = head;
//        head = head.next;
//        temp.next = null;
//        length--;
//
//        if (length == 0) {
//            tail = null;
//        }
//        return temp;
//    }

    public void printList() {
        Node temp = head;
        while(temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead() {
        System.out.println("Head: " + this.head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + this.tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + this.length);
    }
}
