/*
No. 2
DLL: Append
Implement the append method that appends a new node to the end of the doubly linked list.

Return type: void



The method should perform the following tasks:

Accept an integer value as an argument, which will be the value of the new node.

Create a new Node object called newNode with the given value.

If the length of the doubly linked list is 0:

Set both the head and tail pointers of the list to the newNode.

If the length of the doubly linked list is greater than 0:

Set the next attribute of the current tail node to the newNode.

Set the prev attribute of the newNode to the current tail node.

Update the tail pointer of the list to point to the newNode.

Increment the length attribute of the list by 1.
 */
package doublylinkedlist.codingexercises;

public class Append {
}
