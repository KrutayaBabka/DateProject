/**
 * The Date class represents a date with day, month, and year.
 * It allows validation of dates and determination of leap years.
 */
public class Date {
    int day;
    int month;
    int year;

    /**
     * Creates a Date object with the specified day, month, and year.
     *
     * @param day   the day of the month (1 to 31, depending on the month)
     * @param month the month of the year (1 to 12)
     * @param year  the year (1 and above)
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Creates a Date object representing the default start date (January 1, year 1).
     */
    public Date() {
        this.day = 1;
        this.month = 1;
        this.year = 1;
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
}
