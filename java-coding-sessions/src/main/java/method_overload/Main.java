package method_overload;

public class Main {
    public static void main(String[] args) {
        MethodOverloadChallenge result = new MethodOverloadChallenge();

        System.out.println("5ft, 8in = " + result.convertToCentimeters(5, 8) + "cm");
        System.out.println("68in = " + result.convertToCentimeters(68) + "cm");
    }
}
