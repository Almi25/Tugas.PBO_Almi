import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        String line;
        String csvSplitBy = ",";

        int jumlahBaris = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                for (String item : data) {
                    System.out.print(item + "\t");
                }
                System.out.println();

                jumlahBaris++;
            }

            System.out.println("\nJumlah baris dalam file: " + jumlahBaris);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}