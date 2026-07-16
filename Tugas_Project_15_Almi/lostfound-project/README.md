# Sistem Informasi Barang Hilang dan Ditemukan (Lost & Found CLI)

Aplikasi CLI Java + MySQL untuk mengelola data pelapor, laporan barang hilang/ditemukan,
dan proses klaim barang. Dibuat untuk memenuhi tugas mata kuliah Pemrograman Berbasis Objek (PBO).

## Struktur Package
- `com.lostfound.app`   -> Main.java (menu CLI)
- `com.lostfound.model` -> Pelapor, Mahasiswa, Staf, Barang, BarangHilang, BarangDitemukan, Klaim
- `com.lostfound.dao`   -> PelaporDAO, BarangDAO, KlaimDAO
- `com.lostfound.util`  -> Koneksi (JDBC)
- `com.lostfound.exception` -> custom exception

## Langkah Setup

### 1. Setup Database
1. Buka MySQL (bisa lewat MySQL Workbench, phpMyAdmin, atau terminal `mysql`).
2. Jalankan seluruh isi file `sql/database.sql`. Script ini akan membuat database
   `lost_found`, tabel, stored procedure, function, trigger, view, dan data contoh.

### 2. Konfigurasi Koneksi
Buka file `src/main/java/com/lostfound/util/Koneksi.java`, sesuaikan:
```
URL      = jdbc:mysql://localhost:3306/lost_found
USER     = root
PASSWORD = (isi sesuai password MySQL lokal)
```

### 3. Build & Jalankan (menggunakan Maven)
```
mvn clean package
java -cp target/lost-found-cli.jar;target/lib/* com.lostfound.app.Main
```
(pada Linux/Mac gunakan `:` bukan `;` sebagai pemisah classpath)

### 4. Build & Jalankan Manual (tanpa Maven, pakai driver yang sudah disertakan)
Driver JDBC MySQL sudah disertakan di folder `lib/mysql-connector-java-8.0.30.jar`, jadi tidak perlu download lagi.

1. Compile (Windows, dari root folder project):
   ```
   javac -cp lib\mysql-connector-java-8.0.30.jar -d out src\main\java\com\lostfound\app\*.java src\main\java\com\lostfound\model\*.java src\main\java\com\lostfound\dao\*.java src\main\java\com\lostfound\util\*.java src\main\java\com\lostfound\exception\*.java
   ```
2. Jalankan:
   ```
   java -cp "out;lib\mysql-connector-java-8.0.30.jar" com.lostfound.app.Main
   ```
   (di Linux/Mac, ganti `;` menjadi `:` dan `\` menjadi `/`)

## Bukti Aplikasi Sudah Diuji

Project ini sudah diuji coba secara nyata (compile, koneksi ke MySQL, dan menjalankan seluruh menu) sebelum dikirim ke Anda. Hasil screenshot asli tersedia di folder `screenshots/`:
- `01_menu_utama.png` - Tampilan menu utama
- `02_tambah_pelapor.png` - Tambah pelapor
- `03_daftar_pelapor.png` - Lihat daftar pelapor
- `04_lapor_hilang.png` - Lapor barang hilang
- `05_lapor_ditemukan.png` - Lapor barang ditemukan
- `06_proses_klaim.png` - Proses klaim (procedure + function + trigger teruji jalan)
- `07_riwayat_klaim.png` - Riwayat klaim (view teruji jalan)
- `08_exception_klaim_ganda.png` - Exception handling teruji (klaim barang yang sudah diklaim ditolak)

Screenshot-screenshot ini sudah otomatis dimasukkan ke dalam `Laporan_Lost_Found.docx`. Namun, dosen kemungkinan meminta screenshot dari hasil run di komputer Anda sendiri (bukan hasil pengujian ini), jadi tetap disarankan untuk menjalankan ulang aplikasi ini di laptop Anda sendiri dan mengambil screenshot versi Anda sendiri sebelum dikumpulkan.

## Alur Menu
1. Menu Pelapor -> Tambah Pelapor, Lihat Daftar Pelapor
2. Menu Barang -> Lapor Barang Hilang, Lapor Barang Ditemukan
3. Menu Klaim -> Proses Klaim Barang, Lihat Riwayat Klaim
4. Keluar

## Konsep OOP yang Diimplementasikan
- Class & Object: seluruh entitas (Pelapor, Barang, Klaim)
- Enkapsulasi: atribut private dengan getter/setter dan validasi
- Inheritance: Pelapor -> Mahasiswa, Staf | Barang -> BarangHilang, BarangDitemukan
- Polimorfisme: override getTipe(), getIdentitas(), getJenis(), getInfo()
- Package: app, model, dao, util, exception
- Exception Handling: BarangTidakDitemukanException, PelaporTidakValidException,
  StatusKlaimTidakValidException, serta penanganan SQLException

## Konsep Database Lanjutan
- Stored Procedure: `sp_proses_klaim`
- Function: `fn_lama_hari_ditemukan`
- Trigger: `trg_update_status_barang`
- View: `v_barang_belum_diklaim`, `v_riwayat_klaim`
