/*
No. 10
LL: Reverse
Implement a method called reverse that reverses the order of the nodes in the linked list.

When solving the reverse() method, students are not allowed to create a new list or use any additional data structures besides the linked list itself.

They must reverse the nodes in the existing linked list by manipulating the pointers between them.

Return type: void



The method should perform the following tasks:

The process of reversing the linked list should be done in one pass of the linked list.

Create a temporary Node object called temp and set it to the head attribute of the list.

Set the head attribute of the list to the current tail attribute.

Set the tail attribute of the list to the temporary Node object.

Create a Node object called after and set it to the next attribute of the temporary Node object.

Create a Node object called before and initialize it to null.

Loop through the linked list using a for loop with a counter variable i, starting from 0 and ending at the length attribute of the list. a. Set the after attribute to the next attribute of the temporary Node object. b. Set the next attribute of the temporary Node object to the before attribute. c. Set the before attribute to the temporary Node object. d. Set the temporary Node object to the after attribute.

The method has no return value.
 */

package linkedlist_coding_exercises;

public class Reverse {
}
