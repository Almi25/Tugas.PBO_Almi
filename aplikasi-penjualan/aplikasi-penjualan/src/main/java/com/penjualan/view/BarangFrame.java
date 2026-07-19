package com.penjualan.view;

import com.penjualan.model.Barang;
import com.penjualan.service.BarangService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BarangFrame extends JFrame {

    private BarangService barangService;
    private JTextField txtNama, txtKategori, txtHarga, txtStok, txtCari;
    private JTable table;
    private DefaultTableModel model;
    private int idTerpilih = 0;

    public BarangFrame() {
        barangService = new BarangService();

        setTitle("Kelola Barang");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel Form (atas) ---
        JPanel form = new JPanel(new GridLayout(2, 5, 5, 5));
        txtNama = new JTextField();
        txtKategori = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBersih = new JButton("Bersihkan Form");

        form.add(new JLabel("Nama Barang"));
        form.add(new JLabel("Kategori"));
        form.add(new JLabel("Harga"));
        form.add(new JLabel("Stok"));
        form.add(new JLabel());
        form.add(txtNama);
        form.add(txtKategori);
        form.add(txtHarga);
        form.add(txtStok);
        form.add(btnBersih);

        JPanel panelTombol = new JPanel();
        panelTombol.add(btnTambah);
        panelTombol.add(btnUpdate);
        panelTombol.add(btnHapus);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(form, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // --- Panel Cari ---
        JPanel panelCari = new JPanel();
        txtCari = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        JButton btnRefresh = new JButton("Tampilkan Semua");
        panelCari.add(new JLabel("Cari nama barang:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);

        JPanel panelUtaraGabung = new JPanel(new BorderLayout());
        panelUtaraGabung.add(panelAtas, BorderLayout.NORTH);
        panelUtaraGabung.add(panelCari, BorderLayout.SOUTH);

        // --- Tabel ---
        model = new DefaultTableModel(new String[]{"ID", "Nama", "Kategori", "Harga", "Stok"}, 0);
        table = new JTable(model);

        add(panelUtaraGabung, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Event ---
        btnTambah.addActionListener(e -> tambah());
        btnUpdate.addActionListener(e -> update());
        btnHapus.addActionListener(e -> hapus());
        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> cari());
        btnRefresh.addActionListener(e -> tampilkanData());

        table.getSelectionModel().addListSelectionListener(e -> pilihBaris());

        tampilkanData();
        setVisible(true);
    }

    private void tampilkanData() {
        model.setRowCount(0); // kosongkan tabel dulu
        List<Barang> daftar = barangService.getAllBarang();
        for (Barang b : daftar) {
            model.addRow(new Object[]{b.getIdBarang(), b.getNamaBarang(), b.getKategori(), b.getHarga(), b.getStok()});
        }
    }

    private void cari() {
        model.setRowCount(0);
        List<Barang> hasil = barangService.cariBarang(txtCari.getText());
        for (Barang b : hasil) {
            model.addRow(new Object[]{b.getIdBarang(), b.getNamaBarang(), b.getKategori(), b.getHarga(), b.getStok()});
        }
    }

    private void tambah() {
        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            barangService.tambahBarang(txtNama.getText(), txtKategori.getText(), harga, stok);
            tampilkanData();
            bersihkanForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!");
        }
    }

    private void update() {
        if (idTerpilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel dulu!");
            return;
        }
        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            Barang barang = new Barang(idTerpilih, txtNama.getText(), txtKategori.getText(), harga, stok);
            barangService.updateBarang(barang);
            tampilkanData();
            bersihkanForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka!");
        }
    }

    private void hapus() {
        if (idTerpilih == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel dulu!");
            return;
        }
        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus barang ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            barangService.hapusBarang(idTerpilih);
            tampilkanData();
            bersihkanForm();
        }
    }

    private void pilihBaris() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            idTerpilih = (int) model.getValueAt(row, 0);
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtKategori.setText(model.getValueAt(row, 2).toString());
            txtHarga.setText(model.getValueAt(row, 3).toString());
            txtStok.setText(model.getValueAt(row, 4).toString());
        }
    }

    private void bersihkanForm() {
        txtNama.setText("");
        txtKategori.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        idTerpilih = 0;
        table.clearSelection();
    }
}