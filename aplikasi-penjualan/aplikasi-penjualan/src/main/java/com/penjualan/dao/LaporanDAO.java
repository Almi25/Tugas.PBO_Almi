package com.penjualan.dao;

import com.penjualan.database.DatabaseConnection;
import com.penjualan.model.LaporanPenjualan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaporanDAO {

    public List<LaporanPenjualan> getLaporan() {
        List<LaporanPenjualan> daftarLaporan = new ArrayList<>();
        String query = "SELECT * FROM v_laporan_penjualan";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                daftarLaporan.add(new LaporanPenjualan(
                    rs.getInt("id_transaksi"),
                    rs.getTimestamp("tanggal"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("nama_barang"),
                    rs.getInt("jumlah"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("total_harga")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil laporan: " + e.getMessage());
        }
        return daftarLaporan;
    }
}