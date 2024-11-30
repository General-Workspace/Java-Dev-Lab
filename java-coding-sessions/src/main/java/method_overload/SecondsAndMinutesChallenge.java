package method_overload;

public class SecondsAndMinutesChallenge {
    public String getDurationString(int seconds) {
        if (seconds < 0) return "Invalid entry. Value should be greater than 0.";

        int minutes = seconds / 60;
        int hours = minutes / 60;

        int remainingMinutes = minutes % 60;
        int remainingSeconds = seconds % 60;

        return "%dh %s".formatted(hours, getDurationString(remainingMinutes, remainingSeconds));
    }

    public String getDurationString(int minutes, int seconds) {
        if (seconds < 0 && (minutes < 0 || minutes > 59)) return "Invalid entry. Value should be greater than 0 or " +
                "less than 59.";
        return "%dm %ds".formatted(minutes, seconds);
    }
}
