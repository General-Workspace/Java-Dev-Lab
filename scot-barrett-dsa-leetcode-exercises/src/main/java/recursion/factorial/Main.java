package recursion.factorial;

public class Main {
    public static void main(String[] args) {
        Factorial factorial = new Factorial();

        int num = 8;
        int response = factorial.usingFactorial(num);
        System.out.printf("The result for factorial of %d using while loop is %d.%n", num, response);

        num = 5;
        response = factorial.usingRecursion(num);
        System.out.printf("The recursive result for factorial of %d is %d.%n", num, response);
    }
}
