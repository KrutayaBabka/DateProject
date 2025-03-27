import java.time.LocalDate;
import java.util.List;

/**
 * The Date class represents a date with day, month, and year.
 * It allows validation of dates and determination of leap years.
 */
public class Date implements Comparable<Date>{
    //-----------------------------------------------------------------------------------------------|
    //                                       CONSTRUCTORS                                            |
    //-----------------------------------------------------------------------------------------------|

    /**
     * Creates a Date object representing the current date.
     */
    public Date() {
        LocalDate today = LocalDate.now();
        this.day = today.getDayOfMonth();
        this.month = today.getMonthValue();
        this.year = today.getYear();
    }

    /**
     * Creates a Date object with the specified day, month, and year.
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param year  the year (1 and above)
     * @throws IllegalArgumentException if the provided date is invalid
     */
    public Date(int month, int day, int year) {
        if (!isValidDate(month, day, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //-----------------------------------------------------------------------------------------------|
    //                                       STATIC METHODS                                          |
    //-----------------------------------------------------------------------------------------------|
    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the number of days in a given month for a specified year.
     * Accounts for leap years when calculating February days.
     *
     * @param month the month (1-12)
     * @param year the year to consider for leap year calculation
     * @return the number of days in the specified month
     * @throws IllegalArgumentException if the month is not in the range 1-12
     */
    public static int getDaysInMonth(int month, int year) {
        if(!isValidMonth(month) || !isValidYear(year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " (must be 1-12)");
        }
        if(!isValidYear(year)) {
            throw new IllegalArgumentException("Invalid year: " + year + " (must be 1 and above)");
        }
        
        // If the month is February and it's a leap year, return 29 days
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        
        // Array representing the number of days in each month
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        return daysInMonth[month - 1]; // Return the correct number of days for the given month
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Checks if the given date is valid.
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param year  the year (1 and above)
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(int month, int day, int year) {
        return isValidYear(year) && isValidMonth(month) && isValidDay(month, day, year);
    }

    /**
     * Checks if the given year is valid.
     *
     * @param year the year to check (must be 1 or greater)
     * @return true if the year is valid, false otherwise
     */
    public static boolean isValidYear(int year) {
        return year >= 1;
    }

    /**
     * Checks if the given month is valid.
     *
     * @param month the month to check (1 to 12)
     * @return true if the month is valid, false otherwise
     */
    public static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    /**
     * Checks if the given day is valid for the specified month and year.
     *
     * @param day   the day to check (1 to 31)
     * @param month the month in which the day is checked
     * @param year  the year to consider (for leap year adjustments)
     * @return true if the day is valid, false otherwise
     */
    public static boolean isValidDay(int month, int day, int year) {
        int maxDays = getDaysInMonth(month, year);
        return day >= 1 && day <= maxDays;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Determines whether the given year is a leap year.
     * A year is a leap year if it is divisible by 4 but not by 100,
     * except when it is also divisible by 400 (in which case it is a leap year).
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        if(!isValidYear(year)) {
            throw new IllegalArgumentException("Invalid year: " + year + " (must be 1 and above)");
        }

        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Displays the given date in a readable format, such as "January 1, 2023".
     * Ends with a newline by default.
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month
     * @param year  the year
     */
    public static void printDate(int month, int day, int year) {
        printDate(month, day, year, "\n"); // Calls the overloaded method with default newline
    }

    /**
     * Displays the given date in a readable format, such as "January 1, 2023",
     * and allows specifying a custom ending (e.g., "" for no newline).
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month
     * @param year  the year
     * @param end   the ending character(s) to append after the date
     */
    public static void printDate(int month, int day, int year, String end) {
        if (!isValidDate(month, day, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }

        System.out.print(getMonthName(month) + " " + day + ", " + year + end);
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the name of the month as a string.
     * 
     * @param month the month of the year (1 to 12)
     * @return the name of the current month
     */
    public static String getMonthName(int month) {
        if(!isValidMonth(month)) {
            throw new IllegalArgumentException("Invalid month: " + month + " (must be 1-12)");
        }

        String[] monthNames = { "January", "February", "March", "April", "May", "June", 
                                "July", "August", "September", "October", "November", "December" };
        return monthNames[month - 1]; // Return the month name
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the day of the week for the current date instance (e.g., "Monday"),
     * using Zeller's Congruence algorithm.
     * @param month the new month to set
     * @param day the new day to set
     * @param year the new year to set
     * @throws IllegalArgumentException if the provided date is invalid
     * @return the name of the day of the week
     */
    public static String getDayOfWeek(int month, int day, int year) {
        if(!isValidDate(month, day, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }

        int m = month;
        int d = day;
        int y = year;

        // Adjust months so that March = 3, ..., December = 12, January = 13, February = 14 (previous year)
        if (m < 3) {
            m += 12;
            y -= 1;
        }

        int K = y % 100; // Year within the century
        int J = y / 100; // Zero-based century

        // Zeller's Congruence formula
        int h = (d + (13 * (m + 1)) / 5 + K + (K / 4) + (J / 4) + (5 * J)) % 7;

        // Days of the week mapping (Zeller's algorithm returns 0 = Saturday, 1 = Sunday, ..., 6 = Friday)
        String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

        return days[h]; // Return the computed day of the week
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Computes the difference in days between two dates.
     * Considers leap years.
     *
     * @param month_1 the month of the first date (1 to 12)
     * @param day_1   the day of the first date
     * @param year_1  the year of the first date
     * @param month_2 the month of the second date (1 to 12)
     * @param day_2   the day of the second date
     * @param year_2  the year of the second date
     * @return the absolute number of days between the two dates
     * @throws IllegalArgumentException if either date is invalid
     */
    public static int calculateDifference(int month_1, int day_1, int year_1, int month_2, int day_2, int year_2) {
        // Validate the input dates
        if (!isValidDate(month_1, day_1, year_1)) {
            throw new IllegalArgumentException("Invalid date: " + month_1 + " " + day_1 + ", " + year_1);
        }
        if (!isValidDate(month_2, day_2, year_2)) {
            throw new IllegalArgumentException("Invalid date: " + month_2 + " " + day_2 + ", " + year_2);
        }

        // Calculate the total days for each date directly
        int days1 = toDays(month_1, day_1, year_1);
        int days2 = toDays(month_2, day_2, year_2);

        // Return the absolute difference in days
        return Math.abs(days1 - days2);
    }

    /**
     * Converts a given date to the total number of days since year 1.
     * Uses a faster, optimized method to calculate the total days.
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month
     * @param year  the year
     * @return the total number of days from year 1 to the given date
     */
    public static int toDays(int month, int day, int year) {
        // Total days from full years (ignores leap years)
        int days = (year - 1) * 365;

        // Add leap years
        days += (year - 1) / 4;   // Every 4 years is a leap year
        days -= (year - 1) / 100; // Every 100 years is not a leap year
        days += (year - 1) / 400; // Every 400 years is a leap year again

        for (int i = 0; i < month - 1; i++) {
            days += getDaysInMonth(i + 1, year);
        }

        // Add the days of the current month
        days += day;

        return days;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Checks if there is at least one leap year in the given range [minYear, maxYear].
     * A leap year is a year that is:
     * - Divisible by 4
     * - Not divisible by 100, unless also divisible by 400
     *
     * @param minYear the start of the range (inclusive)
     * @param maxYear the end of the range (inclusive)
     * @return true if there is at least one leap year in the range, false otherwise
     * @throws IllegalArgumentException if minYear is greater than maxYear
     */
    public static boolean hasLeapYearInRange(int minYear, int maxYear) {
        if (minYear > maxYear) {
            throw new IllegalArgumentException("Invalid range: minYear(" + minYear + ") must be less or equal maxYear(" + maxYear + ").");
        }

        // Find the first leap year greater than or equal to minYear
        int firstLeap = (minYear % 4 == 0) ? minYear : minYear + (4 - minYear % 4);

        // If the found year is divisible by 100 but not by 400, it is not a leap year
        if (firstLeap % 100 == 0 && firstLeap % 400 != 0) {
            firstLeap += 4; // Move to the next multiple of 4
        }

        // Check if the first valid leap year is within the given range
        return firstLeap <= maxYear;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the minimum number of days among the given range of months.
     *
     * @param minMonth   the starting month (1-12)
     * @param maxMonth   the ending month (1-12)
     * @param isLeapYear whether the year is a leap year
     * @return the minimum number of days in the given month range
     * @throws IllegalArgumentException if minMonth or maxMonth are out of range, or minMonth > maxMonth
     */
    public static int getMinDaysInMonths(int minMonth, int maxMonth, boolean isLeapYear) {
        if (!isValidMonth(maxMonth) || !isValidMonth(minMonth)) {
            throw new IllegalArgumentException("Months must be between 1 and 12.");
        }
        if (minMonth > maxMonth) {
            throw new IllegalArgumentException("minMonth must be less than or equal to maxMonth.");
        }

        // Количество дней в каждом месяце
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Если год високосный, меняем количество дней в феврале
        if (isLeapYear) {
            daysInMonth[1] = 29;
        }

        // Находим минимальное количество дней в заданном диапазоне месяцев
        int minDays = Integer.MAX_VALUE;
        for (int month = minMonth; month <= maxMonth; month++) {
            minDays = Math.min(minDays, daysInMonth[month - 1]);
        }

        return minDays;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the maximum number of days among the given range of months.
     *
     * @param minMonth   the starting month (1-12)
     * @param maxMonth   the ending month (1-12)
     * @param isLeapYear whether the year is a leap year
     * @return the maximum number of days in the given month range
     * @throws IllegalArgumentException if minMonth or maxMonth are out of range, or minMonth > maxMonth
     */
    public static int getMaxDaysInMonths(int minMonth, int maxMonth, boolean isLeapYear) {
        if (!isValidMonth(minMonth) || !isValidMonth(maxMonth)) {
            throw new IllegalArgumentException("Months must be between 1 and 12.");
        }
        if (minMonth > maxMonth) {
            throw new IllegalArgumentException("minMonth must be less than or equal to maxMonth.");
        }

        // Количество дней в каждом месяце
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Если год високосный, меняем количество дней в феврале
        if (isLeapYear) {
            daysInMonth[1] = 29;
        }

        // Находим максимальное количество дней в заданном диапазоне месяцев
        int maxDays = Integer.MIN_VALUE;
        for (int month = minMonth; month <= maxMonth; month++) {
            maxDays = Math.max(maxDays, daysInMonth[month - 1]);
        }

        return maxDays;
    }

    //-----------------------------------------------------------------------------------------------|
    //                                       INSTANCE METHODS                                        |
    //-----------------------------------------------------------------------------------------------|

    // Inner class for generating random dates
    public final DateGenerator generator = new DateGenerator();

    // Instance variables
    private int month;
    private int day;
    private int year;

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the month.
     *
     * @return the month (1-12)
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month the month to set (1-12)
     * @throws IllegalArgumentException if the month is out of range
     */
    public void setMonth(int month) {
        if (!isValidMonth(month)) {
            throw new IllegalArgumentException("Invalid month: " + month + ". Must be between 1 and 12.");
        }
        this.month = month;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the day.
     *
     * @return the day (1-31, depending on the month and year)
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day.
     *
     * @param day the day to set (valid range depends on the month and year)
     * @throws IllegalArgumentException if the day is out of range for the given month and year
     */
    public void setDay(int day) {
        if (!isValidDay(day, this.month, this.year)) {
            throw new IllegalArgumentException("Invalid day: " + day + ". Must be valid for month " + month + " and year " + year + ".");
        }
        this.day = day;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the year.
     *
     * @return the year (1 or greater)
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year.
     *
     * @param year the year to set (must be positive)
     * @throws IllegalArgumentException if the year is less than 1
     */
    public void setYear(int year) {
        if (!isValidYear(year)) {
            throw new IllegalArgumentException("Invalid year: " + year + ". Must be greater than 0.");
        }
        this.year = year;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the number of days in a given month for a specified year.
     * Accounts for leap years when calculating February days.
     *
     * @return the number of days in the specified month
     */
    public int getDaysInMonth() {        
        // If the month is February and it's a leap year, return 29 days
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        
        // Array representing the number of days in each month
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        return daysInMonth[month - 1]; // Return the correct number of days for the given month
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Determines whether the year of this Date instance is a leap year.
     *
     * @return true if the year of this date is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Displays the date in a readable format, such as "January 1, 2023".
     * Ends with a newline by default.
     */
    public void printDate() {
        printDate("\n"); // Calls the overloaded method with default newline
    }

    /**
     * Displays the date in a readable format, such as "January 1, 2023",
     * and allows specifying a custom ending (e.g., "" for no newline).
     *
     * @param end the ending character(s) to append after the date
     */
    public void printDate(String end) {
        System.out.print(getMonthName() + " " + day + ", " + year + end);
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the name of the month as a string.
     *
     * @return the name of the current month
     */
    public String getMonthName() {
        String[] monthNames = { "January", "February", "March", "April", "May", "June", 
                                "July", "August", "September", "October", "November", "December" };
        return monthNames[month - 1]; // Return the month name
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Updates the day, month, and year attributes after checking validity.
     *
     * @param month the new month to set
     * @param day the new day to set
     * @param year the new year to set
     * @throws IllegalArgumentException if the provided date is invalid
     */
    public void updateDate(int month, int day, int year) {
        if (!isValidDate(month, day, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Returns the day of the week for the current date instance (e.g., "Monday"),
     * using Zeller's Congruence algorithm.
     *
     * @return the name of the day of the week
     */
    public String getDayOfWeek() {
        int m = month;
        int d = day;
        int y = year;

        // Adjust months so that March = 3, ..., December = 12, January = 13, February = 14 (previous year)
        if (m < 3) {
            m += 12;
            y -= 1;
        }

        int K = y % 100; // Year within the century
        int J = y / 100; // Zero-based century

        // Zeller's Congruence formula
        int h = (d + (13 * (m + 1)) / 5 + K + (K / 4) + (J / 4) + (5 * J)) % 7;

        // Days of the week mapping (Zeller's algorithm returns 0 = Saturday, 1 = Sunday, ..., 6 = Friday)
        String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

        return days[h]; // Return the computed day of the week
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Computes the difference in days between this date and another date.
     * Considers leap years.
     *
     * @param otherDate the other Date object to compare with
     * @return the number of days between the two dates
     */
    public int calculateDifference(Date otherDate) {
        return Math.abs(toDays() - otherDate.toDays());
    }

    /**
     * Converts the date to the total number of days since year 1.
     * Uses a mathematical approach for faster calculation.
     *
     * @return the total number of days from year 1 to this date
     */
    public int toDays() {
        // Total days from full years (ignores leap years)
        int days = (year - 1) * 365;

        // Add leap years
        days += (year - 1) / 4;   // Every 4 years is a leap year
        days -= (year - 1) / 100; // Every 100 years is not a leap year
        days += (year - 1) / 400; // Every 400 years is a leap year again

        for (int i = 0; i < month - 1; i++) {
            days += getDaysInMonth(i + 1, year);
        }

        // Add the days of the current month
        days += day;

        return days;
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Generates a list of n random Date objects.
     *
     * @param n the number of random Date objects to generate
     * @return a list of n random Date objects
     */
    public List<Date> generateRandomDates(int n) {
        return generator.generateRandomDates(n);
    }

    //-----------------------------------------------------------------------------------------------
    /**
     * Compares this date with another date for order.
     * The comparison is first by year, then by month, and finally by day.
     *
     * @param other the other date to compare to
     * @return a negative integer, zero, or a positive integer if this date is 
     *         less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date other) {
        // Compare by year first
        if (this.year != other.year) {
            return this.year - other.year;
        }

        // If years are the same, compare by month
        if (this.month != other.month) {
            return this.month - other.month;
        }

        // If both year and month are the same, compare by day
        return this.day - other.day;
    }
}
