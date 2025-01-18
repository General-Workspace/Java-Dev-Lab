/*
No. 4
DLL: Prepend
Implement the prepend method that adds a new node to the beginning of the doubly linked list.

Return type: void



The method should perform the following tasks:

Accept an integer value as an argument, which will be the value of the new node.

Create a new Node object called newNode with the given value.

If the length of the doubly linked list is 0:

Set both the head and tail pointers of the list to the newNode.

If the length of the doubly linked list is greater than 0:

Set the next attribute of the newNode to the current head node.

Set the prev attribute of the current head node to the newNode.

Update the head pointer of the list to point to the newNode.

Increment the length attribute of the list by 1.
 */
package doubly_linked_list_coding_exercise;

public class Prepend {
}
