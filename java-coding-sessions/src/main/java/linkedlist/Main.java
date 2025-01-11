package linkedlist;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<Place> placesToVisit = new LinkedList<>();
        LinkedListChallenge places = new LinkedListChallenge();

        places.addPlace(placesToVisit, new Place("Rivers", 1374));
        places.addPlace(placesToVisit, new Place("Kaduna", 917));
        places.addPlace(placesToVisit, new Place("Akwa Ibom", 3923));
        places.addPlace(placesToVisit, new Place("Kogi", 2771));
        places.addPlace(placesToVisit, new Place("Lagos", 3972));
        places.addPlace(placesToVisit, new Place("Benue", 877));

        placesToVisit.addFirst(new Place("Abuja", 0));
        System.out.println(placesToVisit);

        var iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        places.printMenu();

        while(!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }
            System.out.println("Enter value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0, 1);

            switch (menuItem) {
                case "F" -> {
                    System.out.println("Going forward");
                    if (!forward) {     // Reversing direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();    // Adjust position forward
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                case "B" -> {
                    System.out.println("Going Backwards");
                    if (forward) {     // Reversing direction
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();    // Adjust position backward
                        }
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                }
                case "L" -> System.out.println(placesToVisit);
                case "M" -> places.printMenu();
                default -> quitLoop = true;
            }
        }
    }
}
