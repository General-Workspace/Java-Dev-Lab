/*
No. 6
DLL: Get
Implement the get method that retrieves a node at a given index from the doubly linked list.

Return type: Node



The method should perform the following tasks:

If the index is less than 0 or greater than or equal to the length of the doubly linked list, return null.

Initialize a temp variable to store the node to be retrieved.

If the index is less than half of the list's length:

Set temp to the head node.

Iterate from the head node to the target index by updating temp to temp.next in each iteration.

If the index is greater than or equal to half of the list's length:

Set temp to the tail node.

Iterate from the tail node to the target index by updating temp to temp.prev in each iteration.

Return the temp node.
 */
package doublylinkedlist.codingexercises;

public class Get {
}
