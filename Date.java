/**
 * The Date class represents a date with day, month, and year.
 * It allows validation of dates and determination of leap years.
 */
public class Date {
    int day;
    int month;
    int year;

    /**
     * Creates a Date object representing the default start date (January 1, year 1).
     */
    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 1;
    }

    /**
     * Creates a Date object with the specified day, month, and year.
     *
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param month the month of the year (1 to 12)
     * @param year  the year (1 and above)
     * @throws IllegalArgumentException if the provided date is invalid
     */
    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if the given date is valid.
     *
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param month the month of the year (1 to 12)
     * @param year  the year (1 and above)
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(int day, int month, int year) {
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
     * @param day   the day of the month
     * @param month the month of the year (1 to 12)
     * @param year  the year
     */
    public static void printDate(int day, int month, int year) {
        printDate(day, month, year, "\n"); // Calls the overloaded method with default newline
    }

    /**
     * Displays the given date in a readable format, such as "January 1, 2023",
     * and allows specifying a custom ending (e.g., "" for no newline).
     *
     * @param day   the day of the month
     * @param month the month of the year (1 to 12)
     * @param year  the year
     * @param end   the ending character(s) to append after the date
     */
    public static void printDate(int day, int month, int year, String end) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date to print");
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
     * @param day the new day to set
     * @param month the new month to set
     * @param year the new year to set
     * @throws IllegalArgumentException if the provided date is invalid
     */
    public void updateDate(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the day of the week for the current date instance (e.g., "Monday"),
     * using Zeller's Congruence algorithm.
     *
     * @return the name of the day of the week
     */
    public String getDayOfWeek() {
        int d = day;
        int m = month;
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
     * @param day the new day to set
     * @param month the new month to set
     * @param year the new year to set
     * @throws IllegalArgumentException if the provided date is invalid
     * @return the name of the day of the week
     */
    public static String getDayOfWeek(int day, int month, int year) {
        if(!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + month + " " + day + ", " + year);
        }

        int d = day;
        int m = month;
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
}
