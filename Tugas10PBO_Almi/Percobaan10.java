import java.io.*;

public class Percobaan10 {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("test.txt", "r");
            System.out.println("File berhasil dibuka");
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File tidak ditemukan!");
            System.out.println("Detail: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error I/O: " + e.getMessage());
        }
    }
}