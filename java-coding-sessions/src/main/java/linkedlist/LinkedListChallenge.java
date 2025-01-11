package linkedlist;

import java.util.LinkedList;

public class LinkedListChallenge {

    public void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace: list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }


        list.add(place);
    }

    public void printMenu() {
        String menu = """
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit""";
        System.out.println(menu);
    }
}
