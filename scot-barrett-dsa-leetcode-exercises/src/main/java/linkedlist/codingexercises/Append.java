/*
No. 2
LL: Append
Implement the append method that appends a new node to the end of the linked list.

Return type: void



The method should perform the following tasks:

Accept an integer value as an argument, which will be the value of the new node.

Create a new Node object called newNode with the given value.

If the length of the linked list is 0, set both the head and tail pointers of the list to the newNode.

If the length of the linked list is greater than 0, perform the following tasks:

Set the next attribute of the current tail node to the newNode.

Update the tail pointer of the list to point to the newNode.

Increment the length attribute of the list by 1.
 */

package linkedlist.codingexercises;

public class Append extends LinkedListConstructor {
    public Append(int value) {
        super(value);
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (getLength() == 0) {
            setHead(newNode);
            setTail(newNode);
        } else {
            getTail().next = newNode;
            setTail(newNode);
        }
        setLength(1);
    }
}
