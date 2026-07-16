package com.lostfound.dao;

import com.lostfound.exception.BarangTidakDitemukanException;
import com.lostfound.model.Barang;
import com.lostfound.model.BarangDitemukan;
import com.lostfound.model.BarangHilang;
import com.lostfound.util.Koneksi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BarangDAO {

    public void laporBarangHilang(String namaBarang, String lokasi, LocalDate tanggal, String ciriCiri) {
        String sql = "INSERT INTO barang (nama_barang, jenis, lokasi, tanggal, keterangan, status) "
                + "VALUES (?, 'Hilang', ?, ?, ?, 'Belum Diklaim')";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, namaBarang);
            ps.setString(2, lokasi);
            ps.setDate(3, Date.valueOf(tanggal));
            ps.setString(4, ciriCiri);
            ps.executeUpdate();
            System.out.println(">> Laporan barang hilang berhasil disimpan.");

        } catch (SQLException e) {
            System.out.println(">> Gagal menyimpan laporan barang hilang: " + e.getMessage());
        }
    }

    public void laporBarangDitemukan(String namaBarang, String lokasi, LocalDate tanggal, String tempatPenyimpanan) {
        String sql = "INSERT INTO barang (nama_barang, jenis, lokasi, tanggal, keterangan, status) "
                + "VALUES (?, 'Ditemukan', ?, ?, ?, 'Belum Diklaim')";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, namaBarang);
            ps.setString(2, lokasi);
            ps.setDate(3, Date.valueOf(tanggal));
            ps.setString(4, tempatPenyimpanan);
            ps.executeUpdate();
            System.out.println(">> Laporan barang ditemukan berhasil disimpan.");

        } catch (SQLException e) {
            System.out.println(">> Gagal menyimpan laporan barang ditemukan: " + e.getMessage());
        }
    }

    /**
     * Mengambil daftar barang yang masih berstatus belum diklaim
     * dengan memanfaatkan VIEW v_barang_belum_diklaim.
     * Setiap baris dipetakan secara polimorfik ke BarangHilang atau BarangDitemukan.
     */
    public List<Barang> getBarangBelumDiklaim() {
        List<Barang> daftar = new ArrayList<>();
        String sql = "SELECT * FROM v_barang_belum_diklaim";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_barang");
                String nama = rs.getString("nama_barang");
                String jenis = rs.getString("jenis");
                String lokasi = rs.getString("lokasi");
                LocalDate tanggal = rs.getDate("tanggal").toLocalDate();
                String keterangan = rs.getString("keterangan");
                String status = rs.getString("status");

                Barang b = jenis.equalsIgnoreCase("Hilang")
                        ? new BarangHilang(id, nama, lokasi, tanggal, status, keterangan)
                        : new BarangDitemukan(id, nama, lokasi, tanggal, status, keterangan);

                daftar.add(b);
            }

        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil data barang: " + e.getMessage());
        }
        return daftar;
    }

    /**
     * Mengecek keberadaan barang berdasarkan id, dan menghitung
     * sudah berapa lama barang dilaporkan menggunakan FUNCTION
     * fn_lama_hari_ditemukan.
     */
    public int getLamaHariDilaporkan(int idBarang) throws BarangTidakDitemukanException {
        String sqlCek = "SELECT tanggal FROM barang WHERE id_barang = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlCek)) {

            ps.setInt(1, idBarang);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new BarangTidakDitemukanException("Barang dengan ID " + idBarang + " tidak ditemukan");
                }
            }

            // memanggil FUNCTION fn_lama_hari_ditemukan menggunakan tanggal barang
            String sqlHitung = "SELECT fn_lama_hari_ditemukan(tanggal) AS lama_hari FROM barang WHERE id_barang = ?";
            try (PreparedStatement ps3 = conn.prepareStatement(sqlHitung)) {
                ps3.setInt(1, idBarang);
                try (ResultSet rs3 = ps3.executeQuery()) {
                    if (rs3.next()) {
                        return rs3.getInt("lama_hari");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(">> Gagal menghitung lama hari: " + e.getMessage());
        }
        return -1;
    }
}
