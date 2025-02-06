package recursion.factorial;

public class Factorial {
    public int usingFactorial(int n) {
        if (n < 1) return 0;

        int result = 1;
        while (n >= 1) {
            result *= n;
            n -= 1;
        }

        return result;
    }

    public int usingRecursion(int n) {
        if (n == 1) return 1;

        return n * usingRecursion(n - 1);
    }
}
