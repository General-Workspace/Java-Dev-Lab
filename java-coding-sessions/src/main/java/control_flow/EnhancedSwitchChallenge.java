package control_flow;

public class EnhancedSwitchChallenge {
    public String printDayOfWeek(int day) {

        return switch (day) {
            case 0 -> { yield "Sunday"; }
            case 1 -> { yield "Monday"; }
            case 2 -> { yield "Tuesday"; }
            case 3 -> { yield "Wednesday"; }
            case 4 -> { yield "Thursday"; }
            case 5 -> { yield "Friday"; }
            case 6 -> { yield "Saturday"; }
            default -> { yield "Invalid Day"; }
        };
    }

    public String printWeekDay(int day) {
        if (day == 0) {
            return day + " is Sunday";
        } else if (day == 1) {
            return day + " is Monday";
        } else if (day == 2) {
            return day + " is Tueday";
        } else if (day == 3) {
            return day + " is Wednesday";
        } else if (day == 4) {
            return day + " is Thurday";
        } else if (day == 5) {
            return day + " is Friday";
        } else if (day == 6) {
            return day + " is Saturday";
        } else {
            return "Invalid Day";
        }
    }
}
