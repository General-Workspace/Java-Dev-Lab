package enum_project;

import java.util.Random;

public class RandomDay {
    public DayOfTheWeek getRandomDay(int bound) {
        Random random = new Random();
        int randomInteger = random.nextInt(bound);
        var allDays = DayOfTheWeek.values();

        return allDays[randomInteger];
    }
}
