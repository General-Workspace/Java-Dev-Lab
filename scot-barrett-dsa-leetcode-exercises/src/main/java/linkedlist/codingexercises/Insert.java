/*
No. 8
LL: Insert
Implement a method called insert that inserts a new node at a specified index in the linked list.

Return type: boolean

Method signature: public boolean insert(int index, int value)



The method should perform the following tasks:

Accept an integer index as an argument, representing the index at which the new node should be inserted.

Accept an integer value as an argument, representing the value of the new node.

If the index is less than 0 or greater than the length of the list, return false.

If the index is 0, call the prepend method with the provided value and return true.

If the index is equal to the length of the list, call the append method with the provided value and return true.

Create a new Node object called newNode with the provided value.

Call the get method with the index minus 1 to obtain the node before the specified index.

Update the next attribute of the newNode to point to the next attribute of the node before the specified index.

Update the next attribute of the node before the specified index to point to the newNode.

Increment the length attribute of the list by 1.
 */

package linkedlist.codingexercises;

public class Insert {
}
