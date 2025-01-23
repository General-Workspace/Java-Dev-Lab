/*
No. 6
LL: Binary to Decimal ( ** Interview Question)
Objective:

You have a linked list where each node represents a binary digit (0 or 1). The goal of the binaryToDecimal function is to convert this binary number, represented by the linked list, into its decimal equivalent.



Function Signature:

public int binaryToDecimal()



How Binary to Decimal Conversion Works:

In binary-to-decimal conversion, each position of a binary number corresponds to a specific power of 2, starting from the rightmost digit.

The rightmost digit is multiplied by 2^0 (which equals 1).

The next digit to the left is multiplied by 2^1 (which equals 2).

The digit after that is multiplied by 2^2 (which equals 4). ... and so on.

To find the decimal representation:

Multiply each binary digit by its corresponding power of 2 value.

Sum up all these products.



Example Execution with Binary 101:

Start with num = 0.

Process 1 (from the head of the linked list): num = 0 * 2 + 1 = 1

Process 0: num = 1 * 2 + 0 = 2

Process 1: num = 2 * 2 + 1 = 5

Return num, which is 5.



Steps Involved in the Function:

A variable num is initialized to 0, which will store our computed decimal number.

Starting from the head of the linked list (the leftmost binary digit), iterate through each node until the end.

For every node, double the current value of num (this is analogous to shifting in binary representation). Then, add the binary digit of the current node.

Move to the next node and repeat until you've visited all nodes.

Return the value in num, which now represents the decimal value of the binary number in the linked list.
 */
package linkedlist_leetcode_interview.BinaryToDecimal_6;

public class BinaryToDecimal {
}

/*
public class LinkedList {

    private Node head;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        if (temp == null) {
            System.out.println("empty");
        } else {
            while (temp != null) {
                System.out.print(temp.value);
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public void makeEmpty() {
        head = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
    }

    //   +===================================================+
    //   |               WRITE YOUR CODE HERE                |
    //   | Description:                                      |
    //   | - This method converts a binary number,           |
    //   |   represented as a linked list, to a decimal int. |
    //   |                                                   |
    //   | Return type: int                                  |
    //   | - Returns the decimal equivalent of the binary    |
    //   |   number.                                         |
    //   |                                                   |
    //   | Tips:                                             |
    //   | - We use a while loop to traverse the linked list.|
    //   | - Multiply the current sum by 2 and add the value |
    //   |   at each node to get the decimal number.         |
    //   +===================================================+

}



 */
