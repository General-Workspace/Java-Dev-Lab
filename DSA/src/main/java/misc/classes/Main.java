package misc.classes;

public class Main {
    public static void main(String[] args) {
        Cookie cookieOne = new Cookie("green");
        Cookie cookieTwo = new Cookie("blue");
        Cookie cookieThree = new Cookie("");

        cookieThree.setColor("yellow");

        System.out.println(cookieOne.getColor());
        System.out.println(cookieTwo.getColor());
        System.out.println(cookieThree.getColor());
    }
}
