package com.lostfound.model;

import java.time.LocalDate;

public class BarangHilang extends Barang {

    private String ciriCiri;

    public BarangHilang(int idBarang, String namaBarang, String lokasi, LocalDate tanggal,
                         String status, String ciriCiri) {
        super(idBarang, namaBarang, lokasi, tanggal, status);
        this.ciriCiri = ciriCiri;
    }

    public String getCiriCiri() {
        return ciriCiri;
    }

    public void setCiriCiri(String ciriCiri) {
        this.ciriCiri = ciriCiri;
    }

    @Override
    public String getJenis() {
        return "Hilang";
    }

    @Override
    public String getInfo() {
        return "Barang hilang: " + getNamaBarang() + " di " + getLokasi()
                + " | Ciri-ciri: " + ciriCiri + " | Status: " + getStatus();
    }
}
