package method_overload;

public class MethodOverloadChallenge {
    public double convertToCentimeters(int heightInInches) {
        return heightInInches * 2.54;
    }

    public double convertToCentimeters(int heightInFeet, int heightInInches) {
        double convertFeetToInches = heightInFeet * 12;
        double totalHeightInInches = convertFeetToInches + heightInInches;
        return convertToCentimeters((int) totalHeightInInches);
    }
}
