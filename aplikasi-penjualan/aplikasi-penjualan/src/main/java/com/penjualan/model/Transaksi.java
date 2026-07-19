package com.penjualan.model;

import java.sql.Timestamp;

public class Transaksi {
    private int idTransaksi;
    private int idPelanggan;
    private Timestamp tanggal;
    private double totalHarga;

    public Transaksi(int idTransaksi, int idPelanggan, Timestamp tanggal, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.idPelanggan = idPelanggan;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
    }

    // Constructor untuk transaksi baru (id & tanggal auto dari database)
    public Transaksi(int idPelanggan, double totalHarga) {
        this.idPelanggan = idPelanggan;
        this.totalHarga = totalHarga;
    }

    public int getIdTransaksi() { 
        return idTransaksi; 
    }
    public int getIdPelanggan() { 
        return idPelanggan; 
    }
    public Timestamp getTanggal() { 
        return tanggal; 
    }
    public double getTotalHarga() { 
        return totalHarga; 
    }

    public void setIdTransaksi(int idTransaksi) { 
        this.idTransaksi = idTransaksi; 
    }
    public void setIdPelanggan(int idPelanggan) { 
        this.idPelanggan = idPelanggan; 
    }
    public void setTanggal(Timestamp tanggal) { 
        this.tanggal = tanggal; 
    }
    public void setTotalHarga(double totalHarga) { 
        this.totalHarga = totalHarga; 
    }
}