package com.penjualan.service;

import com.penjualan.dao.BarangDAO;
import com.penjualan.model.Barang;

import java.util.List;

public class BarangService {
    private BarangDAO barangDAO;

    public BarangService() {
        this.barangDAO = new BarangDAO();
    }

    public void tambahBarang(String nama, String kategori, double harga, int stok) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Nama barang tidak boleh kosong!");
            return;
        }
        if (harga <= 0) {
            System.out.println("Harga harus lebih dari 0!");
            return;
        }
        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif!");
            return;
        }
        Barang barang = new Barang(nama, kategori, harga, stok);
        barangDAO.tambah(barang);
    }

    public List<Barang> getAllBarang() {
        return barangDAO.getAll();
    }

    public List<Barang> cariBarang(String keyword) {
        return barangDAO.cari(keyword);
    }

    public void updateBarang(Barang barang) {
        if (barang.getHarga() <= 0) {
            System.out.println("Harga harus lebih dari 0!");
            return;
        }
        barangDAO.update(barang);
    }

    public void hapusBarang(int idBarang) {
        barangDAO.hapus(idBarang);
    }
    }
