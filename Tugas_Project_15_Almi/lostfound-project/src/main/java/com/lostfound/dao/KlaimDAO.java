package com.lostfound.dao;

import com.lostfound.exception.StatusKlaimTidakValidException;
import com.lostfound.model.Klaim;
import com.lostfound.util.Koneksi;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KlaimDAO {

    /**
     * Memproses klaim barang dengan memanggil STORED PROCEDURE
     * sp_proses_klaim. Jika barang sudah pernah diklaim, procedure
     * akan melempar error SQL yang ditangkap dan dikonversi menjadi
     * custom exception StatusKlaimTidakValidException.
     */
    public void prosesKlaim(int idBarang, int idPelapor) throws StatusKlaimTidakValidException {
        String sql = "{CALL sp_proses_klaim(?, ?)}";

        try (Connection conn = Koneksi.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idBarang);
            cs.setInt(2, idPelapor);
            cs.execute();
            System.out.println(">> Klaim berhasil diproses.");

        } catch (SQLException e) {
            // pesan dari SIGNAL SQLSTATE pada procedure akan tertangkap di sini
            throw new StatusKlaimTidakValidException("Klaim gagal diproses: " + e.getMessage());
        }
    }

    /**
     * Mengambil riwayat klaim menggunakan VIEW v_riwayat_klaim.
     */
    public List<Klaim> getRiwayatKlaim() {
        List<Klaim> daftar = new ArrayList<>();
        String sql = "SELECT * FROM v_riwayat_klaim";

        try (Connection conn = Koneksi.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Klaim k = new Klaim(
                        rs.getInt("id_klaim"),
                        rs.getString("nama_barang"),
                        rs.getString("jenis"),
                        rs.getString("nama_pelapor"),
                        rs.getString("tipe_pelapor"),
                        rs.getDate("tanggal_klaim").toLocalDate(),
                        rs.getString("status_klaim")
                );
                daftar.add(k);
            }

        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil riwayat klaim: " + e.getMessage());
        }
        return daftar;
    }
}
