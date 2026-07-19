package com.penjualan.service;

import com.penjualan.dao.TransaksiDAO;
import com.penjualan.model.DetailTransaksi;

import java.util.List;

public class TransaksiService {
    private TransaksiDAO transaksiDAO;

    public TransaksiService() {
        this.transaksiDAO = new TransaksiDAO();
    }

    public boolean buatTransaksi(int idPelanggan, double totalHarga, List<DetailTransaksi> daftarDetail) {
        if (daftarDetail == null || daftarDetail.isEmpty()) {
            System.out.println("Transaksi harus memiliki minimal 1 barang!");
            return false;
        }
        if (totalHarga <= 0) {
            System.out.println("Total harga tidak valid!");
            return false;
        }
        return transaksiDAO.buatTransaksi(idPelanggan, totalHarga, daftarDetail);
    }

    public double getTotalPendapatan() {
        return transaksiDAO.getTotalPendapatan();
    }
}