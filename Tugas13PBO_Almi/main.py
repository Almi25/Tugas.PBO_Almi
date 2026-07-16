"""
Aplikasi CLI Sederhana - Toko Retail
Tugas 13
--------------------------------------
Fitur:
1. Tampil Semua Data
2. Tambah Data
3. Cari Data
4. Ubah Data
5. Hapus Data
0. Keluar

Database: SQLite (toko_retail.db) - tabel: barang
"""

import sqlite3
import os

DB_NAME = os.path.join(os.path.dirname(os.path.abspath(__file__)), "toko_retail.db")


def get_connection():
    conn = sqlite3.connect(DB_NAME)
    conn.row_factory = sqlite3.Row
    return conn


def init_db():
    """Membuat tabel barang jika belum ada, dan mengisi data awal (seed)."""
    conn = get_connection()
    cur = conn.cursor()
    cur.execute(
        """
        CREATE TABLE IF NOT EXISTS barang (
            kode TEXT PRIMARY KEY,
            nama_barang TEXT NOT NULL,
            harga INTEGER NOT NULL,
            stok INTEGER NOT NULL
        )
        """
    )
    conn.commit()

    cur.execute("SELECT COUNT(*) as jumlah FROM barang")
    jumlah = cur.fetchone()["jumlah"]

    if jumlah == 0:
        data_awal = [
            ("B001", "Roti Tawar", 10000, 100),
            ("B002", "Malkist", 2000, 100),
            ("B003", "Kopi Kapal Api", 3000, 100),
        ]
        cur.executemany(
            "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)",
            data_awal,
        )
        conn.commit()

    conn.close()


def cetak_header():
    print("=" * 42)
    print("           MENU TOKO RETAIL")
    print("=" * 42)


def cetak_menu():
    cetak_header()
    print("1. Tampil Semua Data")
    print("2. Tambah Data")
    print("3. Cari Data")
    print("4. Ubah Data")
    print("5. Hapus Data")
    print("0. Keluar")
    print("=" * 42)


def cetak_tabel(rows):
    print("-" * 58)
    print("| DAFTAR BARANG TOKO RETAIL" + " " * 30 + "|")
    print("-" * 58)
    print(f"| {'#':<3}| {'Kode':<6}| {'Nama Barang':<16}| {'Harga':<8}| {'Stok':<6}|")
    print("-" * 58)
    for i, row in enumerate(rows, start=1):
        print(
            f"| {i:<3}| {row['kode']:<6}| {row['nama_barang']:<16}| "
            f"{row['harga']:<8}| {row['stok']:<6}|"
        )
    print("-" * 58)
    print(f"Total: {len(rows)} barang")


# ------------------- 1. TAMPIL SEMUA DATA -------------------
def tampil_semua_data():
    conn = get_connection()
    cur = conn.cursor()
    cur.execute("SELECT * FROM barang ORDER BY kode")
    rows = cur.fetchall()
    conn.close()

    if not rows:
        print("Belum ada data barang.")
        return
    cetak_tabel(rows)


# ------------------- 2. TAMBAH DATA -------------------
def tambah_data(kode=None, nama=None, harga=None, stok=None, interaktif=True):
    if interaktif:
        kode = input("Kode Barang  : ").strip().upper()
        nama = input("Nama Barang  : ").strip()
        harga = input("Harga        : ").strip()
        stok = input("Stok         : ").strip()

    if not kode or not nama:
        print("Kode dan Nama Barang tidak boleh kosong!")
        return False

    try:
        harga = int(harga)
        stok = int(stok)
    except (ValueError, TypeError):
        print("Harga dan Stok harus berupa angka!")
        return False

    conn = get_connection()
    cur = conn.cursor()
    try:
        cur.execute(
            "INSERT INTO barang (kode, nama_barang, harga, stok) VALUES (?, ?, ?, ?)",
            (kode, nama, harga, stok),
        )
        conn.commit()
        print(f"Data barang '{nama}' berhasil ditambahkan.")
        return True
    except sqlite3.IntegrityError:
        print(f"Kode '{kode}' sudah digunakan. Gunakan kode lain.")
        return False
    finally:
        conn.close()


# ------------------- 3. CARI DATA -------------------
def cari_data(keyword=None, interaktif=True):
    if interaktif:
        keyword = input("Masukkan kode/nama barang yang dicari: ").strip()

    conn = get_connection()
    cur = conn.cursor()
    cur.execute(
        "SELECT * FROM barang WHERE kode LIKE ? OR nama_barang LIKE ?",
        (f"%{keyword}%", f"%{keyword}%"),
    )
    rows = cur.fetchall()
    conn.close()

    if not rows:
        print(f"Data dengan kata kunci '{keyword}' tidak ditemukan.")
        return rows
    cetak_tabel(rows)
    return rows


# ------------------- 4. UBAH DATA -------------------
def ubah_data(kode=None, nama=None, harga=None, stok=None, interaktif=True):
    if interaktif:
        kode = input("Masukkan kode barang yang akan diubah: ").strip().upper()

    conn = get_connection()
    cur = conn.cursor()
    cur.execute("SELECT * FROM barang WHERE kode = ?", (kode,))
    row = cur.fetchone()

    if not row:
        print(f"Data dengan kode '{kode}' tidak ditemukan.")
        conn.close()
        return False

    if interaktif:
        print(f"Data ditemukan: {row['nama_barang']} | Harga: {row['harga']} | Stok: {row['stok']}")
        nama = input(f"Nama Barang baru [{row['nama_barang']}]: ").strip() or row["nama_barang"]
        harga_input = input(f"Harga baru [{row['harga']}]: ").strip()
        stok_input = input(f"Stok baru [{row['stok']}]: ").strip()
        harga = int(harga_input) if harga_input else row["harga"]
        stok = int(stok_input) if stok_input else row["stok"]
    else:
        nama = nama or row["nama_barang"]
        harga = harga if harga is not None else row["harga"]
        stok = stok if stok is not None else row["stok"]

    cur.execute(
        "UPDATE barang SET nama_barang = ?, harga = ?, stok = ? WHERE kode = ?",
        (nama, harga, stok, kode),
    )
    conn.commit()
    conn.close()
    print(f"Data barang '{kode}' berhasil diperbarui.")
    return True


# ------------------- 5. HAPUS DATA -------------------
def hapus_data(kode=None, interaktif=True):
    if interaktif:
        kode = input("Masukkan kode barang yang akan dihapus: ").strip().upper()

    conn = get_connection()
    cur = conn.cursor()
    cur.execute("SELECT * FROM barang WHERE kode = ?", (kode,))
    row = cur.fetchone()

    if not row:
        print(f"Data dengan kode '{kode}' tidak ditemukan.")
        conn.close()
        return False

    if interaktif:
        konfirmasi = input(f"Yakin ingin menghapus '{row['nama_barang']}'? (y/n): ").strip().lower()
        if konfirmasi != "y":
            print("Penghapusan dibatalkan.")
            conn.close()
            return False

    cur.execute("DELETE FROM barang WHERE kode = ?", (kode,))
    conn.commit()
    conn.close()
    print(f"Data barang '{kode}' berhasil dihapus.")
    return True


def main():
    init_db()
    while True:
        cetak_menu()
        pilihan = input("Pilihan : ").strip()

        if pilihan == "1":
            tampil_semua_data()
        elif pilihan == "2":
            tambah_data()
        elif pilihan == "3":
            cari_data()
        elif pilihan == "4":
            ubah_data()
        elif pilihan == "5":
            hapus_data()
        elif pilihan == "0":
            print("Terima kasih telah menggunakan aplikasi Toko Retail.")
            break
        else:
            print("Pilihan tidak valid, silakan coba lagi.")

        print()


if __name__ == "__main__":
    main()
