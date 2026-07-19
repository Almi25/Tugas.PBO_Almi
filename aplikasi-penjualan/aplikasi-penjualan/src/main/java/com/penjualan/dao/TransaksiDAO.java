package com.penjualan.dao;

import com.penjualan.database.DatabaseConnection;
import com.penjualan.model.DetailTransaksi;

import java.sql.*;
import java.util.List;

public class TransaksiDAO {

    // Simpan 1 transaksi (header) + semua detail barangnya sekaligus
    public boolean buatTransaksi(int idPelanggan, double totalHarga, List<DetailTransaksi> daftarDetail) {
        Connection c = DatabaseConnection.getConnection();
        String queryTransaksi = "INSERT INTO transaksi (id_pelanggan, total_harga) VALUES (?, ?)";
        String queryDetail = "INSERT INTO detail_transaksi (id_transaksi, id_barang, jumlah, subtotal) VALUES (?, ?, ?, ?)";

        try {
            // 1. Insert transaksi (header) dulu, minta ID yang baru di-generate
            PreparedStatement stmtTransaksi = c.prepareStatement(queryTransaksi, Statement.RETURN_GENERATED_KEYS);
            stmtTransaksi.setInt(1, idPelanggan);
            stmtTransaksi.setDouble(2, totalHarga);
            stmtTransaksi.executeUpdate();

            // 2. Ambil ID transaksi yang baru dibuat
            ResultSet keys = stmtTransaksi.getGeneratedKeys();
            int idTransaksiBaru = 0;
            if (keys.next()) {
                idTransaksiBaru = keys.getInt(1);
            }
            stmtTransaksi.close();

            // 3. Insert setiap detail barang, pakai idTransaksiBaru tadi
            //    (setiap insert ini otomatis memicu trigger trg_kurangi_stok)
            PreparedStatement stmtDetail = c.prepareStatement(queryDetail);
            for (DetailTransaksi d : daftarDetail) {
                stmtDetail.setInt(1, idTransaksiBaru);
                stmtDetail.setInt(2, d.getIdBarang());
                stmtDetail.setInt(3, d.getJumlah());
                stmtDetail.setDouble(4, d.getSubtotal());
                stmtDetail.executeUpdate();
            }
            stmtDetail.close();

            System.out.println("Transaksi berhasil disimpan. ID Transaksi: " + idTransaksiBaru);
            return true;

        } catch (SQLException e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
            return false;
        }
    }

    // Panggil Stored Function total_pendapatan()
    public double getTotalPendapatan() {
        String query = "SELECT total_pendapatan() AS total";
        Connection c = DatabaseConnection.getConnection();
        double total = 0;

        try (PreparedStatement stmt = c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil total pendapatan: " + e.getMessage());
        }
        return total;
    }
}