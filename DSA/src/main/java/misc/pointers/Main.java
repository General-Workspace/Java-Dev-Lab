package misc.pointers;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        int num1 = 11;
        int num2 = num1;

        num1 = 22;

        System.out.printf("Num 1: %d%n", num1);
        System.out.printf("Num 2: %d%n", num2);

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        HashMap<String, Integer> map3 = new HashMap<>();

        map1.put("0", 11);

        map1.put("1", 22);
        map3.put("2", 57);

        map1 = map3;
        map2 = map3;

        System.out.println(map1);
        System.out.println(map2);
        System.out.println(map3);
    }
}
