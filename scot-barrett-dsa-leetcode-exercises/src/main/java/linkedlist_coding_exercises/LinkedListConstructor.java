/*
No. 1
LL: Constructor
Create a constructor for a LinkedList class that initializes a new linked list with a single node.

Instructions:

Understand the Node Structure:

Before writing the constructor, ensure you're familiar with the Node class, which is a nested class within the LinkedList class. Each Node should hold an integer value and a reference to the next node (Node next). For the initial node, next will be null.

Constructor Signature:

Your constructor should be public and named LinkedList. It will accept a single integer argument, value, which represents the data to store in the list's first node.

Create the First Node:

Inside the constructor, use the passed value to create a new instance of the Node class. This instance will be the first and only node in your linked list upon creation.

Initialize Head and Tail:

Assign the newly created node to both the head and tail of the linked list. This is because, initially, the list contains only one node, making it both the beginning and end of the list.

Set the List Length:

Initialize the length of the linked list to 1, reflecting that the list currently contains a single node.



Deliverable:

Implement the LinkedList constructor following the above guidelines. This constructor will lay the foundation for further methods you might implement in the LinkedList class, such as adding or removing nodes, searching for values, etc.
 */

package linkedlist_coding_exercises;

public class LinkedListConstructor {
    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedListConstructor(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }
}
