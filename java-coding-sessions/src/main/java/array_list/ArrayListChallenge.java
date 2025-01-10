package array_list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ArrayListChallenge {
    private String menu;

    public void menuOptions() {
        menu = """
                0 - to shutdown
                
                1 - to add item(s) to list (comma delimited list)
                
                2 - to remove any item(s) (comma delimited list)
                
                Enter a number for which action to do:""";

        System.out.println(menu + " ");
    }

    public void arrayListChallenge() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        ArrayList<String> groceriesList = new ArrayList<>();

        while(flag) {
            menuOptions();
            int entry = scanner.nextInt();

//            switch (entry) {
//                case 1 -> addItems(groceriesList);
//                case 2 -> removeItems(groceriesList);
//                default -> flag = false;
//            }
            if (entry == 0) {
                System.out.println("Exiting the program");
                break;
            } else if (entry == 1) {
                addItems(groceriesList);
            } else if (entry == 2) {
                removeItems(groceriesList);
            }
            groceriesList.sort(Comparator.naturalOrder());
            System.out.println(groceriesList);
        }
    }

    public void addItems(ArrayList<String> groceriesList) {
        System.out.println("Add item(s) [separate items by comma]: ");
        Scanner scanner = new Scanner(System.in);
        String[] items = scanner.nextLine().split(",");

        for (String i : items) {
            String trimmed = i.trim();
            if (!groceriesList.contains(trimmed)) {
                groceriesList.add(trimmed);
            }
        }

    }

    public void removeItems(ArrayList<String> groceriesList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Remove item(s) [separate items by comma]: ");
        String[] items = scanner.nextLine().split(",");

        for (String i : items) {
            String trimmed = i.trim();
            groceriesList.remove(trimmed);
        }
    }
}
