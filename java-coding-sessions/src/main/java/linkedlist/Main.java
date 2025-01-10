package linkedlist;

import java.util.LinkedList;

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
    }
}
