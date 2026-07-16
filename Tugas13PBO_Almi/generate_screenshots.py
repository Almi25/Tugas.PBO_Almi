"""
Menjalankan setiap opsi menu (1-5) dan menghasilkan screenshot PNG
bergaya terminal untuk keperluan tugas (Screenshoot semua tampilan menu).
"""

import io
import os
from contextlib import redirect_stdout

import main as app
from render_screenshot import render

OUT_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "screenshots")
os.makedirs(OUT_DIR, exist_ok=True)

# Selalu mulai dari database yang bersih & konsisten
DB_PATH = app.DB_NAME
if os.path.exists(DB_PATH):
    os.remove(DB_PATH)
app.init_db()


def capture(func, *args, **kwargs):
    buf = io.StringIO()
    with redirect_stdout(buf):
        func(*args, **kwargs)
    return buf.getvalue().rstrip("\n").split("\n")


def menu_lines():
    return capture(app.cetak_menu)


def build_screenshot(filename, pilihan, prompt_lines, action_lines, title):
    lines = []
    lines += menu_lines()
    lines.append(f"Pilihan : {pilihan}")
    lines += prompt_lines
    lines += action_lines
    lines.append("")
    lines += menu_lines()
    lines.append("Pilihan : ")
    render(lines, os.path.join(OUT_DIR, filename), title=title)


# ---------- Menu 1: Tampil Semua Data ----------
out = capture(app.tampil_semua_data)
build_screenshot(
    "menu_1_tampil_semua_data.png",
    "1",
    [],
    out,
    "Menu 1 - Tampil Semua Data",
)

# ---------- Menu 2: Tambah Data ----------
prompt_lines_2 = [
    "Kode Barang  : B004",
    "Nama Barang  : Gula Pasir",
    "Harga        : 15000",
    "Stok         : 50",
]
out = capture(app.tambah_data, kode="B004", nama="Gula Pasir", harga="15000", stok="50", interaktif=False)
build_screenshot(
    "menu_2_tambah_data.png",
    "2",
    prompt_lines_2,
    out,
    "Menu 2 - Tambah Data",
)

# ---------- Menu 3: Cari Data ----------
prompt_lines_3 = ["Masukkan kode/nama barang yang dicari: Kopi"]
out = capture(app.cari_data, keyword="Kopi", interaktif=False)
build_screenshot(
    "menu_3_cari_data.png",
    "3",
    prompt_lines_3,
    out,
    "Menu 3 - Cari Data",
)

# ---------- Menu 4: Ubah Data ----------
prompt_lines_4 = [
    "Masukkan kode barang yang akan diubah: B001",
    "Data ditemukan: Roti Tawar | Harga: 10000 | Stok: 100",
    "Nama Barang baru [Roti Tawar]: Roti Tawar Gandum",
    "Harga baru [10000]: 11000",
    "Stok baru [100]: 80",
]
out = capture(app.ubah_data, kode="B001", nama="Roti Tawar Gandum", harga=11000, stok=80, interaktif=False)
build_screenshot(
    "menu_4_ubah_data.png",
    "4",
    prompt_lines_4,
    out,
    "Menu 4 - Ubah Data",
)

# ---------- Menu 5: Hapus Data ----------
prompt_lines_5 = [
    "Masukkan kode barang yang akan dihapus: B002",
    "Yakin ingin menghapus 'Malkist'? (y/n): y",
]
out = capture(app.hapus_data, kode="B002", interaktif=False)
build_screenshot(
    "menu_5_hapus_data.png",
    "5",
    prompt_lines_5,
    out,
    "Menu 5 - Hapus Data",
)

print("Semua screenshot berhasil dibuat di folder:", OUT_DIR)
