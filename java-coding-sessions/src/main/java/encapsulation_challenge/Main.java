package encapsulation_challenge;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(70, true);
        System.out.println("Initial number of pages: " + printer.getPagesPrinted());
        int addedToner = printer.addToner(82);
        int printedPages = printer.printPages(14);
        System.out.println("Added toner: " + addedToner);
        System.out.println("Printed pages: " + printedPages);
    }
}
