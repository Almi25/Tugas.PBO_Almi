package com.lostfound.model;

/**
 * Subclass Staf - mewarisi Pelapor (inheritance)
 * dan mengoverride method getTipe()/getIdentitas() (polimorfisme).
 */
public class Staf extends Pelapor {

    private String nip;

    public Staf(int idPelapor, String nama, String kontak, String nip) {
        super(idPelapor, nama, kontak);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public String getTipe() {
        return "Staf";
    }

    @Override
    public String getIdentitas() {
        return nip;
    }
}
