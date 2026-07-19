package com.penjualan.model;

public class Kasir extends User {

    public Kasir(int idUser, String username, String password, String nama, String role) {
        super(idUser, username, password, nama, role);
    }

    public Kasir(String username, String password, String nama, String role) {
        super(username, password, nama, role);
    }

    @Override
    public void tampilMenu() {
        System.out.println("=== MENU KASIR ===");
        System.out.println("1. Kelola Barang (Lihat & Cari)");
        System.out.println("2. Kelola Pelanggan");
        System.out.println("3. Transaksi Penjualan");
        System.out.println("4. Logout");
    }

    @Override
    public String[] getMenuItems() {
        return new String[]{"Kelola Barang", "Kelola Pelanggan", "Transaksi Penjualan"};
}
}