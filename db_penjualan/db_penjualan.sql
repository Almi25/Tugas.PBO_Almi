-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 19 Jul 2026 pada 03.33
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Basis data: `db_penjualan`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_barang` (IN `p_nama` VARCHAR(100), IN `p_kategori` VARCHAR(50), IN `p_harga` DECIMAL(12,2), IN `p_stok` INT)   BEGIN
    INSERT INTO barang (nama_barang, kategori, harga, stok)
    VALUES (p_nama, p_kategori, p_harga, p_stok);
END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `total_pendapatan` () RETURNS DECIMAL(12,2) DETERMINISTIC BEGIN
    DECLARE total DECIMAL(12,2);
    SELECT IFNULL(SUM(total_harga), 0) INTO total FROM transaksi;
    RETURN total;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga` decimal(12,2) NOT NULL,
  `stok` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `kategori`, `harga`, `stok`) VALUES
(1, 'Indomie Goreng', 'Makanan', 3500.00, 100),
(2, 'Aqua 600ml', 'Minuman', 4000.00, 150),
(3, 'Beras 5kg', 'Sembako', 65000.00, 30);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` int(11) NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `trg_kurangi_stok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    UPDATE barang
    SET stok = stok - NEW.jumlah
    WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat`, `no_hp`) VALUES
(1, 'Siti Aminah', 'Jl. Merdeka No. 1', '081234567890'),
(2, 'Joko Susilo', 'Jl. Sudirman No. 5', '081298765432');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_harga` decimal(12,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'kasir'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama`, `role`) VALUES
(1, 'admin', 'admin123', 'Admin Utama', 'admin'),
(2, 'kasir1', 'kasir123', 'Budi Kasir', 'kasir'),
(3, 'shelva', 'shelva123', 'shelva', 'kasir');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_laporan_penjualan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_laporan_penjualan` (
`id_transaksi` int(11)
,`tanggal` timestamp
,`nama_pelanggan` varchar(100)
,`nama_barang` varchar(100)
,`jumlah` int(11)
,`subtotal` decimal(12,2)
,`total_harga` decimal(12,2)
);

--
-- Indeks untuk tabel yang dibuang
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

-- --------------------------------------------------------

--
-- Struktur untuk view `v_laporan_penjualan`
--
DROP TABLE IF EXISTS `v_laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_laporan_penjualan`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `t`.`tanggal` AS `tanggal`, `p`.`nama_pelanggan` AS `nama_pelanggan`, `b`.`nama_barang` AS `nama_barang`, `dt`.`jumlah` AS `jumlah`, `dt`.`subtotal` AS `subtotal`, `t`.`total_harga` AS `total_harga` FROM (((`transaksi` `t` join `pelanggan` `p` on(`t`.`id_pelanggan` = `p`.`id_pelanggan`)) join `detail_transaksi` `dt` on(`t`.`id_transaksi` = `dt`.`id_transaksi`)) join `barang` `b` on(`dt`.`id_barang` = `b`.`id_barang`)) ORDER BY `t`.`tanggal` DESC ;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
