package com.lostfound.model;

/**
 * Abstract class Pelapor sebagai superclass dari Mahasiswa dan Staf.
 * Menerapkan konsep enkapsulasi (atribut private + getter/setter)
 * dan menjadi dasar konsep inheritance & polimorfisme.
 */
public abstract class Pelapor {

    private int idPelapor;
    private String nama;
    private String kontak;

    public Pelapor(int idPelapor, String nama, String kontak) {
        this.idPelapor = idPelapor;
        this.nama = nama;
        setKontak(kontak);
    }

    public int getIdPelapor() {
        return idPelapor;
    }

    public void setIdPelapor(int idPelapor) {
        this.idPelapor = idPelapor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        if (kontak == null || kontak.trim().length() < 8) {
            throw new IllegalArgumentException("Nomor kontak tidak valid, minimal 8 karakter");
        }
        this.kontak = kontak;
    }

    // method abstract -> akan di-override berbeda oleh subclass (polimorfisme)
    public abstract String getTipe();

    public abstract String getIdentitas();

    @Override
    public String toString() {
        return "ID: " + idPelapor + " | Nama: " + nama + " | Tipe: " + getTipe()
                + " | Identitas: " + getIdentitas() + " | Kontak: " + kontak;
    }
}
