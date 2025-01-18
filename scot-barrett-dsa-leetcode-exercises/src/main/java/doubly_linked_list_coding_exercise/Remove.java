/*
No. 9
DLL: Remove
Implement the remove method that removes a node at a specified index from the doubly linked list.

Return type: Node



The method should perform the following tasks:

Check if the given index is within the valid range. If the index is less than 0 or greater than or equal to the list's length, return null.

If the index is 0, call the removeFirst method and return its result.

If the index is equal to the list's length minus 1, call the removeLast method and return its result.

Use the get method to retrieve the node at the given index and store it in a variable called temp.

Update the prev attribute of the next node of the temp node to point to the prev node of the temp node.

Update the next attribute of the prev node of the temp node to point to the next node of the temp node.

Set the next and prev attributes of the temp node to null.

Decrement the length of the list.

Return the removed node (the temp node).
 */
package doubly_linked_list_coding_exercise;

public class Remove {
}
