package com.penjualan.model;

public class Pelanggan {
    private int idPelanggan;
    private String namaPelanggan;
    private String alamat;
    private String noHp;

    public Pelanggan(int idPelanggan, String namaPelanggan, String alamat, String noHp) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noHp = noHp;
    }

    public Pelanggan(String namaPelanggan, String alamat, String noHp) {
        this.namaPelanggan = namaPelanggan;
        this.alamat = alamat;
        this.noHp = noHp;
    }

    public int getIdPelanggan() { 
        return idPelanggan; 
    }
    public String getNamaPelanggan() { 
        return namaPelanggan; 
    }
    public String getAlamat() { 
        return alamat; 
    }
    public String getNoHp() { 
        return noHp; 
    }

    public void setIdPelanggan(int idPelanggan) { 
        this.idPelanggan = idPelanggan; 
    }
    public void setNamaPelanggan(String namaPelanggan) { 
        this.namaPelanggan = namaPelanggan; 
    }
    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }
    public void setNoHp(String noHp) { 
        this.noHp = noHp; 
    }
}