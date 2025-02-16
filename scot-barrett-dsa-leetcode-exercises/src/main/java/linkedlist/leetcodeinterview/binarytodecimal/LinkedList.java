package linkedlist.leetcodeinterview.binarytodecimal;

public class LinkedList {

    private Node head;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
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
        if (temp == null) {
            System.out.println("empty");
        } else {
            while (temp != null) {
                System.out.print(temp.value);
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public void makeEmpty() {
        head = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
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

    public int binaryToDecimal() {
        Node current = head;
        int decimal = 0;
        int power = length - 1;
        while (current != null) {
            decimal += current.value * Math.pow(2, power);
            current = current.next;
            power--;
        }
        return decimal;
    }

    /*
    public int binaryToDecimal() {
        int num = 0;
        int sum = 0;
        Node current = head;

        while (current != null) {
            sum += num * 2 + current.value;
            num++;
            current = current.next;
        }

        return sum;
    }
     */

}
