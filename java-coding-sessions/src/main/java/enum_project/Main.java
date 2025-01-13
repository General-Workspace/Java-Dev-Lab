package enum_project;

public class Main {
    public static void main(String[] args) {
        DayOfTheWeek weekDay = DayOfTheWeek.MONDAY;
        System.out.println(weekDay);

        var randomDay = new RandomDay();

        for (int i = 0; i < 10; ++i) {
            weekDay  = randomDay.getRandomDay(7);
            System.out.printf("WeekDay is %s, Ordinal value = %d%n", weekDay.name().charAt(0) + weekDay.name().substring(1).toLowerCase(), weekDay.ordinal() + 1);
        }
    }
}
