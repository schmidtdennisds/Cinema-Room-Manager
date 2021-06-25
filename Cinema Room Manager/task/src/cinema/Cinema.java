package cinema;

import java.util.Scanner;

public class Cinema {

    private static int boughtTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;
    private static int rows = 0;
    private static int seats = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        calculateTotalIncome();
        String[][] array = createArray();
        printOptions();
        int option = scanner.nextInt();
        while (option != 0) {
            switch(option) {
                case 1:
                    showSeats(array);
                    break;
                case 2:
                    buyTicket(scanner, array);
                    break;
                case 3:
                    printStatistics();
                    break;
                default:
                    break;
            }
            printOptions();
            option = scanner.nextInt();
        }
    }

    private static void calculateTotalIncome() {
        if (rows * seats <= 60) {
            totalIncome = 10 * rows * seats;
        } else {
            totalIncome = (rows / 2) * seats * 10 + (rows / 2 + 1) * seats * 8;
        }
    }

    private static void printStatistics() {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d\n", boughtTickets);
        System.out.printf("Percentage: %.2f%%\n", (float)boughtTickets * 100 / (rows * seats));
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income : $%d\n", totalIncome);
    }

    private static void buyTicket(Scanner scanner, String[][] array) {
        int[] ticket = askAndCheckTicketInput(scanner, array);
        int rowNumber = ticket[0];
        int seatNumber = ticket[1];

        array[rowNumber][seatNumber] = "B";
        int price;
        if (rows * seats <= 60) {
            price = 10;
        } else {
            if (rowNumber <= rows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        System.out.printf("\nTicket price: $%d", price);
        currentIncome += price;
        boughtTickets++;
        System.out.println();
    }

    private static int[] askAndCheckTicketInput(Scanner scanner, String[][] array) {
        boolean inputIsRight = false;
        int rowNumber = 0;
        int seatNumber = 0;
        while (!inputIsRight) {
            System.out.println();
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber < 0 || rowNumber > rows || seatNumber < 0 || seatNumber > seats) {
                System.out.println("\nWrong input!");
            } else if (array[rowNumber][seatNumber].equals("B")) {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                inputIsRight = true;
            }
        }
        return new int[]{rowNumber, seatNumber};
    }

    private static void showSeats(String[][] array) {
        System.out.println();
        System.out.println("Cinema:");
        printArray(array, rows, seats);
        System.out.println();
    }

    private static String[][] createArray() {
        String[][] array = new String[rows + 1][seats + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0 && j == 0) {
                    array[i][j] = " ";
                } else if (i == 0) {
                    array[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    array[i][j] = String.valueOf(i);
                } else {
                    array[i][j] = "S";
                }
            }
        }
        return array;
    }

    public static void printArray(String[][] array, int rows, int seats) {
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printOptions() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}