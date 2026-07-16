package com.lostfound.model;

import java.time.LocalDate;

/**
 * Abstract class Barang sebagai superclass dari BarangHilang dan BarangDitemukan.
 */
public abstract class Barang {

    private int idBarang;
    private String namaBarang;
    private String lokasi;
    private LocalDate tanggal;
    private String status;

    public Barang(int idBarang, String namaBarang, String lokasi, LocalDate tanggal, String status) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // method abstract -> di-override berbeda oleh subclass (polimorfisme)
    public abstract String getJenis();

    public abstract String getInfo();
}
