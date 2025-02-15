package linkedlist.leetcodeinterview.RemoveDuplicates_5;

public class Main {

    public static void main(String[] args) {

        LinkedList myLinkedList = new LinkedList(1);

        myLinkedList.append(1);
        myLinkedList.append(2);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(3);
        myLinkedList.append(3);
        myLinkedList.append(4);

        myLinkedList.removeDuplicates();

        myLinkedList.printList();

        myLinkedList.makeEmpty();

        myLinkedList.append(4);
        myLinkedList.append(5);
        myLinkedList.append(5);
        myLinkedList.append(2);
        myLinkedList.append(6);
        myLinkedList.append(4);
        myLinkedList.append(6);
        myLinkedList.append(7);
        myLinkedList.append(5);
        myLinkedList.append(7);
        myLinkedList.append(8);
        myLinkedList.append(7);

        myLinkedList.removeDuplicatesUsingSets();

        System.out.println();
        myLinkedList.printList();
    }

}
