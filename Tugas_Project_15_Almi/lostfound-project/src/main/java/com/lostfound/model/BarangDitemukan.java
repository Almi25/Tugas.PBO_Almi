package com.lostfound.model;

import java.time.LocalDate;

public class BarangDitemukan extends Barang {

    private String tempatPenyimpanan;

    public BarangDitemukan(int idBarang, String namaBarang, String lokasi, LocalDate tanggal,
                            String status, String tempatPenyimpanan) {
        super(idBarang, namaBarang, lokasi, tanggal, status);
        this.tempatPenyimpanan = tempatPenyimpanan;
    }

    public String getTempatPenyimpanan() {
        return tempatPenyimpanan;
    }

    public void setTempatPenyimpanan(String tempatPenyimpanan) {
        this.tempatPenyimpanan = tempatPenyimpanan;
    }

    @Override
    public String getJenis() {
        return "Ditemukan";
    }

    @Override
    public String getInfo() {
        return "Barang ditemukan: " + getNamaBarang() + " di " + getLokasi()
                + " | Disimpan di: " + tempatPenyimpanan + " | Status: " + getStatus();
    }
}
