import java.util.ArrayList;
import java.util.Scanner;

class Room {
    String type;
    boolean isAvailable;
    double price;
    
    Room(String type, double price) {
        this.type = type;
        this.isAvailable = true;
        this.price = price;
    }
}

class Reservation {
    String guestName;
    Room room;
    String checkInDate;
    String checkOutDate;
    double amountPaid;

    Reservation(String guestName, Room room, String checkInDate, String checkOutDate, double amountPaid) {
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amountPaid = amountPaid;
    }

    void displayBookingDetails() {
        System.out.println("Booking Details for " + guestName);
        System.out.println("Room Type: " + room.type);
        System.out.println("Price per Night: $" + room.price);
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Amount Paid: $" + amountPaid);
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample rooms
        rooms.add(new Room("Single", 100.0));
        rooms.add(new Room("Double", 150.0));
        rooms.add(new Room("Suite", 250.0));

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Search for available rooms
    public static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room.type + " - $" + room.price);
            }
        }
    }

    // Make a reservation
    public static void makeReservation() {
        System.out.print("\nEnter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.nextLine();

        System.out.println("\nAvailable Rooms:");
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).isAvailable) {
                System.out.println((i + 1) + ". " + rooms.get(i).type + " - $" + rooms.get(i).price);
            }
        }
        System.out.print("Select room by number: ");
        int roomChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Room selectedRoom = rooms.get(roomChoice - 1);
        selectedRoom.isAvailable = false; // Room is now booked

        System.out.print("Enter payment amount: $");
        double payment = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Create reservation
        Reservation reservation = new Reservation(guestName, selectedRoom, checkInDate, checkOutDate, payment);
        reservations.add(reservation);
        System.out.println("\nReservation made successfully!");
    }

    // View booking details
    public static void viewBookingDetails() {
        System.out.print("\nEnter guest name to view booking details: ");
        String guestName = scanner.nextLine();

        for (Reservation reservation : reservations) {
            if (reservation.guestName.equalsIgnoreCase(guestName)) {
                reservation.displayBookingDetails();
                return;
            }
        }
        System.out.println("No booking found for " + guestName);
    }
}
