package datastructures.linkedlist;

public class Main {
    public static void main(String[] args) {
        int count = 20;
        String sep = "*";

        System.out.println(sep.repeat(count));
        System.out.println("Creating Node");
        LinkedList myLinkedList1 = new LinkedList(4);
        myLinkedList1.getHead();
        myLinkedList1.getTail();
        myLinkedList1.getLength();

        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Append");
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);

        myLinkedList.printList();

        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Get");

        System.out.println("Get at index: " + myLinkedList.get(0).value);

        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Remove");
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast());

        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Prepend");

        LinkedList prependLinkedList = new LinkedList(3);
        prependLinkedList.prepend(2);
        prependLinkedList.prepend(8);
        prependLinkedList.printList();

        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Remove First");

        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst());

    }
}
