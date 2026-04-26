import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Laptop laptop;

        System.out.println("Pilih Laptop:");
        System.out.println("1. Toshiba");
        System.out.println("2. MacBook");
        System.out.print("Pilihan: ");
        String pilih = input.nextLine();

        if (pilih.equals("1")) {
            laptop = new Toshiba();
        } else {
            laptop = new MacBook();
        }

        while (true) {
            System.out.println("\nCommand: ON | OFF | UP | DOWN | EXIT");
            System.out.print("Input: ");
            String cmd = input.nextLine().toUpperCase();

            switch (cmd) {
                case "ON":
                    laptop.turnOn();
                    break;
                case "OFF":
                    laptop.turnOff();
                    break;
                case "UP":
                    laptop.volumeUp();
                    break;
                case "DOWN":
                    laptop.volumeDown();
                    break;
                case "EXIT":
                    System.out.println("Selesai.");
                    return;
                default:
                    System.out.println("Salah input!");
            }
        }
    }
}