package linkedlist.leetcodeinterview.reversebetween;

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
        while (temp != null) {
            //System.out.println(temp.value);
            System.out.printf("%d ", temp.value);;
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

    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null) return;

        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;

        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        Node currentNode = previousNode.next;

        for (int i = 0; i < endIndex - startIndex; i++) {
            Node nodeToMove = currentNode.next;
            currentNode.next = nodeToMove.next;
            nodeToMove.next = previousNode.next;
            previousNode.next = nodeToMove;
        }

        head = dummyNode.next;
    }

    /*
    public void reverseBetween(int m, int n) {
        if (length == 0 || length == 1) {
            return;
        }

        if (m == n) {
            return;
        }

        if (m < 0 || n < 0 || m >= length || n >= length) {
            return;
        }

        if (m > n) {
            return;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }

        Node start = prev.next;
        Node then = start.next;

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }

        head = dummy.next;
    }
     */

    /*
    public void reverseBetween(int startIndex, int endIndex) {
    // Check: If linked list is empty, nothing to reverse.
    // Exit the method.
    if (head == null) return;

    // Create a 'dummyNode' that precedes the head.
    // Simplifies handling edge cases.
    Node dummyNode = new Node(0);
    dummyNode.next = head;

    // 'previousNode' is used to navigate to the node
    // right before our sublist begins.
    Node previousNode = dummyNode;

    // Move 'previousNode' to node just before sublist.
    for (int i = 0; i < startIndex; i++) {
        previousNode = previousNode.next;
    }

    // 'currentNode' marks the first node of sublist.
    Node currentNode = previousNode.next;

    // Loop reverses the section from startIndex to endIndex.
    for (int i = 0; i < endIndex - startIndex; i++) {

        // 'nodeToMove' is the node we'll move to sublist start.
        Node nodeToMove = currentNode.next;

        // Detach 'nodeToMove' from its current position.
        currentNode.next = nodeToMove.next;

        // Attach 'nodeToMove' at the beginning of the sublist.
        nodeToMove.next = previousNode.next;

        // Move 'nodeToMove' to the start of our sublist.
        previousNode.next = nodeToMove;
    }

    // Adjust 'head' if the first node was part of sublist.
    head = dummyNode.next;
}
     */
}