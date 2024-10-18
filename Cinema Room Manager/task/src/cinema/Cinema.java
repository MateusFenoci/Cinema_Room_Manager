package cinema;

import java.util.Scanner;

public class Cinema {
    public static int totalTicketselled = 0;
    public static int currentIncome = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int numberRows = sc.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int numberSeats = sc.nextInt();

        char[][] cinema = new char[numberRows][numberSeats];

        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberSeats; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showSeats(cinema, numberRows, numberSeats);
                    break;
                case 2:
                    buyTicket(sc, cinema, numberRows, numberSeats);
                    break;
                case 3:
                    showStatistics(numberRows, numberSeats);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 0.");
                    break;
            }
        }
    }

    public static void showSeats(char[][] cinema, int numberRows, int numberSeats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int x = 1; x <= numberSeats; x++) {
            System.out.print(" " + x);
        }
        System.out.println();
        for (int i = 0; i < numberRows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < numberSeats; j++) {
                System.out.print(" " + cinema[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyTicket(Scanner sc, char[][] cinema, int numberRows, int numberSeats) {
        while (true) {
            System.out.println("Enter a row number: ");
            int rowNumber = sc.nextInt();

            System.out.println("Enter a seat number in that row: ");
            int seatNumber = sc.nextInt();

            if (rowNumber < 1 || rowNumber > numberRows || seatNumber < 1 || seatNumber > numberSeats) {
                System.out.println("Wrong input!");
                continue;
            }

            int ticketPrice;
            if (numberRows * numberSeats <= 60) {
                ticketPrice = 10;
            } else {
                int frontRows = numberRows / 2;
                ticketPrice = rowNumber <= frontRows ? 10 : 8;
            }

            if (cinema[rowNumber - 1][seatNumber - 1] == 'S') {
                cinema[rowNumber - 1][seatNumber - 1] = 'B';
                totalTicketselled++;
                currentIncome += ticketPrice;
                System.out.println("Ticket price: $" + ticketPrice);
                break;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }
    }

    public static void showStatistics(int numberRows, int numberSeats) {
        System.out.println("Number of purchased tickets: " + totalTicketselled);

        int totalSeats = numberSeats * numberRows;

        double percentageSelled = ((double) totalTicketselled / totalSeats) * 100;
        System.out.printf("Percentage: %.2f%%\n", percentageSelled);

        System.out.println("Current Income: $" + currentIncome);

        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            int frontRows = numberRows / 2;
            int backRows = numberRows - frontRows;

            int frontIncome = frontRows * numberSeats * 10;
            int backIncome = backRows * numberSeats * 8;

            totalIncome = frontIncome + backIncome;
        }

        System.out.println("Total Income: $" + totalIncome);
    }
}
