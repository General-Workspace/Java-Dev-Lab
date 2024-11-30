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
            case "Thurday":
                System.out.println("Let's go balling on Thurdays...");
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
}
