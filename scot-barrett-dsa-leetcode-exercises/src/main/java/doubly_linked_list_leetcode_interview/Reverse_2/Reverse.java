/*
No. 2
DLL: Reverse ( ** Interview Question)
Implement a method called reverse() that reverses the order of the nodes in the list.

This method should reverse the order of the nodes in the list by manipulating the pointers of each node, not by swapping the values within the nodes.


Method Signature:

public void reverse()


Output:

No explicit output is returned. However, the method should modify the doubly linked list such that the order of the nodes is reversed.


Constraints:

The doubly linked list may be empty or have one or more nodes.


Example:

Consider the following doubly linked list:

Head: 1
Tail: 5
Length: 5

Doubly Linked List:
1 <-> 2 <-> 3 <-> 4 <-> 5


After calling reverse(), the list should be:

Head: 5
Tail: 1
Length: 5

Doubly Linked List:
5 <-> 4 <-> 3 <-> 2 <-> 1
 */
package doubly_linked_list_leetcode_interview.Reverse_2;

public class Reverse {
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

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

	// WRITE REVERSE METHOD HERE //
	//                           //
	//                           //
	//                           //
	//                           //
	///////////////////////////////

}


 */
