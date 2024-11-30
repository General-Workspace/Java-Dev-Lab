package control_flow;

public class SwitchStatement {
    public void useSwitchStatement(String data) {
        switch (data) {
            case "Sunday":
                System.out.println("It is the first day of the week.");
                break;
            case "Monday":
                System.out.println("The first working day of the week.");
                break;
            case "Tuesday":
                System.out.println("Things are really picking up on Tuesdays!");
                break;
            case "Wednesday":
                System.out.println("So soon? We're half way through the week.");
                break;
            case "Thursday":
                System.out.println("Let's go balling on Thursdays...");
                break;
            case "Friday":
                System.out.println("Yay! Weekend approaching");
                break;
            case "Saturday":
                System.out.println("Weekend!!! It's social life time.");
                break;
            default:
                System.out.println("What's that? That's not a day of the week...");
                break;
        }
    }

    public void enhancedSwitchStatement(String data) {
        switch (data) {
            case "Sunday" -> System.out.println("It is the first day of the week.");
            case "Monday" -> System.out.println("The first working day of the week.");
            case "Tuesday" -> System.out.println("Things are really picking up on Tuesdays!");
            case "Wednesday" -> System.out.println("So soon? We're half way through the week.");
            case "Thursday" -> System.out.println("Let's go balling on Thursdays...");
            case "Friday" -> System.out.println("Yay! Weekend approaching");
            case "Saturday" -> System.out.println("Weekend!!! It's social life time.");
            default -> System.out.println("What's that? That's not a day of the week...");
        }
    }

    public String enhancedSwitchWithReturn(String month) {
        return switch (month) {
            case "January", "February", "March" -> "1st";
            case "April", "May", "June" -> "2nd";
            case "July", "August", "September" -> "3rd";
            case "October", "November", "December" -> "4th";
            default -> {
                //String invalidEntry = "%s is not a valid month of the year".formatted(month);
                yield "%s is not a valid month of the year".formatted(month);
            }
        };
    }
}
