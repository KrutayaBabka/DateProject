public class Main {
    public static void main(String[] args) {
        // Static methods testing
        System.out.println("27 January, 2005 is valid Date?: " + Date.isValidDate(27, 1, 2005));
        System.out.println("30 February, 2020 is valid Date?: " + Date.isValidDate(30, 2, 2020));
        System.out.println();

        System.out.println("2020 is leap year?: " + Date.isLeapYear(2020));
        System.out.println("2021 is leap year?: " + Date.isLeapYear(2021));
        System.out.println();

        System.out.print("Print valid date: "); Date.printDate(27, 1, 2005);
        try {
            Date.printDate(30, 2, 2005);
        } catch (Exception e) {
            System.out.println("Print invalid date 30 February, 2005: " + e.getMessage()); 
        }
        System.out.println();

        // Constructor testing
        Date date = new Date(); // Default constructor
        System.out.print("Default date: "); date.printDate();

        date = new Date(27, 1, 2005); // Parameterized constructor 
        System.out.print("Parameterized date: "); date.printDate();
        try {
            date = new Date(30, 2, 2020); // Invalid date 
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": 30 February, 2020");
        }
        System.out.println();

        // Update date testing
        date.updateDate(26, 3, 2025); System.out.print("Updated date: "); date.printDate();
        try {
            date.updateDate(30, 2, 2000); 
        } catch (Exception e) {
            System.out.println("Try to update date to 30 February, 2020: " + e.getMessage());
        }
        System.out.println();
        
        System.out.print("Day of the "); date.printDate(""); System.out.println(" is " + date.getDayOfWeek());
    }
}
