package inheritance_challenge;

public class Main {
    public static void main(String[] args) {
        SalariedEmployee aniebiet = new SalariedEmployee("Aniebiet Afia", "01/11/1993", "12/12/2052", 192678, "03/01" +
                "/2025", 18000, false);

        System.out.println(aniebiet.getAge());
        System.out.println(aniebiet.collectPay());
        System.out.println(aniebiet.retire());
    }
}
