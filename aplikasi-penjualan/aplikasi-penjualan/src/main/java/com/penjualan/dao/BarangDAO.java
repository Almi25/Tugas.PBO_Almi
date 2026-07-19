package com.penjualan.dao;
import com.penjualan.database.DatabaseConnection;
import com.penjualan.model.Barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    // Tambah barang via Stored Procedure tambah_barang()
    public void tambah(Barang barang) {
        String query = "CALL tambah_barang(?, ?, ?, ?)";
        Connection c = DatabaseConnection.getConnection();

        try (CallableStatement stmt = c.prepareCall(query)) {
            stmt.setString(1, barang.getNamaBarang());
            stmt.setString(2, barang.getKategori());
            stmt.setDouble(3, barang.getHarga());
            stmt.setInt(4, barang.getStok());
            stmt.execute();
            System.out.println("Barang berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan barang: " + e.getMessage());
        }
    }

    public List<Barang> getAll() {
        List<Barang> daftarBarang = new ArrayList<>();
        String query = "SELECT * FROM barang";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                daftarBarang.add(new Barang(
                    rs.getInt("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data barang: " + e.getMessage());
        }
        return daftarBarang;
    }

    // Cari barang berdasarkan nama (pakai LIKE)
    public List<Barang> cari(String keyword) {
        List<Barang> hasil = new ArrayList<>();
        String query = "SELECT * FROM barang WHERE nama_barang LIKE ?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new Barang(
                        rs.getInt("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("kategori"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari barang: " + e.getMessage());
        }
        return hasil;
    }

    public void update(Barang barang) {
        String query = "UPDATE barang SET nama_barang=?, kategori=?, harga=?, stok=? WHERE id_barang=?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, barang.getNamaBarang());
            stmt.setString(2, barang.getKategori());
            stmt.setDouble(3, barang.getHarga());
            stmt.setInt(4, barang.getStok());
            stmt.setInt(5, barang.getIdBarang());
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "Barang berhasil diperbarui." : "Barang tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui barang: " + e.getMessage());
        }
    }

    public void hapus(int idBarang) {
        String query = "DELETE FROM barang WHERE id_barang = ?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, idBarang);
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "Barang berhasil dihapus." : "Barang tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus barang: " + e.getMessage());
        }
    }
}