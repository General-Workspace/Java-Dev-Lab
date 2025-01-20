package linkedlist_coding_exercises;

public class Main {
    public static void main(String[] args) {
        String lineSeparator = "- ".repeat(33);

        System.out.println("LinkedList Constructor");
        LinkedList linkedList = new LinkedList(1);
        linkedList.getHead();
        linkedList.getTail();
        linkedList.getLength();

        System.out.println(lineSeparator);
        System.out.println("LinkedList Append method");
        linkedList.append(2);
        linkedList.append(3);
        linkedList.printList();

        System.out.println(lineSeparator);
        System.out.println("LinkedList - Remove Last method");
        System.out.println("Removed Node: " + linkedList.removeLast().value);
        linkedList.printList();

        System.out.println(lineSeparator);
        System.out.println("LinkedList - Prepend method");
        linkedList.prepend(5);
        linkedList.prepend(10);
        linkedList.printList();
    }
}
