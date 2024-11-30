package control_flow;

public class EnhancedSwitchChallenge {
    public String printDayOfWeek(int day) {

        String dayOfTheWeek = switch (day) {
            case 0 -> { yield "Sunday"; }
            case 1 -> { yield "Monday"; }
            case 2 -> { yield "Tuesday"; }
            case 3 -> { yield "Wednesday"; }
            case 4 -> { yield "Thursday"; }
            case 5 -> { yield "Friday"; }
            case 6 -> { yield "Saturday"; }
            default -> { yield "Invalid Day"; }
        };

        return day + " stands for " + dayOfTheWeek;
    }

    public String printWeekDay(int day) {
        String dayOfTheWeek;

        if (day == 0) {
            dayOfTheWeek = "Sunday";
        } else if (day == 1) {
            dayOfTheWeek = "Monday";
        } else if (day == 2) {
            dayOfTheWeek = "Tuesday";
        } else if (day == 3) {
            dayOfTheWeek = "Wednesday";
        } else if (day == 4) {
            dayOfTheWeek = "Thursday";
        } else if (day == 5) {
            dayOfTheWeek = "Friday";
        } else if (day == 6) {
            dayOfTheWeek = "Saturday";
        } else {
            dayOfTheWeek = "Invalid Day";
        }

        return dayOfTheWeek;
    }
}
