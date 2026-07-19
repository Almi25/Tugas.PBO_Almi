package com.penjualan.service;

import com.penjualan.dao.PelangganDAO;
import com.penjualan.model.Pelanggan;

import java.util.List;

public class PelangganService {
    private PelangganDAO pelangganDAO;

    public PelangganService() {
        this.pelangganDAO = new PelangganDAO();
    }

    public void tambahPelanggan(String nama, String alamat, String noHp) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Nama pelanggan tidak boleh kosong!");
            return;
        }
        Pelanggan pelanggan = new Pelanggan(nama, alamat, noHp);
        pelangganDAO.tambah(pelanggan);
    }

    public List<Pelanggan> getAllPelanggan() {
        return pelangganDAO.getAll();
    }

    public List<Pelanggan> cariPelanggan(String keyword) {
        return pelangganDAO.cari(keyword);
    }

    public void updatePelanggan(Pelanggan pelanggan) {
        pelangganDAO.update(pelanggan);
    }

    public void hapusPelanggan(int idPelanggan) {
        pelangganDAO.hapus(idPelanggan);
    }
}