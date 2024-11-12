package datastructures.linkedlist;

public class Main {
    public static void main(String[] args) {
        int count;
        String sep = "*";

        count = "Creating Node".length();
        System.out.println(sep.repeat(count));
        System.out.println("Creating Node");
        LinkedList myLinkedList1 = new LinkedList(4);
        myLinkedList1.getHead();
        myLinkedList1.getTail();
        myLinkedList1.getLength();

        count = "Linked List: Append".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Append");
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);

        myLinkedList.printList();

        count = "Linked List: Get".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Get");

        System.out.println("Get at index: " + myLinkedList.get(0).value);

        count = "Linked List: Remove".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Remove");
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast().value);
        System.out.println(myLinkedList.removeLast());

        count = "Linked List: Prepend".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Prepend");

        LinkedList prependLinkedList = new LinkedList(3);
        prependLinkedList.prepend(2);
        prependLinkedList.prepend(8);
        prependLinkedList.printList();

        count = "Linked List: Remove First".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Remove First");

        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst().value);
        System.out.println(prependLinkedList.removeFirst());

        count = "Linked List: Before Set value at an index".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Before Set value at an index");

        LinkedList setValueAtIndex = new LinkedList(2);
        setValueAtIndex.append(4);
        setValueAtIndex.append(8);
        setValueAtIndex.append(16);

        setValueAtIndex.printList();

        count = "Linked List: After Set value at an index".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: After Set value at an index");
        setValueAtIndex.set(2, 10);
        setValueAtIndex.printList();

        count = "Linked List: Insert Value at an index.".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Insert Value at an index.");

        LinkedList insertValue = new LinkedList(1);
        insertValue.append(3);

        count = "Linked List: Insert Value after an index.".length();
        System.out.println(sep.repeat(count));
        System.out.println("Linked List: Insert Value after an index.");
        insertValue.insert(1, 2);
        insertValue.printList();

    }
}
