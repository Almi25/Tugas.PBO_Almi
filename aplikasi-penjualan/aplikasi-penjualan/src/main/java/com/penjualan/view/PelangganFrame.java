package com.penjualan.view;

import com.penjualan.model.Pelanggan;
import com.penjualan.service.PelangganService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PelangganFrame extends JFrame {

    private PelangganService pelangganService;
    private JTextField txtNama, txtAlamat, txtNoHp, txtCari;
    private JTable table;
    private DefaultTableModel model;
    private int idTerpilih = 0;

    public PelangganFrame() {
        pelangganService = new PelangganService();

        setTitle("Kelola Pelanggan");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 4, 5, 5));
        txtNama = new JTextField();
        txtAlamat = new JTextField();
        txtNoHp = new JTextField();
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBersih = new JButton("Bersihkan");

        form.add(new JLabel("Nama"));
        form.add(new JLabel("Alamat"));
        form.add(new JLabel("No. HP"));
        form.add(new JLabel());
        form.add(txtNama);
        form.add(txtAlamat);
        form.add(txtNoHp);
        form.add(btnBersih);

        JPanel panelTombol = new JPanel();
        panelTombol.add(btnTambah);
        panelTombol.add(btnUpdate);
        panelTombol.add(btnHapus);

        JPanel panelCari = new JPanel();
        txtCari = new JTextField(15);
        JButton btnCari = new JButton("Cari");
        JButton btnRefresh = new JButton("Tampilkan Semua");
        panelCari.add(new JLabel("Cari nama:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnRefresh);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(form, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        JPanel panelUtaraGabung = new JPanel(new BorderLayout());
        panelUtaraGabung.add(panelAtas, BorderLayout.NORTH);
        panelUtaraGabung.add(panelCari, BorderLayout.SOUTH);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "No HP"}, 0);
        table = new JTable(model);

        add(panelUtaraGabung, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnTambah.addActionListener(e -> {
            pelangganService.tambahPelanggan(txtNama.getText(), txtAlamat.getText(), txtNoHp.getText());
            tampilkanData();
            bersihkanForm();
        });

        btnUpdate.addActionListener(e -> {
            if (idTerpilih == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }
            Pelanggan p = new Pelanggan(idTerpilih, txtNama.getText(), txtAlamat.getText(), txtNoHp.getText());
            pelangganService.updatePelanggan(p);
            tampilkanData();
            bersihkanForm();
        });

        btnHapus.addActionListener(e -> {
            if (idTerpilih == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }
            pelangganService.hapusPelanggan(idTerpilih);
            tampilkanData();
            bersihkanForm();
        });

        btnBersih.addActionListener(e -> bersihkanForm());
        btnCari.addActionListener(e -> {
            model.setRowCount(0);
            List<Pelanggan> hasil = pelangganService.cariPelanggan(txtCari.getText());
            for (Pelanggan p : hasil) {
                model.addRow(new Object[]{p.getIdPelanggan(), p.getNamaPelanggan(), p.getAlamat(), p.getNoHp()});
            }
        });
        btnRefresh.addActionListener(e -> tampilkanData());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idTerpilih = (int) model.getValueAt(row, 0);
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtAlamat.setText(model.getValueAt(row, 2) == null ? "" : model.getValueAt(row, 2).toString());
                txtNoHp.setText(model.getValueAt(row, 3) == null ? "" : model.getValueAt(row, 3).toString());
            }
        });

        tampilkanData();
        setVisible(true);
    }

    private void tampilkanData() {
        model.setRowCount(0);
        List<Pelanggan> daftar = pelangganService.getAllPelanggan();
        for (Pelanggan p : daftar) {
            model.addRow(new Object[]{p.getIdPelanggan(), p.getNamaPelanggan(), p.getAlamat(), p.getNoHp()});
        }
    }

    private void bersihkanForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        idTerpilih = 0;
        table.clearSelection();
    }
}