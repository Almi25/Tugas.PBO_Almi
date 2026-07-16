-- =========================================================
-- DATABASE: lost_found
-- Sistem Informasi Barang Hilang dan Ditemukan
-- =========================================================

DROP DATABASE IF EXISTS lost_found;
CREATE DATABASE lost_found;
USE lost_found;

-- =========================================================
-- 1. TABEL DASAR
-- =========================================================

CREATE TABLE pelapor (
    id_pelapor  INT AUTO_INCREMENT PRIMARY KEY,
    nama        VARCHAR(100) NOT NULL,
    tipe        VARCHAR(20)  NOT NULL,   -- 'Mahasiswa' atau 'Staf'
    identitas   VARCHAR(30)  NOT NULL,   -- NIM atau NIP
    kontak      VARCHAR(50)  NOT NULL
);

CREATE TABLE barang (
    id_barang    INT AUTO_INCREMENT PRIMARY KEY,
    nama_barang  VARCHAR(100) NOT NULL,
    jenis        VARCHAR(20)  NOT NULL,  -- 'Hilang' atau 'Ditemukan'
    lokasi       VARCHAR(100),
    tanggal      DATE NOT NULL,
    keterangan   VARCHAR(200),           -- ciri-ciri (jika Hilang) / tempat penyimpanan (jika Ditemukan)
    status       VARCHAR(20) DEFAULT 'Belum Diklaim'
);

CREATE TABLE klaim (
    id_klaim      INT AUTO_INCREMENT PRIMARY KEY,
    id_barang     INT NOT NULL,
    id_pelapor    INT NOT NULL,
    tanggal_klaim DATE,
    status_klaim  VARCHAR(20) DEFAULT 'Diproses',
    FOREIGN KEY (id_barang) REFERENCES barang(id_barang),
    FOREIGN KEY (id_pelapor) REFERENCES pelapor(id_pelapor)
);

-- =========================================================
-- 2. STORED PROCEDURE
-- sp_proses_klaim: memproses klaim barang. Jika barang sudah
-- diklaim sebelumnya, procedure akan menolak dengan error.
-- =========================================================

DELIMITER //
CREATE PROCEDURE sp_proses_klaim(
    IN p_id_barang   INT,
    IN p_id_pelapor  INT
)
BEGIN
    DECLARE v_status VARCHAR(20);

    SELECT status INTO v_status
    FROM barang
    WHERE id_barang = p_id_barang;

    IF v_status IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Barang tidak ditemukan';
    ELSEIF v_status = 'Sudah Diklaim' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Barang sudah diklaim sebelumnya';
    ELSE
        INSERT INTO klaim (id_barang, id_pelapor, tanggal_klaim, status_klaim)
        VALUES (p_id_barang, p_id_pelapor, CURDATE(), 'Selesai');
    END IF;
END //
DELIMITER ;

-- =========================================================
-- 3. FUNCTION
-- fn_lama_hari_ditemukan: menghitung sudah berapa hari sejak
-- barang dilaporkan hingga hari ini.
-- =========================================================

DELIMITER //
CREATE FUNCTION fn_lama_hari_ditemukan(p_tanggal DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN DATEDIFF(CURDATE(), p_tanggal);
END //
DELIMITER ;

-- =========================================================
-- 4. TRIGGER
-- trg_update_status_barang: otomatis mengubah status barang
-- menjadi 'Sudah Diklaim' setiap kali ada data baru masuk
-- ke tabel klaim.
-- =========================================================

DELIMITER //
CREATE TRIGGER trg_update_status_barang
AFTER INSERT ON klaim
FOR EACH ROW
BEGIN
    UPDATE barang
    SET status = 'Sudah Diklaim'
    WHERE id_barang = NEW.id_barang;
END //
DELIMITER ;

-- =========================================================
-- 5. VIEW
-- v_barang_belum_diklaim: menampilkan seluruh barang yang
-- masih berstatus belum diklaim.
-- =========================================================

CREATE VIEW v_barang_belum_diklaim AS
SELECT id_barang, nama_barang, jenis, lokasi, tanggal, keterangan, status
FROM barang
WHERE status = 'Belum Diklaim';

-- =========================================================
-- 6. VIEW TAMBAHAN
-- v_riwayat_klaim: menampilkan riwayat klaim lengkap dengan
-- data barang dan pelapor (join beberapa tabel).
-- =========================================================

CREATE VIEW v_riwayat_klaim AS
SELECT k.id_klaim, b.nama_barang, b.jenis, p.nama AS nama_pelapor,
       p.tipe AS tipe_pelapor, k.tanggal_klaim, k.status_klaim
FROM klaim k
JOIN barang b ON k.id_barang = b.id_barang
JOIN pelapor p ON k.id_pelapor = p.id_pelapor;

-- =========================================================
-- 7. DATA CONTOH (opsional, untuk uji coba)
-- =========================================================

INSERT INTO pelapor (nama, tipe, identitas, kontak) VALUES
('Andi Saputra', 'Mahasiswa', '10122001', '081234567890'),
('Budi Hartono', 'Staf', 'NIP001122', '081298765432');

INSERT INTO barang (nama_barang, jenis, lokasi, tanggal, keterangan, status) VALUES
('Dompet Coklat', 'Hilang', 'Perpustakaan', '2026-07-10', 'Berisi KTM dan uang tunai', 'Belum Diklaim'),
('Flashdisk 16GB', 'Ditemukan', 'Lab Komputer', '2026-07-12', 'Disimpan di pos satpam', 'Belum Diklaim');
