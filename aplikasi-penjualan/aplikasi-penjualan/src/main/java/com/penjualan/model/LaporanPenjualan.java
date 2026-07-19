package com.penjualan.model;

import java.sql.Timestamp;

public class LaporanPenjualan {
    private int idTransaksi;
    private Timestamp tanggal;
    private String namaPelanggan;
    private String namaBarang;
    private int jumlah;
    private double subtotal;
    private double totalHarga;

    public LaporanPenjualan(int idTransaksi, Timestamp tanggal, String namaPelanggan, String namaBarang, int jumlah, double subtotal, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.namaPelanggan = namaPelanggan;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
        this.totalHarga = totalHarga;
    }

    public int getIdTransaksi() { 
        return idTransaksi; 
    }
    public Timestamp getTanggal() { 
        return tanggal; 
    }
    public String getNamaPelanggan() { 
        return namaPelanggan; 
    }
    public String getNamaBarang() { 
        return namaBarang;
    }
    public int getJumlah() { 
        return jumlah; 
    }
    public double getSubtotal() { 
        return subtotal; 
    }
        
    public double getTotalHarga() { 
        return totalHarga; 
    }
}