import java.util.ArrayList;
import java.util.Scanner;

class DataMahasiswa {
    private String nim;
    private String nama;
    private int nilai;
    private String grade;
    private boolean lulus;

    public DataMahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.grade = hitungGrade(nilai);
        this.lulus = !grade.equals("D") && !grade.equals("E");
    }

    private String hitungGrade(int nilai) {
        if (nilai >= 80 && nilai <= 100) {
            return "A";
        } else if (nilai >= 70 && nilai <= 79) {
            return "B";
        } else if (nilai >= 60 && nilai <= 69) {
            return "C";
        } else if (nilai >= 50 && nilai <= 59) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public int getNilai() { return nilai; }
    public String getGrade() { return grade; }
    public boolean isLulus() { return lulus; }
}

public class NilaiMahasiswa { // disesuaikan dengan nama file
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<DataMahasiswa> daftarMahasiswa = new ArrayList<>();

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\n--- Data Mahasiswa ke-" + (i + 1) + " ---");

            System.out.print("NIM   : ");
            String nim = scanner.nextLine();

            System.out.print("Nama  : ");
            String nama = scanner.nextLine();

            int nilai;
            while (true) {
                System.out.print("Nilai : ");
                nilai = scanner.nextInt();
                scanner.nextLine();

                if (nilai >= 0 && nilai <= 100) {
                    break;
                } else {
                    System.out.println("Input nilai anda salah! Nilai harus antara 0-100.");
                }
            }

            daftarMahasiswa.add(new DataMahasiswa(nim, nama, nilai));
        }

        // Tampilkan hasil
        System.out.println("\n");
        for (DataMahasiswa mhs : daftarMahasiswa) {
            for (int i = 0; i < 50; i++) System.out.print("=");
            System.out.println();

            System.out.println("NIM   : " + mhs.getNim());
            System.out.println("Nama  : " + mhs.getNama());
            System.out.println("Nilai : " + mhs.getNilai());
            System.out.println("Grade : " + mhs.getGrade());
        }
        for (int i = 0; i < 50; i++) System.out.print("=");
        System.out.println();

        // Hitung statistik
        int totalMahasiswa = daftarMahasiswa.size();
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeE = 0;
        int totalNilai = 0;

        ArrayList<String> namaLulus = new ArrayList<>();
        ArrayList<String> namaTidakLulus = new ArrayList<>();
        ArrayList<String> namaNilaiA = new ArrayList<>();
        ArrayList<String> namaNilaiB = new ArrayList<>();
        ArrayList<String> namaNilaiC = new ArrayList<>();
        ArrayList<String> namaNilaiD = new ArrayList<>();
        ArrayList<String> namaNilaiE = new ArrayList<>();

        for (DataMahasiswa mhs : daftarMahasiswa) {
            totalNilai += mhs.getNilai();

            if (mhs.isLulus()) {
                jumlahLulus++;
                namaLulus.add(mhs.getNama());
            } else {
                jumlahTidakLulus++;
                namaTidakLulus.add(mhs.getNama());
            }

            switch (mhs.getGrade()) {
                case "A": gradeA++; namaNilaiA.add(mhs.getNama()); break;
                case "B": gradeB++; namaNilaiB.add(mhs.getNama()); break;
                case "C": gradeC++; namaNilaiC.add(mhs.getNama()); break;
                case "D": gradeD++; namaNilaiD.add(mhs.getNama()); break;
                case "E": gradeE++; namaNilaiE.add(mhs.getNama()); break;
            }
        }

        double rataRata = (double) totalNilai / totalMahasiswa;

        // Tampilkan ringkasan
        System.out.println("\nJumlah Mahasiswa : " + totalMahasiswa);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus + " yaitu " + String.join(", ", namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + " yaitu " + String.join(", ", namaTidakLulus));

        if (gradeA > 0) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA + " yaitu " + String.join(", ", namaNilaiA));
        if (gradeB > 0) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB + " yaitu " + String.join(", ", namaNilaiB));
        if (gradeC > 0) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeC + " yaitu " + String.join(", ", namaNilaiC));
        if (gradeD > 0) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD + " yaitu " + String.join(", ", namaNilaiD));
        if (gradeE > 0) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeE + " yaitu " + String.join(", ", namaNilaiE));

        // Rata-rata
        StringBuilder nilaiStr = new StringBuilder();
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            nilaiStr.append(daftarMahasiswa.get(i).getNilai());
            if (i < daftarMahasiswa.size() - 1) nilaiStr.append("+");
        }

        System.out.printf("Rata-rata nilai mahasiswa adalah : %s / %d = %.2f%n",
                nilaiStr.toString(), totalMahasiswa, rataRata);

        scanner.close();
    }
}