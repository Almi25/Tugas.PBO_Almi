package com.lostfound.app;

import com.lostfound.dao.BarangDAO;
import com.lostfound.dao.KlaimDAO;
import com.lostfound.dao.PelaporDAO;
import com.lostfound.exception.BarangTidakDitemukanException;
import com.lostfound.exception.PelaporTidakValidException;
import com.lostfound.exception.StatusKlaimTidakValidException;
import com.lostfound.model.Barang;
import com.lostfound.model.Klaim;
import com.lostfound.model.Pelapor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PelaporDAO pelaporDAO = new PelaporDAO();
    private static final BarangDAO barangDAO = new BarangDAO();
    private static final KlaimDAO klaimDAO = new KlaimDAO();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n===== SISTEM INFORMASI BARANG HILANG DAN DITEMUKAN =====");
            System.out.println("1. Menu Pelapor");
            System.out.println("2. Menu Barang");
            System.out.println("3. Menu Klaim");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1 -> menuPelapor();
                case 2 -> menuBarang();
                case 3 -> menuKlaim();
                case 4 -> System.out.println("Terima kasih. Program selesai.");
                default -> System.out.println(">> Pilihan tidak valid.");
            }
        } while (pilihan != 4);

        scanner.close();
    }

    // ================= MENU PELAPOR =================
    private static void menuPelapor() {
        int pilihan;
        do {
            System.out.println("\n--- Menu Pelapor ---");
            System.out.println("1. Tambah Pelapor");
            System.out.println("2. Lihat Daftar Pelapor");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1 -> tambahPelapor();
                case 2 -> lihatDaftarPelapor();
                case 0 -> System.out.println("Kembali ke menu utama.");
                default -> System.out.println(">> Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void tambahPelapor() {
        try {
            System.out.print("Nama         : ");
            String nama = scanner.nextLine();
            System.out.print("Tipe (Mahasiswa/Staf): ");
            String tipe = scanner.nextLine();
            System.out.print("NIM/NIP      : ");
            String identitas = scanner.nextLine();
            System.out.print("Kontak       : ");
            String kontak = scanner.nextLine();

            pelaporDAO.tambahPelapor(nama, tipe, identitas, kontak);

        } catch (PelaporTidakValidException e) {
            System.out.println(">> Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(">> Input tidak valid: " + e.getMessage());
        }
    }

    private static void lihatDaftarPelapor() {
        List<Pelapor> daftar = pelaporDAO.getAllPelapor();
        System.out.println("\n--- Daftar Pelapor ---");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data pelapor.");
        } else {
            for (Pelapor p : daftar) {
                System.out.println(p); // memanggil toString() -> polimorfisme lewat getTipe()/getIdentitas()
            }
        }
    }

    // ================= MENU BARANG =================
    private static void menuBarang() {
        int pilihan;
        do {
            System.out.println("\n--- Menu Barang ---");
            System.out.println("1. Lapor Barang Hilang");
            System.out.println("2. Lapor Barang Ditemukan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1 -> laporBarangHilang();
                case 2 -> laporBarangDitemukan();
                case 0 -> System.out.println("Kembali ke menu utama.");
                default -> System.out.println(">> Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void laporBarangHilang() {
        System.out.print("Nama Barang : ");
        String nama = scanner.nextLine();
        System.out.print("Lokasi      : ");
        String lokasi = scanner.nextLine();
        System.out.print("Ciri-ciri   : ");
        String ciriCiri = scanner.nextLine();

        barangDAO.laporBarangHilang(nama, lokasi, LocalDate.now(), ciriCiri);
    }

    private static void laporBarangDitemukan() {
        System.out.print("Nama Barang       : ");
        String nama = scanner.nextLine();
        System.out.print("Lokasi Ditemukan  : ");
        String lokasi = scanner.nextLine();
        System.out.print("Tempat Penyimpanan: ");
        String tempatSimpan = scanner.nextLine();

        barangDAO.laporBarangDitemukan(nama, lokasi, LocalDate.now(), tempatSimpan);
    }

    // ================= MENU KLAIM =================
    private static void menuKlaim() {
        int pilihan;
        do {
            System.out.println("\n--- Menu Klaim ---");
            System.out.println("1. Proses Klaim Barang");
            System.out.println("2. Lihat Riwayat Klaim");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1 -> prosesKlaimBarang();
                case 2 -> lihatRiwayatKlaim();
                case 0 -> System.out.println("Kembali ke menu utama.");
                default -> System.out.println(">> Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void prosesKlaimBarang() {
        System.out.println("\n--- Daftar Barang Belum Diklaim ---");
        List<Barang> daftarBarang = barangDAO.getBarangBelumDiklaim();
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang yang bisa diklaim saat ini.");
            return;
        }
        for (Barang b : daftarBarang) {
            System.out.println("ID " + b.getIdBarang() + " -> " + b.getInfo()); // polimorfisme lewat getInfo()
        }

        try {
            System.out.print("Masukkan ID Barang yang diklaim: ");
            int idBarang = bacaInt();
            System.out.print("Masukkan ID Pelapor            : ");
            int idPelapor = bacaInt();

            int lamaHari = barangDAO.getLamaHariDilaporkan(idBarang);
            System.out.println(">> Barang sudah dilaporkan sejak " + lamaHari + " hari yang lalu.");

            klaimDAO.prosesKlaim(idBarang, idPelapor);

        } catch (BarangTidakDitemukanException | StatusKlaimTidakValidException e) {
            System.out.println(">> Error: " + e.getMessage());
        }
    }

    private static void lihatRiwayatKlaim() {
        List<Klaim> daftar = klaimDAO.getRiwayatKlaim();
        System.out.println("\n--- Riwayat Klaim ---");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada riwayat klaim.");
        } else {
            for (Klaim k : daftar) {
                System.out.println(k);
            }
        }
    }

    // ================= UTIL INPUT =================
    private static int bacaInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print(">> Masukkan angka yang valid: ");
            }
        }
    }
}
