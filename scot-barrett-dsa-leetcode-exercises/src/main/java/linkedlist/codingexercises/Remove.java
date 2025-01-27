/*
No. 9
LL: Remove
Implement a method called remove that removes a node at a specified index in the linked list.

Return type: Node (the node that has been removed)



The method should perform the following tasks:

Accept an integer index as an argument, representing the index of the node to be removed.

If the index is less than 0 or greater than or equal to the length of the list, return null.

If the index is 0, call the removeFirst method and return its result.

If the index is equal to the length of the list minus 1, call the removeLast method and return its result.

Call the get method with the index minus 1 to obtain the node before the specified index.

Create a temporary Node object called temp and set it to the next attribute of the node before the specified index.

Update the next attribute of the node before the specified index to point to the next attribute of the temporary Node object.

Set the next attribute of the temporary Node object to null.

Decrement the length attribute of the list by 1.
 */

package linkedlist.codingexercises;

public class Remove {
}
