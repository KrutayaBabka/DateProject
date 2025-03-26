public class Main {
    public static void main(String[] args) {
        // Testing static is valid date method
        System.out.println("January 27, 2005 is valid Date?: " + Date.isValidDate(1, 27, 2005));
        System.out.println("February 30, 2020 is valid Date?: " + Date.isValidDate(2, 30, 2020));
        System.out.println();

        // Testing static is leap year method
        System.out.println("2020 is leap year?: " + Date.isLeapYear(2020));
        System.out.println("2021 is leap year?: " + Date.isLeapYear(2021));
        System.out.println();

        // Testing static print date method
        System.out.print("Print valid date: "); Date.printDate(1, 27, 2005);
        try {
            Date.printDate(2, 30, 2005);
        } catch (Exception e) {
            System.out.println("Print invalid date February 30, 2005: " + e.getMessage()); 
        }
        System.out.println();

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

        // Update date testing
        date.updateDate(3, 26, 2025); System.out.print("Updated date: "); date.printDate();
        try {
            date.updateDate(2, 30, 2000); 
        } catch (Exception e) {
            System.out.println("Try to update date to February 30, 2020: " + e.getMessage());
        }
        System.out.println();
        
        // Get day of the week testing
        System.out.println("Day of the week of January 27, 2005: " + Date.getDayOfWeek(1, 27, 2005)); // Static method
        System.out.print("Day of the "); date.printDate(""); System.out.println(" is " + date.getDayOfWeek()); // Instance method
        System.out.println();

        // To days testing
        System.out.println("To days of January 27, 2005: " + Date.toDays(1, 27, 2005)); // Static method
        System.out.print("To days: "); date.printDate(""); System.out.println(" is " + date.toDays()); // Instance method
        System.out.println();


        // calculate difference testing
        // Static method
        System.out.println("Difference between January 1, 2025 and March 26, 2025: " + Date.calculateDifference(1, 1, 2025, 3, 26, 2025));
        
        Date date2 = new Date(1, 1, 2025);
        System.out.print("Difference between "); date.printDate(""); System.out.print(" and "); date2.printDate(" is "); 
        System.out.println(date.calculateDifference(date2)); // Instance method
        System.out.println();

        
    }
}
