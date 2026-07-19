package com.penjualan.dao;

import com.penjualan.database.DatabaseConnection;
import com.penjualan.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // LOGIN — cek username & password, return User kalau cocok, null kalau tidak
    public User login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        Connection c = DatabaseConnection.getConnection();
        User user = null;

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("id_user"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nama"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal login: " + e.getMessage());
        }
        return user;
    }

    public void tambah(User user) {
        String query = "INSERT INTO user (username, password, nama, role) VALUES (?, ?, ?, ?)";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNama());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            System.out.println("User berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan user: " + e.getMessage());
        }
    }

    public List<User> getAll() {
        List<User> daftarUser = new ArrayList<>();
        String query = "SELECT * FROM user";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                daftarUser.add(new User(
                    rs.getInt("id_user"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nama"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data user: " + e.getMessage());
        }
        return daftarUser;
    }

    public void update(User user) {
        String query = "UPDATE user SET username=?, password=?, nama=?, role=? WHERE id_user=?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNama());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getIdUser());
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "User berhasil diperbarui." : "User tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui user: " + e.getMessage());
        }
    }

    public void hapus(int idUser) {
        String query = "DELETE FROM user WHERE id_user = ?";
        Connection c = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            int baris = stmt.executeUpdate();
            System.out.println(baris > 0 ? "User berhasil dihapus." : "User tidak ditemukan.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus user: " + e.getMessage());
        }
    }
}