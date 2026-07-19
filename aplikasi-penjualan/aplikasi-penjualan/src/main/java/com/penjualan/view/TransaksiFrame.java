package com.penjualan.view;

import com.penjualan.model.Barang;
import com.penjualan.model.DetailTransaksi;
import com.penjualan.model.Pelanggan;
import com.penjualan.service.BarangService;
import com.penjualan.service.PelangganService;
import com.penjualan.service.TransaksiService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiFrame extends JFrame {

    private BarangService barangService;
    private PelangganService pelangganService;
    private TransaksiService transaksiService;

    private JComboBox<String> cmbPelanggan, cmbBarang;
    private JTextField txtJumlah;
    private JLabel lblTotal;
    private JTable table;
    private DefaultTableModel model;

    private List<Pelanggan> daftarPelanggan;
    private List<Barang> daftarBarang;
    private List<DetailTransaksi> keranjang;
    private double totalHarga = 0;

    public TransaksiFrame() {
        barangService = new BarangService();
        pelangganService = new PelangganService();
        transaksiService = new TransaksiService();
        keranjang = new ArrayList<>();

        setTitle("Transaksi Penjualan");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel atas: pilih pelanggan & barang ---
        JPanel panelAtas = new JPanel(new GridLayout(3, 2, 5, 5));

        daftarPelanggan = pelangganService.getAllPelanggan();
        cmbPelanggan = new JComboBox<>();
        for (Pelanggan p : daftarPelanggan) {
            cmbPelanggan.addItem(p.getIdPelanggan() + " - " + p.getNamaPelanggan());
        }

        daftarBarang = barangService.getAllBarang();
        cmbBarang = new JComboBox<>();
        for (Barang b : daftarBarang) {
            cmbBarang.addItem(b.getIdBarang() + " - " + b.getNamaBarang() + " (Rp" + b.getHarga() + ")");
        }

        txtJumlah = new JTextField();
        JButton btnTambahKeranjang = new JButton("Tambah ke Keranjang");

        panelAtas.add(new JLabel("Pelanggan:"));
        panelAtas.add(cmbPelanggan);
        panelAtas.add(new JLabel("Barang:"));
        panelAtas.add(cmbBarang);
        panelAtas.add(new JLabel("Jumlah:"));
        panelAtas.add(txtJumlah);

        JPanel panelTombolAtas = new JPanel();
        panelTombolAtas.add(btnTambahKeranjang);

        JPanel panelUtara = new JPanel(new BorderLayout());
        panelUtara.add(panelAtas, BorderLayout.CENTER);
        panelUtara.add(panelTombolAtas, BorderLayout.SOUTH);

        // --- Tabel keranjang ---
        model = new DefaultTableModel(new String[]{"Barang", "Jumlah", "Subtotal"}, 0);
        table = new JTable(model);

        // --- Panel bawah: total + tombol simpan ---
        lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        JButton btnSimpan = new JButton("Simpan Transaksi");

        JPanel panelBawah = new JPanel(new BorderLayout());
        panelBawah.add(lblTotal, BorderLayout.WEST);
        panelBawah.add(btnSimpan, BorderLayout.EAST);

        add(panelUtara, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBawah, BorderLayout.SOUTH);

        btnTambahKeranjang.addActionListener(e -> tambahKeKeranjang());
        btnSimpan.addActionListener(e -> simpanTransaksi());

        setVisible(true);
    }

    private void tambahKeKeranjang() {
        try {
            int jumlah = Integer.parseInt(txtJumlah.getText());
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Jumlah harus lebih dari 0!");
                return;
            }

            int indexBarang = cmbBarang.getSelectedIndex();
            Barang barangDipilih = daftarBarang.get(indexBarang);

            if (jumlah > barangDipilih.getStok()) {
                JOptionPane.showMessageDialog(this, "Stok tidak cukup! Stok tersedia: " + barangDipilih.getStok());
                return;
            }

            double subtotal = barangDipilih.getHarga() * jumlah;

            // idTransaksi diisi 0 dulu (nanti DAO yang isi ID transaksi asli saat disimpan)
            DetailTransaksi detail = new DetailTransaksi(0, barangDipilih.getIdBarang(), jumlah, subtotal);
            keranjang.add(detail);

            model.addRow(new Object[]{barangDipilih.getNamaBarang(), jumlah, subtotal});

            totalHarga += subtotal;
            lblTotal.setText("Total: Rp " + totalHarga);

            txtJumlah.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
        }
    }

    private void simpanTransaksi() {
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
            return;
        }

        int indexPelanggan = cmbPelanggan.getSelectedIndex();
        int idPelanggan = daftarPelanggan.get(indexPelanggan).getIdPelanggan();

        boolean berhasil = transaksiService.buatTransaksi(idPelanggan, totalHarga, keranjang);

        if (berhasil) {
            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi!");
        }
    }
}