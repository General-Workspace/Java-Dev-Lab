package linkedlist.leetcodeinterview.removeduplicates;


import java.util.HashSet;

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

    public void removeDuplicates() {
        Node current = head;
        while (current != null) {
            Node temp = current;
            while (temp.next != null) {
                if (current.value == temp.next.value) {
                    temp.next = temp.next.next;
                    length--;
                } else {
                    temp = temp.next;
                }
            }
            current = current.next;
        }
    }

    // using hashsets
    public void removeDuplicatesUsingSets() {
        HashSet<Integer> set = new HashSet<>();
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (set.contains(current.value)) {
                previous.next = current.next;
                length--;
            } else {
                set.add(current.value);
                previous = current;
            }
            current = current.next;
        }
    }

    /*
    public void removeDuplicates() {
    // Step 1: Create a Set to store unique node values.
    // We initialize a HashSet, which allows for fast look-up
    // to check for duplicates later on.
    Set<Integer> values = new HashSet<>();

    // Step 2: Initialize a Node variable 'previous' to null.
    // 'previous' will help us unlink a node if it's a duplicate.
    Node previous = null;

    // Step 3: Initialize 'current' to point to the head node.
    // 'current' will be used to iterate through the linked list.
    Node current = head;

    // Step 4: Begin iteration through the linked list.
    // We will keep iterating until 'current' becomes null,
    // indicating we have reached the end of the list.
    while (current != null) {

        // Step 5: Check for duplicates.
        // We check if the current node's value is already in the set.
        if (values.contains(current.value)) {

            // Step 6: Remove the duplicate node.
            // By setting 'previous.next' to 'current.next',
            // we remove the link to 'current', effectively
            // deleting it from the list.
            previous.next = current.next;

            // Step 7: Update the length of the list.
            // Since we removed a node, we decrement the length by 1.
            length -= 1;

        } else {

            // Step 8: Add unique value to set.
            // If the current value is not a duplicate,
            // we add it to the set for future reference.
            values.add(current.value);

            // Step 9: Update 'previous' node.
            // We set 'previous' to the 'current' node, as we
            // move forward in the list.
            previous = current;
        }

        // Step 10: Move to the next node.
        // We update 'current' to point to the next node in the list,
        // continuing our iteration.
        current = current.next;
    }
}



Alternative Solution (does not use a Set):

You can also do this without a Set but this will change the Big O from O(n) to O(n^2):

A Set is a data structure that we will learn more about later in the course.

Here is the solution without using a Set:



    public void removeDuplicates() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                    length -= 1;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }


    public void removeDuplicates() {
    // Step 1: Start at the beginning of the list.
    // Initialize `current` to point to the head node.
    // `current` will be used to navigate through the list,
    // one node at a time, starting from the head node.
    Node current = head;

    // Step 2: Check if we've reached the end of the list.
    // We loop until `current` becomes null, which means
    // we've checked all nodes for duplicates.
    while (current != null) {

        // Step 3: Set up a runner node.
        // Initialize `runner` to the `current` node.
        // We'll use `runner` to scan ahead and find duplicates
        // of the `current` node.
        Node runner = current;

        // Step 4: Loop through the remaining nodes.
        // `runner.next` will be null at the end of the list.
        while (runner.next != null) {

            // Step 5: Compare nodes.
            // Check if `runner.next` (the next node) has the
            // same value as `current`.
            if (runner.next.value == current.value) {

                // Step 6: Remove duplicate.
                // If we find a duplicate, we skip it by
                // setting `runner.next` to `runner.next.next`.
                runner.next = runner.next.next;

                // Step 7: Update list length.
                // We removed a node, so decrease the list length
                // by 1 to keep it accurate.
                length -= 1;

            } else {

                // Step 8: Move to the next node.
                // If the next node is not a duplicate,
                // move `runner` to the next node to continue.
                runner = runner.next;
            }
        }

        // Step 9: Move to the next unique node.
        // After checking all nodes for duplicates of the
        // current value, move `current` to the next node.
        current = current.next;
    }
}
     */

}
