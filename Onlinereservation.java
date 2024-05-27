import java.util.Scanner;

// Define a class for the Reservation System
class ReservationSystem {
    // Define variables to store user details
    private String name;
    private String trainNumber;
    private String classType;
    private String journeyDate;
    private String from;
    private String to;
    private String pnrNumber;

    // Method to take user input for reservation
    public void takeReservationInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scanner.nextLine();
        // Similar inputs for other fields like train number, class type, etc.
        // Insert the data into the central database
        System.out.println("Reservation successful!");
    }

    // Method to cancel reservation
    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your PNR number: ");
        pnrNumber = scanner.nextLine();
        // Fetch reservation details from the database using PNR number
        // Display reservation details
        System.out.println("Do you want to cancel this reservation? (Yes/No)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Yes")) {
            // Cancel the reservation
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("Reservation not cancelled.");
        }
    }
}

// Define a class for the main application
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();

        // Login
        System.out.println("Login");
        // Code for login functionality

        // Main menu
        System.out.println("1. Make a reservation");
        System.out.println("2. Cancel a reservation");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                reservationSystem.takeReservationInput();
                break;
            case 2:
                reservationSystem.cancelReservation();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
