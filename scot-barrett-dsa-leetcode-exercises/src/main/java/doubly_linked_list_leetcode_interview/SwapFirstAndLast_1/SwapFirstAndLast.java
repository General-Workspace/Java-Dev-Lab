/*
No. 1
DLL: Swap First and Last ( ** Interview Question)
Swap First and Last Node Values in a Doubly Linked List.

Given a doubly linked list, write a method called swapFirstLast() that swaps the values of the first and last nodes in the list.

If the length of the list is less than 2, the method should not perform any operation.


Function Signature:

public void swapFirstLast()


Input:

The method is part of the DoublyLinkedList class, which has a head and a tail pointer, as well as a length attribute.



Output:

No explicit output is returned. However, the method should modify the doubly linked list such that the values of the first and last nodes are swapped.



Constraints:

The doubly linked list may be empty, have only one node, or have two or more nodes.



Example:

Consider the following doubly linked list:

Head: 1
Tail: 5
Length: 5

Doubly Linked List:
1 <-> 2 <-> 3 <-> 4 <-> 5


After calling swapFirstLast(), the list should be:

Head: 5
Tail: 1
Length: 5

Doubly Linked List:
5 <-> 2 <-> 3 <-> 4 <-> 1


Note that the pointers to the nodes themselves are not swapped - only their values are exchanged.
 */
package doubly_linked_list_leetcode_interview.SwapFirstAndLast_1;

public class SwapFirstAndLast {
}

/*
public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append (int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

	// WRITE SWAPFIRSTLAST METHOD HERE //
	//                                 //
	//                                 //
	//                                 //
	//                                 //
	/////////////////////////////////////

}


 */
