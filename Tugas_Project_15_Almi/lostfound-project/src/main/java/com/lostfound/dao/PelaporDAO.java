package com.lostfound.dao;

import com.lostfound.exception.PelaporTidakValidException;
import com.lostfound.model.Mahasiswa;
import com.lostfound.model.Pelapor;
import com.lostfound.model.Staf;
import com.lostfound.util.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PelaporDAO {

    public void tambahPelapor(String nama, String tipe, String identitas, String kontak)
            throws PelaporTidakValidException {

        if (!tipe.equalsIgnoreCase("Mahasiswa") && !tipe.equalsIgnoreCase("Staf")) {
            throw new PelaporTidakValidException("Tipe pelapor harus 'Mahasiswa' atau 'Staf'");
        }

        String sql = "INSERT INTO pelapor (nama, tipe, identitas, kontak) VALUES (?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nama);
            ps.setString(2, tipe);
            ps.setString(3, identitas);
            ps.setString(4, kontak);
            ps.executeUpdate();
            System.out.println(">> Data pelapor berhasil ditambahkan.");

        } catch (SQLException e) {
            System.out.println(">> Gagal menambah pelapor: " + e.getMessage());
        }
    }

    public List<Pelapor> getAllPelapor() {
        List<Pelapor> daftar = new ArrayList<>();
        String sql = "SELECT * FROM pelapor";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_pelapor");
                String nama = rs.getString("nama");
                String tipe = rs.getString("tipe");
                String identitas = rs.getString("identitas");
                String kontak = rs.getString("kontak");

                Pelapor p = tipe.equalsIgnoreCase("Mahasiswa")
                        ? new Mahasiswa(id, nama, kontak, identitas)
                        : new Staf(id, nama, kontak, identitas);

                daftar.add(p);
            }

        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil data pelapor: " + e.getMessage());
        }
        return daftar;
    }
}
