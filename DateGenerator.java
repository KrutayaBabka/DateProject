import java.util.*;

/**
 * A class that generates random Date objects within a specified range.
 */
public class DateGenerator {
    // Random object to generate random values
    private final Random random = new Random();
    
    // Default range values
    private int minDay = 1, maxDay = 31;
    private int minMonth = 1, maxMonth = 12;
    private int minYear = 1, maxYear = Integer.MAX_VALUE;

    /**
     * Returns the minimum day of the allowed range.
     *
     * @return the minimum day (1-31)
     */
    public int getMinDay() {
        return minDay;
    }

    /**
     * Returns the maximum day of the allowed range.
     *
     * @return the maximum day (1-31)
     */
    public int getMaxDay() {
        return maxDay;
    }

    /**
     * Returns the minimum month of the allowed range.
     *
     * @return the minimum month (1-12)
     */
    public int getMinMonth() {
        return minMonth;
    }

    /**
     * Returns the maximum month of the allowed range.
     *
     * @return the maximum month (1-12)
     */
    public int getMaxMonth() {
        return maxMonth;
    }

    /**
     * Returns the minimum year of the allowed range.
     *
     * @return the minimum year (1 or greater)
     */
    public int getMinYear() {
        return minYear;
    }

    /**
     * Returns the maximum year of the allowed range.
     *
     * @return the maximum year (1 to Integer.MAX_VALUE)
     */
    public int getMaxYear() {
        return maxYear;
    }

    /**
     * Sets the minimum allowed day.
     *
     * @param minDay the minimum day value to set.
     * @throws IllegalArgumentException if minDay is greater than maxDay 
     *                                  or greater than the minimum possible days in the selected month range.
     */
    public void setMinDay(int minDay) { 
        if (minDay > this.maxDay) {
            throw new IllegalArgumentException("Invalid day: " + minDay + " must be less or equal than " + this.maxDay);
        }

        int minDaysInMonths = Date.getMinDaysInMonths(this.minMonth, this.maxMonth, Date.hasLeapYearInRange(this.minYear, this.maxYear));
        if (minDay > minDaysInMonths) {
            throw new IllegalArgumentException("Invalid day: " + minDay + ". minDay must be less or equal than minDaysInMonths " + minDaysInMonths);
        }

        this.minDay = minDay; 
    }

    /**
     * Sets the maximum allowed day.
     *
     * @param maxDay the maximum day value to set.
     * @throws IllegalArgumentException if maxDay is less than minDay 
     *                                  or greater than the maximum possible days in the selected month range.
     */
    public void setMaxDay(int maxDay) { 
        if (maxDay < this.minDay) {
            throw new IllegalArgumentException("Invalid day: " + maxDay + " must be greater or equal than " + this.minDay);
        }

        int maxDaysInMonths = Date.getMaxDaysInMonths(this.minMonth, this.maxMonth, Date.hasLeapYearInRange(this.minYear, this.maxYear));
        if (maxDay > maxDaysInMonths) {
            throw new IllegalArgumentException("Invalid day: " + maxDay + ". maxDay must be less or equal than maxDaysInMonths " + maxDaysInMonths);
        }
        
        this.maxDay = maxDay; 
    }

    /**
     * Sets the minimum allowed month.
     *
     * @param minMonth the minimum month value to set (1-12).
     * @throws IllegalArgumentException if minMonth is out of range (1-12),
     *                                  greater than maxMonth, or if minDay 
     *                                  is greater than the minimum possible days in the selected month range.
     */
    public void setMinMonth(int minMonth) { 
        if (!Date.isValidMonth(minMonth)) {
            throw new IllegalArgumentException("Invalid month: " + minMonth + " must be between 1 and 12");
        }
        if (minMonth > this.maxMonth) {
            throw new IllegalArgumentException("Invalid month: " + minMonth + " must be less or equal than " + this.maxMonth);
        }

        int minDaysInMonths = Date.getMinDaysInMonths(minMonth, this.maxMonth, Date.hasLeapYearInRange(this.minYear, this.maxYear));
        if (this.minDay > minDaysInMonths) {
            throw new IllegalArgumentException("Invalid month: " + minMonth + ". minDay(" + this.minDay + ") must be less or equal than minDaysInMonths " + minDaysInMonths);
        }

        this.minMonth = minMonth; 
    }

    /**
     * Sets the maximum allowed month.
     *
     * @param maxMonth the maximum month value to set (1-12).
     * @throws IllegalArgumentException if maxMonth is out of range (1-12),
     *                                  less than minMonth, or if maxDay 
     *                                  is greater than the maximum possible days in the selected month range.
     */
    public void setMaxMonth(int maxMonth) { 
        if (!Date.isValidMonth(maxMonth)) {
            throw new IllegalArgumentException("Invalid month: " + maxMonth + " must be between 1 and 12");
        }
        if (maxMonth < this.minMonth) {
            throw new IllegalArgumentException("Invalid month: " + maxMonth + " must be greater or equal than " + this.minMonth);
        }

        int maxDaysInMonths = Date.getMaxDaysInMonths(this.minMonth, maxMonth, Date.hasLeapYearInRange(this.minYear, this.maxYear));
        if (this.maxDay > maxDaysInMonths) {
            throw new IllegalArgumentException("Invalid month: " + maxMonth + ". maxDay(" + this.maxDay + ") must be less or equal than maxDaysInMonths " + maxDaysInMonths);
        }
        
        this.maxMonth = maxMonth; 
    }

    /**
     * Sets the minimum allowed year.
     *
     * @param minYear the minimum year value to set.
     * @throws IllegalArgumentException if minYear is not a valid year 
     *                                  (must be greater than 0) or greater than maxYear.
     */
    public void setMinYear(int minYear) { 
        if (!Date.isValidYear(minYear)) {
            throw new IllegalArgumentException("Invalid year: " + minYear + " must be greater than 0");
        }
        if (minYear > this.maxYear) {
            throw new IllegalArgumentException("Invalid year: " + minYear + " must be less or equal than " + this.maxYear);
        }

        this.minYear = minYear; 
    }

    /**
     * Sets the maximum allowed year.
     *
     * @param maxYear the maximum year value to set.
     * @throws IllegalArgumentException if maxYear is not a valid year 
     *                                  (must be greater than 0) or less than minYear.
     */
    public void setMaxYear(int maxYear) { 
        if (!Date.isValidYear(maxYear)) {
            throw new IllegalArgumentException("Invalid year: " + maxYear + " must be greater than 0");
        }
        if (maxYear < this.minYear) {
            throw new IllegalArgumentException("Invalid year: " + maxYear + " must be greater or equal than " + this.minYear);
        }

        this.maxYear = maxYear; 
    }

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

            dates.add(new Date(month, day, year));
        }

        return dates;
    }
}
