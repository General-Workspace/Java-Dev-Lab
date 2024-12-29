package birthday_calculator;

import java.time.Duration;
import java.time.LocalDateTime;

public class BirthdayCalculator {

    /**
     * Calculate the age in years, months, days, hours, minutes, and seconds
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return Age in years, months, days, hours, minutes, and seconds
     */
    public String ageInYears(String year, String month, String day) {
        // Get the current date
        LocalDateTime currentTime = LocalDateTime.now();

        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Calculate the difference between the current date and the birthdate
        var duration = Duration.between(birthDate, currentTime);

        // Calculate the age in years, months, days, hours, minutes, and seconds
        var timeUnit = calculateTimeUnits(duration);

        // Format the age in years, months, days, hours, minutes, and seconds
        var years = formatTimeUnit(timeUnit.years(), "year");
        var months = formatTimeUnit(timeUnit.months(), "month");
        var days = formatTimeUnit(timeUnit.days(), "day");
        var hours = formatTimeUnit(timeUnit.hours(), "hour");
        var minutes = formatTimeUnit(timeUnit.minutes(), "minute");
        var seconds = formatTimeUnit(timeUnit.seconds(), "second");

        return "You are %s old!".formatted("%s, %s, %s, %s, %s, %s".formatted(years, months, days, hours, minutes, seconds));
    }

    /**
     * Check the day of the week you were born on
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return Day of the week
     */
    public String checkDayOfBirthDay(String year, String month, String day) {
        // Parse the birth year, month, and day
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Get the day of the week for the birthdate
        String dayOfWeek = birthDate.getDayOfWeek().toString().toLowerCase();

        // Capitalize the first letter of the day of the week
        return dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
    }

    /**
     * Calculate the remaining days until the next birthday
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return Duration until the next birthday
     */
    public String daysUntilNextBirthday(String year, String month, String day) {
        // Get the current date
        LocalDateTime currentTime = LocalDateTime.now();

        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Calculate the next birthday
        LocalDateTime nextBirthday = birthDate.withYear(currentTime.getYear());

        if (nextBirthday.isBefore(currentTime) || nextBirthday.isEqual(currentTime)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        // Calculate the difference between the current date and the next birthday
        Duration duration = Duration.between(currentTime, nextBirthday);

        // Calculate the remaining month, day, hours, minutes, and seconds until the next birthday
        TimeUnit timeUnit = calculateTimeUnits(duration);

        // Format the remaining month, day, hours, minutes, and seconds until the next birthday
        String months = formatTimeUnit(timeUnit.months(), "month");
        String days = formatTimeUnit(timeUnit.days(), "day");
        String hours = formatTimeUnit(timeUnit.hours(), "hour");
        String minutes = formatTimeUnit(timeUnit.minutes(), "minute");
        String seconds = formatTimeUnit(timeUnit.seconds(), "second");

        return "There are %s until your next birthday!".formatted("%s, %s, %s, %s, %s".formatted(months, days, hours, minutes, seconds));
    }

    /**
     * Calculate the age before a specific date
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @param targetYear year of target date
     * @param targetMonth month of target date
     * @param targetDay day of target date
     * @return Age before the target date
     */
    public String ageBeforeDate(String year, String month, String day, String targetYear, String targetMonth, String targetDay) {
        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Get the target date
        LocalDateTime targetDate = parseBirthDate(targetYear, targetMonth, targetDay);

        // Calculate the difference between the target date and the birthdate
        Duration duration = Duration.between(birthDate, targetDate);

        // Calculate the age in years, months, days, hours, minutes, and seconds
        TimeUnit timeUnit = calculateTimeUnits(duration);

        String years = formatTimeUnit(timeUnit.years(), "year");
        String months = formatTimeUnit(timeUnit.months(), "month");
        String days = formatTimeUnit(timeUnit.days(), "day");
        String hours = formatTimeUnit(timeUnit.hours(), "hour");
        String minutes = formatTimeUnit(timeUnit.minutes(), "minute");
        String seconds = formatTimeUnit(timeUnit.seconds(), "second");

        return "You will be %s old on %s!".formatted("%s, %s, %s, %s, %s, %s".formatted(years, months, days, hours, minutes, seconds), targetDate.toLocalDate());
    }


    /**
     * Calculate the duration since the last birthday
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return Duration since the last birthday
     */
    public String durationSinceLastBirthday(String year, String month, String day) {
        // Get the current date
        LocalDateTime currentTime = LocalDateTime.now();

        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Calculate the last birthday
        LocalDateTime lastBirthday = birthDate.withYear(currentTime.getYear());

        if (lastBirthday.isAfter(currentTime) || lastBirthday.isEqual(currentTime)) {
            lastBirthday = lastBirthday.minusYears(1);
        }

        // Calculate the difference between the current date and the last birthday
        Duration duration = Duration.between(lastBirthday, currentTime);

        // Calculate the duration in years, months, days, hours, minutes, and seconds
        TimeUnit timeUnit = calculateTimeUnits(duration);

        //String years = formatTimeUnit(timeUnit.years(), "year");
        String months = formatTimeUnit(timeUnit.months(), "month");
        String days = formatTimeUnit(timeUnit.days(), "day");
        String hours = formatTimeUnit(timeUnit.hours(), "hour");
        String minutes = formatTimeUnit(timeUnit.minutes(), "minute");
        String seconds = formatTimeUnit(timeUnit.seconds(), "second");

        return "It has been %s since your last birthday!".formatted("%s, %s, %s, %s, %s".formatted(months, days, hours, minutes, seconds));
    }

    /**
     * The year, month and day that user will achieve a certain age
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @param age age to calculate future birthday
     * @return Future birthday
     */
    public String futureBirthday(String year, String month, String day, String age) {
        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Get the age
        long ageToAchieve = Long.parseLong(age);

        // Calculate the future birthday
        LocalDateTime futureBirthday = birthDate.plusYears(ageToAchieve);

        return "You will be %s years old on %s!".formatted(age, futureBirthday.toLocalDate());
    }

    /**
     * Number of years that have passed since the user became an adult (18 years old)
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return Duration since the user became an adult
     */
    public String yearsSinceBecomingAdult(String year, String month, String day) {
        // Get the birthdate
        LocalDateTime birthDate = parseBirthDate(year, month, day);

        // Calculate the date when the user became an adult
        LocalDateTime becameAdult = birthDate.plusYears(18);

        // Get the current date
        LocalDateTime currentTime = LocalDateTime.now();

        // Calculate the difference between the current date and the date when the user became an adult
        Duration duration = Duration.between(becameAdult, currentTime);

        // Calculate the duration in years, months, days, hours, minutes, and seconds
        TimeUnit timeUnit = calculateTimeUnits(duration);

        String years = formatTimeUnit(timeUnit.years(), "year");
        String months = formatTimeUnit(timeUnit.months(), "month");
        String days = formatTimeUnit(timeUnit.days(), "day");
        String hours = formatTimeUnit(timeUnit.hours(), "hour");
        String minutes = formatTimeUnit(timeUnit.minutes(), "minute");
        String seconds = formatTimeUnit(timeUnit.seconds(), "second");

        return "It has been %s since you became an adult!".formatted("%s, %s, %s, %s, %s, %s".formatted(years, months, days, hours, minutes, seconds));
    }

    /**
     * Format the time unit
     * @param timeUnit (year, month, day, hour, minute, second)
     * @param unit literal String of the time unit
     * @return formatted String
     */
    public String formatTimeUnit(long timeUnit, String unit) {
        return "%d %s%s".formatted(timeUnit, unit, timeUnit > 1 ? "s" : "");
    }

    /**
     * Reusable method to Calculate the time units
     * @param duration Duration object
     * @return TimeUnit object
     */
    public TimeUnit calculateTimeUnits(Duration duration) {

        // Calculate the age in years, months, days, hours, minutes, and seconds
        long years = duration.toDays() / 365;
        long months = (duration.toDays() % 365) / 30;
        long days = (duration.toDays() % 365) % 30;
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return new TimeUnit(years, months, days, hours, minutes, seconds);
    }

    /**
     * Reusable method to Parse the birth year, month, and day
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @return LocalDateTime object
     */
    public LocalDateTime parseBirthDate(String year, String month, String day) {
        // Parse the birth year, month, and day
        int birthYear = Integer.parseInt(year);
        int birthMonth = Integer.parseInt(month);
        int birthDay = Integer.parseInt(day);

        // Get the birthdate
        return LocalDateTime.of(birthYear, birthMonth, birthDay, 0, 0, 0);
    }

}
