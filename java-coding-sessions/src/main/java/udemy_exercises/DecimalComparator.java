package udemy_exercises;

/*
DecimalComparator
Write a method areEqualByThreeDecimalPlaces with two parameters of type double.

The method should return boolean, and it needs to return true if two double numbers are the same up to three decimal places. Otherwise, return false.



EXAMPLES OF INPUT/OUTPUT:

areEqualByThreeDecimalPlaces(-3.1756, -3.175); → should return true since numbers are equal up to 3 decimal places.

areEqualByThreeDecimalPlaces(3.175, 3.176); → should return false since numbers are not equal up to 3 decimal places

areEqualByThreeDecimalPlaces(3.0, 3.0); → should return true since numbers are equal up to 3 decimal places.

areEqualByThreeDecimalPlaces(-3.123, 3.123); → should return false since numbers are not equal up to 3 decimal places.



TIP: Use paper and pencil.

TIP: Use casting.

NOTE: The areEqualByThreeDecimalPlaces method  needs to be defined as public static ​like we have been doing so far in the course.
NOTE: Do not add a  main method to solution code.
 */

public class DecimalComparator {
    public static void main(String[] ignoredArgs) {
        System.out.println(areEqualByThreeDecimalPlaces(-3.1756, -3.175)); // true
        System.out.println(areEqualByThreeDecimalPlaces(3.175, 3.176)); // false
        System.out.println(areEqualByThreeDecimalPlaces(3.0, 3.0)); // true
        System.out.println(areEqualByThreeDecimalPlaces(-3.123, 3.123)); // false
    }
    public static boolean areEqualByThreeDecimalPlaces(double x, double y) {
        return (int) (x * 1000) == (int) (y * 1000);
    }
}
