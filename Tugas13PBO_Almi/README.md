# Aplikasi CLI Toko Retail (Tugas 13)

Aplikasi Command Line Interface (CLI) sederhana untuk mengelola data barang
pada **database `toko_retail`**, dengan operasi CRUD lengkap (Create, Read,
Update, Delete).

## Fitur / Menu

```
==========================================
           MENU TOKO RETAIL
==========================================
1. Tampil Semua Data
2. Tambah Data
3. Cari Data
4. Ubah Data
5. Hapus Data
0. Keluar
==========================================
```

## Struktur Proyek

```
toko_retail_cli/
├── main.py                 # Kode utama aplikasi CLI
├── render_screenshot.py    # Helper untuk membuat screenshot bergaya terminal
├── generate_screenshots.py # Script untuk menghasilkan screenshot menu 1-5
├── toko_retail.db           # Database SQLite (dibuat otomatis saat dijalankan)
├── screenshots/             # Hasil screenshot tampilan menu 1-5
└── README.md
```

## Database

Aplikasi ini menggunakan **SQLite** (`toko_retail.db`) agar mudah dijalankan
tanpa perlu instalasi server database terpisah. Nama database dan tabel
disesuaikan dengan ketentuan tugas:

- Database: `toko_retail`
- Tabel: `barang` (kode, nama_barang, harga, stok)

> Jika instruktur mewajibkan **MySQL/MariaDB**, struktur tabel di atas dapat
> langsung dipakai. Cukup ganti bagian koneksi di `main.py`
> (`sqlite3.connect(...)`) dengan library `mysql-connector-python` atau
> `PyMySQL`, lalu sesuaikan query `?` placeholder menjadi `%s`.

## Cara Menjalankan

1. Pastikan Python 3 sudah terpasang.
2. Jalankan aplikasi:

   ```bash
   python3 main.py
   ```

3. Saat pertama kali dijalankan, database `toko_retail.db` beserta tabel dan
   3 data awal (Roti Tawar, Malkist, Kopi Kapal Api) akan otomatis dibuat.
4. Pilih menu 1-5 sesuai kebutuhan, atau `0` untuk keluar.

## Menghasilkan Ulang Screenshot

Screenshot pada folder `screenshots/` dibuat secara otomatis (bukan tangkapan
layar manual) menggunakan `generate_screenshots.py`, yang menjalankan setiap
fungsi menu dan merender outputnya menjadi gambar bergaya terminal:

```bash
python3 generate_screenshots.py
```

## Upload ke GitHub

```bash
git init
git add .
git commit -m "Tugas 13 - Aplikasi CLI Toko Retail"
git branch -M main
git remote add origin <URL_REPO_GITHUB_ANDA>
git push -u origin main
```

Setelah itu, salin link repository GitHub dan isikan ke Edlink sesuai
instruksi tugas.
