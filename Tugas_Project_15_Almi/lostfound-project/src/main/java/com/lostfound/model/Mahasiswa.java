package com.lostfound.model;

/**
 * Subclass Mahasiswa - mewarisi Pelapor (inheritance)
 * dan mengoverride method getTipe()/getIdentitas() (polimorfisme).
 */
public class Mahasiswa extends Pelapor {

    private String nim;

    public Mahasiswa(int idPelapor, String nama, String kontak, String nim) {
        super(idPelapor, nama, kontak);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public String getTipe() {
        return "Mahasiswa";
    }

    @Override
    public String getIdentitas() {
        return nim;
    }
}
