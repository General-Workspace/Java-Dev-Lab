package inheritance_challenge;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Worker {
    protected String name;
    protected String birthDate;
    protected String endDate;

    public Worker() {

    }

    public Worker(String name, String birthDate, String endDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.endDate = endDate;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(this.birthDate, formatter);

        Period age = Period.between(date, now);

        return age.getYears();
    }

    public double collectPay() {
        return 0.0;
    }

    public void terminate(String endDate) {
        this.endDate = endDate;

    }

    @Override
    public String toString() {
        return "Worker{name='%s', birthDate='%s', endDate='%s'}".formatted(name, birthDate, endDate);
    }
}
