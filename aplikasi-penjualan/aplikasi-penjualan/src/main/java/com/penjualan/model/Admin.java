package com.penjualan.model;

public class Admin extends User {

    public Admin(int idUser, String username, String password, String nama, String role) {
        super(idUser, username, password, nama, role);
    }

    public Admin(String username, String password, String nama, String role) {
        super(username, password, nama, role);
    }

    @Override
    public void tampilMenu() {
        System.out.println("=== MENU ADMIN ===");
        System.out.println("1. Kelola User");
        System.out.println("2. Kelola Barang");
        System.out.println("3. Kelola Pelanggan");
        System.out.println("4. Transaksi Penjualan");
        System.out.println("5. Laporan Penjualan");
        System.out.println("6. Logout");
    }
    
    @Override
    public String[] getMenuItems() {
        return new String[]{"Kelola User", "Kelola Barang", "Kelola Pelanggan", "Transaksi Penjualan", "Laporan Penjualan"};
    }
}