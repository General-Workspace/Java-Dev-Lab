/*
No. 8
DLL: Insert
Implement the insert method that inserts a new node with a given value at a specified index in the doubly linked list.

Return type: boolean

Method signature: public boolean insert(int index, int value)



The method should perform the following tasks:

Check if the given index is within the valid range. If the index is less than 0 or greater than the list's length, return false.

If the index is 0, call the prepend method with the given value, and return true.

If the index is equal to the list's length, call the append method with the given value, and return true.

Create a new Node object called newNode with the given value.

Use the get method to retrieve the node before the given index and store it in a variable called before.

Set the after variable to the next attribute of the before node.

Set the prev attribute of the newNode to the before node and the next attribute to the after node.

Update the next attribute of the before node to point to the newNode.

Update the prev attribute of the after node to point to the newNode.

Increment the length of the list.

Return true to indicate that the operation was successful.
 */
package doubly_linked_list_coding_exercise;

public class Insert {
}
