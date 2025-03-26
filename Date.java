/**
 * The Date class represents a date with day, month, and year.
 * It allows validation of dates and determination of leap years.
 */
public class Date {
    int month;
    int day;
    int year;

    /**
     * Creates a Date object representing the default start date (January 1, year 1).
     */
    public Date() {
        this.month = 1;
        this.day = 1;
        this.year = 1;
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

    /**
     * Checks if the given date is valid.
     *
     * @param month the month of the year (1 to 12)
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param year  the year (1 and above)
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(int month, int day, int year) {
        if (month < 1 || month > 12 || day < 1 || year < 1) {
            return false;
        }

        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Adjust for leap years in February
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return day <= daysInMonth[month - 1];
    }

    /**
     * Determines whether the year of this Date instance is a leap year.
     *
     * @return true if the year of this date is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        return isLeapYear(this.year); // Calls the static method
    }

    /**
     * Determines whether the given year is a leap year.
     * A year is a leap year if it is divisible by 4 but not by 100,
     * except when it is also divisible by 400 (in which case it is a leap year).
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

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
        String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        System.out.print(monthNames[month - 1] + " " + day + ", " + year + end);
    }

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

        String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        System.out.print(monthNames[month - 1] + " " + day + ", " + year + end);
    }


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

        // Add days in the previous months of the current year
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (isLeapYear(year)) {
            daysInMonth[1] = 29; // Adjust February days for leap year
        }

        for (int i = 0; i < month - 1; i++) {
            days += daysInMonth[i];
        }

        // Add the days of the current month
        days += day;

        return days;
    }

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

        // Add days in the previous months of the current year
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (isLeapYear(year)) {
            daysInMonth[1] = 29; // Adjust February days for leap year
        }

        for (int i = 0; i < month - 1; i++) {
            days += daysInMonth[i];
        }

        // Add the days of the current month
        days += day;

        return days;
    }

}
