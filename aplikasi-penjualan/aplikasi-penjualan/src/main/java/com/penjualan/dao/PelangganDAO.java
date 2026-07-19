package com.penjualan.dao;

import com.penjualan.database.DatabaseConnection;
import com.penjualan.model.Pelanggan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {

    public void tambah(Pelanggan pelanggan) {
        String query = "INSERT INTO pelanggan (nama_pelanggan, alamat, no_hp) VALUES (?, ?, ?)";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, pelanggan.getNamaPelanggan());
            stmt.setString(2, pelanggan.getAlamat());
            stmt.setString(3, pelanggan.getNoHp());
            stmt.executeUpdate();
            System.out.println("Pelanggan berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan pelanggan: " + e.getMessage());
        }
    }

    public List<Pelanggan> getAll() {
        List<Pelanggan> daftarPelanggan = new ArrayList<>();
        String query = "SELECT * FROM pelanggan";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                daftarPelanggan.add(new Pelanggan(
                    rs.getInt("id_pelanggan"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("alamat"),
                    rs.getString("no_hp")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data pelanggan: " + e.getMessage());
        }
        return daftarPelanggan;
    }

    public List<Pelanggan> cari(String keyword) {
        List<Pelanggan> hasil = new ArrayList<>();
        String query = "SELECT * FROM pelanggan WHERE nama_pelanggan LIKE ?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new Pelanggan(
                        rs.getInt("id_pelanggan"),
                        rs.getString("nama_pelanggan"),
                        rs.getString("alamat"),
                        rs.getString("no_hp")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mencari pelanggan: " + e.getMessage());
        }
        return hasil;
    }

    public void update(Pelanggan pelanggan) {
        String query = "UPDATE pelanggan SET nama_pelanggan=?, alamat=?, no_hp=? WHERE id_pelanggan=?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, pelanggan.getNamaPelanggan());
            stmt.setString(2, pelanggan.getAlamat());
            stmt.setString(3, pelanggan.getNoHp());
            stmt.setInt(4, pelanggan.getIdPelanggan());
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "Pelanggan berhasil diperbarui." : "Pelanggan tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui pelanggan: " + e.getMessage());
        }
    }

    public void hapus(int idPelanggan) {
        String query = "DELETE FROM pelanggan WHERE id_pelanggan = ?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, idPelanggan);
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "Pelanggan berhasil dihapus." : "Pelanggan tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus pelanggan: " + e.getMessage());
        }
    }
}