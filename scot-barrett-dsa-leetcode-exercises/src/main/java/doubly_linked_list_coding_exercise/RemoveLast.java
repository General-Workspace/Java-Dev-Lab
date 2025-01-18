/*
No 3
DLL: Remove Last
Implement the removeLast method that removes the last node from the doubly linked list and returns it.

Return type: Node (the node that is being removed)



The method should perform the following tasks:

If the length of the doubly linked list is 0, return null.

Create a temporary Node called temp and set it to the current tail node.

If the length of the doubly linked list is 1:

Set both the head and tail pointers of the list to null.

If the length of the doubly linked list is greater than 1:

Set the tail pointer of the list to the previous node of the current tail node.

Set the next attribute of the new tail node to null.

Set the prev attribute of the temp node to null.

Decrement the length attribute of the list by 1.

Return the removed temp node.
 */
package doubly_linked_list_coding_exercise;

public class RemoveLast {
}
