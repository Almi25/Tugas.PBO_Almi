package com.penjualan.model;

public class Barang {
    private int idBarang;
    private String namaBarang;
    private String kategori;
    private double harga;
    private int stok;

    public Barang(int idBarang, String namaBarang, String kategori, double harga, int stok) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public Barang(String namaBarang, String kategori, double harga, int stok) {
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdBarang() { 
        return idBarang; 
    }
    public String getNamaBarang() { 
        return namaBarang; 
    }
    public String getKategori() {
        return kategori; 
    }
    public double getHarga() { 
        return harga; 
    }
    public int getStok() { 
        return stok; 
    }

    public void setIdBarang(int idBarang) { 
        this.idBarang = idBarang; 
    }
    public void setNamaBarang(String namaBarang) { 
        this.namaBarang = namaBarang;
    }
    public void setKategori(String kategori) { 
        this.kategori = kategori;
    }
    public void setHarga(double harga) { 
        this.harga = harga; 
    }
    public void setStok(int stok) { 
        this.stok = stok; 
    }
}