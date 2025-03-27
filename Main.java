import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //----------------------------------------------------------------------------------------
        // Testing static is valid date methods
        System.out.println("January 27, 2005 is valid date?: " + Date.isValidDate(1, 27, 2005));
        System.out.println("February 30, 2020 is valid date?: " + Date.isValidDate(2, 30, 2020));
        System.out.println("February is valid month?: " + Date.isValidMonth(2));
        System.err.println("13 is valid month?: " + Date.isValidMonth(13));
        System.out.println("February 29, 2020 is valid day?: " + Date.isValidDay(2, 29, 2020));
        System.out.println("February 29, 2021 is valid day?: " + Date.isValidDay(2, 29, 2021));
        System.out.println("2005 is valid year?: " + Date.isValidYear(2005));
        System.out.println("0 is valid year?: " + Date.isValidYear(0));
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Testing static is leap year method
        System.out.println("2020 is leap year?: " + Date.isLeapYear(2020));
        System.out.println("2021 is leap year?: " + Date.isLeapYear(2021));
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Testing static print date method
        System.out.print("Print valid date: "); Date.printDate(1, 27, 2005);
        try {
            Date.printDate(2, 30, 2005);
        } catch (Exception e) {
            System.out.println("Print invalid date February 30, 2005: " + e.getMessage()); 
        }
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Constructor testing
        Date date = new Date(); // Default constructor
        System.out.print("Default date: "); date.printDate();

        date = new Date(1, 27, 2005); // Parameterized constructor 
        System.out.print("Parameterized date: "); date.printDate();
        try {
            date = new Date(2, 30, 2020); // Invalid date 
        } catch (Exception e) {
            System.out.println("Parameterized invalid date February 30, 2020: " + e.getMessage());
        }
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Update date testing
        date.updateDate(3, 26, 2025); System.out.print("Updated date: "); date.printDate();
        try {
            date.updateDate(2, 30, 2000); 
        } catch (Exception e) {
            System.out.println("Try to update date to February 30, 2020: " + e.getMessage());
        }
        System.out.println();
        
        //----------------------------------------------------------------------------------------
        // Get day of the week testing
        System.out.println("Day of the week of January 27, 2005: " + Date.getDayOfWeek(1, 27, 2005)); // Static method
        System.out.print("Day of the "); date.printDate(""); System.out.println(" is " + date.getDayOfWeek()); // Instance method
        System.out.println();

        //----------------------------------------------------------------------------------------
        // To days testing
        System.out.println("To days of January 27, 2005: " + Date.toDays(1, 27, 2005)); // Static method
        System.out.print("To days: "); date.printDate(""); System.out.println(" is " + date.toDays()); // Instance method
        System.out.println();

        //----------------------------------------------------------------------------------------
        // calculate difference testing
        // Static method
        System.out.println("Difference between January 1, 2025 and March 26, 2025: " + Date.calculateDifference(1, 1, 2025, 3, 26, 2025));
        
        Date date2 = new Date(1, 1, 2025);
        System.out.print("Difference between "); date.printDate(""); System.out.print(" and "); date2.printDate(" is "); 
        System.out.println(date.calculateDifference(date2)); // Instance method
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Get days in month testing
        System.out.println("Days in January 2020: " + Date.getDaysInMonth(1, 2020));
        System.out.println("Days in " + date.getMonthName() + ' ' + date.getYear() + ": " + date.getDaysInMonth());
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Get month name testing
        date.printDate(" "); System.out.println("month is " + date.getMonthName());
        System.out.println("Month 1 is " + Date.getMonthName(1));
        System.out.println();

        // Testing is there leap year in range
        System.out.println("Is there leap year between 2000 and 2005?: " + Date.hasLeapYearInRange(2000, 2005));        
        System.out.println("Is there leap year between 1900 and 1903?: " + Date.hasLeapYearInRange(1900, 1903));
        System.out.println();

        // Testing get min days in months
        System.out.println("Min days in months between January and March not in a leap year: " + Date.getMinDaysInMonths(1, 3, false));
        System.out.println("Min days in months between January and March in a leap year: " + Date.getMinDaysInMonths(1, 3, true));
        System.out.println();

        // Testing get max days in months
        System.out.println("Max days in months between January and March not in a leap year: " + Date.getMaxDaysInMonths(1, 3, false));
        System.out.println("Max days in months between January and March in a leap year: " + Date.getMaxDaysInMonths(1, 3, true));
        System.out.println();

        //----------------------------------------------------------------------------------------
        // Testing generate random dates and sort them
        // Set the range of random dates
        date.generator.setMinYear(2000);
        date.generator.setMaxYear(2025);
        date.generator.setMinMonth(1);
        date.generator.setMaxMonth(12);
        date.generator.setMinDay(1);
        date.generator.setMaxDay(31);

        // Generate 10 random dates
        List<Date> randomDates = date.generateRandomDates(10);

        // Print unsorted list of random dates
        System.out.println("Unsorted Dates:");
        for(Date dt : randomDates) {
            dt.printDate();
        }

        System.out.println();

        Collections.sort(randomDates);

        System.out.println("Sorted Dates:");
        // Print sorted list of random dates
        for(Date dt : randomDates) {
            dt.printDate();
        }
    }
}
