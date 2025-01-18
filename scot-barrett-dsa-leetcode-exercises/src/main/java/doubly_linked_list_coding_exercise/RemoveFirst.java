/*
No. 5
DLL: Remove First
Implement the removeFirst method that removes the first node from the doubly linked list and returns it.

Return type: Node (the node being removed)



The method should perform the following tasks:

If the length of the doubly linked list is 0, return null.

Store the current head node in a temporary variable called temp.

If the length of the doubly linked list is 1:

Set both the head and tail pointers of the list to null.

If the length of the doubly linked list is greater than 1:

Update the head pointer of the list to point to the next node in the list.

Set the prev attribute of the new head node to null.

Set the next attribute of the temp node to null.

Decrement the length attribute of the list by 1.

Return the temp node.
 */
package doubly_linked_list_coding_exercise;

public class RemoveFirst {
}
