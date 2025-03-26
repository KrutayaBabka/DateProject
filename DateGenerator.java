import java.util.*;

public class DateGenerator {
    private int minDay = 1, maxDay = 31;
    private int minMonth = 1, maxMonth = 12;
    private int minYear = 1, maxYear = 2025;
    private final Random random = new Random();

    public void setMinDay(int minDay) { this.minDay = minDay; }
    public void setMaxDay(int maxDay) { this.maxDay = maxDay; }
    public void setMinMonth(int minMonth) { this.minMonth = minMonth; }
    public void setMaxMonth(int maxMonth) { this.maxMonth = maxMonth; }
    public void setMinYear(int minYear) { this.minYear = minYear; }
    public void setMaxYear(int maxYear) { this.maxYear = maxYear; }

    /**
     * Generates a list of n random Date objects.
     *
     * @param n the number of random Date objects to generate
     * @return a list of n random Date objects
     */
    public List<Date> generateRandomDates(int n) {
        List<Date> dates = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int year = random.nextInt(maxYear - minYear + 1) + minYear;
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (Date.isLeapYear(year)) daysInMonth[1] = 29;

            int month = random.nextInt(maxMonth - minMonth + 1) + minMonth;
            int day = random.nextInt(Math.min(maxDay, daysInMonth[month - 1]) - minDay + 1) + minDay;

            dates.add(new Date(day, month, year));
        }

        return dates;
    }
}
