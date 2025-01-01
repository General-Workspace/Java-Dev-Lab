package encapsulation_challenge;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        //this.tonerLevel = tonerLevel < 10 ? tonerLevel = 10 : tonerLevel;
        this.tonerLevel = tonerLevel <= 0 && tonerLevel >= 100 ? tonerLevel : -1;
        this.duplex = duplex;
    }

    public int addToner(int tonerAmount) {
        int newTonerAmount = tonerAmount + tonerLevel;

        if (newTonerAmount > 100 || newTonerAmount < 0) {
            return -1;
        }
        return this.tonerLevel += tonerAmount;
    }

    public int printPages(int pages) {
        if (this.duplex) {
            System.out.println("It's a duplex printer.");
            return this.pagesPrinted = (pages / 2) + (pages % 2);
        } else {
            return this.pagesPrinted = pages;
        }
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public boolean isDuplex() {
        return duplex;
    }
}
