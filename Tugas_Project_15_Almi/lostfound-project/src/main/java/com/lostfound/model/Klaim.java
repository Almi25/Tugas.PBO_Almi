package com.lostfound.model;

import java.time.LocalDate;

public class Klaim {

    private int idKlaim;
    private String namaBarang;
    private String jenisBarang;
    private String namaPelapor;
    private String tipePelapor;
    private LocalDate tanggalKlaim;
    private String statusKlaim;

    public Klaim(int idKlaim, String namaBarang, String jenisBarang, String namaPelapor,
                 String tipePelapor, LocalDate tanggalKlaim, String statusKlaim) {
        this.idKlaim = idKlaim;
        this.namaBarang = namaBarang;
        this.jenisBarang = jenisBarang;
        this.namaPelapor = namaPelapor;
        this.tipePelapor = tipePelapor;
        this.tanggalKlaim = tanggalKlaim;
        this.statusKlaim = statusKlaim;
    }

    public int getIdKlaim() {
        return idKlaim;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public String getNamaPelapor() {
        return namaPelapor;
    }

    public String getTipePelapor() {
        return tipePelapor;
    }

    public LocalDate getTanggalKlaim() {
        return tanggalKlaim;
    }

    public String getStatusKlaim() {
        return statusKlaim;
    }

    @Override
    public String toString() {
        return "ID Klaim: " + idKlaim + " | Barang: " + namaBarang + " (" + jenisBarang + ")"
                + " | Pelapor: " + namaPelapor + " (" + tipePelapor + ")"
                + " | Tanggal: " + tanggalKlaim + " | Status: " + statusKlaim;
    }
}
