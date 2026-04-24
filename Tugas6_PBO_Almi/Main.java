package com.alat.elektronik;

import java.util.Scanner;

/**
 * Class Main
 * Program utama untuk mengontrol Laptop dengan input dari user menggunakan Scanner
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Laptop selectedLaptop = null;
        boolean running = true;

        // Membuat objek Toshiba dan MacBook
        Toshiba toshiba = new Toshiba("Satellite L50");
        MacBook macBook = new MacBook("Apple M1 Pro");

        System.out.println("========================================");
        System.out.println("     LAPTOP CONTROL SYSTEM");
        System.out.println("========================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Select Toshiba");
            System.out.println("2. Select MacBook");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    selectedLaptop = toshiba;
                    System.out.println("\n>>> Toshiba selected <<<");
                    controlLaptop(selectedLaptop, scanner);
                    break;

                case "2":
                    selectedLaptop = macBook;
                    System.out.println("\n>>> MacBook selected <<<");
                    controlLaptop(selectedLaptop, scanner);
                    break;

                case "3":
                    running = false;
                    System.out.println("\nThank you for using Laptop Control System!");
                    break;

                default:
                    System.out.println("\nInvalid input! Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Method untuk mengontrol laptop yang dipilih
     * 
     * @param laptop Objek laptop yang akan dikontrol
     * @param scanner Scanner untuk menerima input user
     */
    public static void controlLaptop(Laptop laptop, Scanner scanner) {
        boolean laptopControl = true;

        while (laptopControl) {
            System.out.println("\n--- LAPTOP CONTROL MENU ---");
            System.out.println("Commands:");
            System.out.println("  ON   - Turn on the laptop");
            System.out.println("  OFF  - Turn off the laptop");
            System.out.println("  UP   - Increase volume");
            System.out.println("  DOWN - Decrease volume");
            System.out.println("  STATUS - Show laptop status");
            System.out.println("  BACK - Back to main menu");
            System.out.print("Enter command: ");

            String command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "ON":
                    laptop.turnOn();
                    break;

                case "OFF":
                    laptop.turnOff();
                    break;

                case "UP":
                    laptop.increaseVolume();
                    break;

                case "DOWN":
                    laptop.decreaseVolume();
                    break;

                case "STATUS":
                    laptop.displayStatus();
                    break;

                case "BACK":
                    laptopControl = false;
                    System.out.println("\nReturning to main menu...");
                    break;

                default:
                    System.out.println("Invalid command! Please try again.");
            }
        }
    }
}