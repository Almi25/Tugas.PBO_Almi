package com.penjualan.model;

public class DetailTransaksi {
    private int idDetail;
    private int idTransaksi;
    private int idBarang;
    private int jumlah;
    private double subtotal;

    public DetailTransaksi(int idDetail, int idTransaksi, int idBarang, int jumlah, double subtotal) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public DetailTransaksi(int idTransaksi, int idBarang, int jumlah, double subtotal) {
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public int getIdDetail() { 
        return idDetail; 
    }
    public int getIdTransaksi() { 
        return idTransaksi; 
    }
    public int getIdBarang() { 
        return idBarang; 
    }
    public int getJumlah() { 
        return jumlah; 
    }
    public double getSubtotal() { 
        return subtotal; 
    }

    public void setIdDetail(int idDetail) { 
        this.idDetail = idDetail; 
    }
    public void setIdTransaksi(int idTransaksi) { 
        this.idTransaksi = idTransaksi; 
    }
    public void setIdBarang(int idBarang) { 
        this.idBarang = idBarang; 
    }
    public void setJumlah(int jumlah) { 
        this.jumlah = jumlah; 
    }
    public void setSubtotal(double subtotal) { 
        this.subtotal = subtotal; 
    }
}