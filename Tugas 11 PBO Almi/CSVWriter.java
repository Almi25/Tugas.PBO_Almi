import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.csv", true))) {

            System.out.print("Masukkan NIM : ");
            String nim = input.nextLine();

            System.out.print("Masukkan Nama : ");
            String nama = input.nextLine();

            System.out.print("Masukkan Umur : ");
            String umur = input.nextLine();

            System.out.print("Masukkan Prodi : ");
            String prodi = input.nextLine();

            String data = nim + "," + nama + "," + umur + "," + prodi;

            bw.write(data);
            bw.newLine();

            System.out.println("Data berhasil ditambahkan ke file CSV.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        input.close();
    }
}