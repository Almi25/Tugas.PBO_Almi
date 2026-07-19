package com.penjualan.view;

import com.penjualan.model.LaporanPenjualan;
import com.penjualan.service.LaporanService;
import com.penjualan.service.TransaksiService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LaporanFrame extends JFrame {

    public LaporanFrame() {
        LaporanService laporanService = new LaporanService();
        TransaksiService transaksiService = new TransaksiService();

        setTitle("Laporan Penjualan");
        setSize(750, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID Transaksi", "Tanggal", "Pelanggan", "Barang", "Jumlah", "Subtotal", "Total Transaksi"}, 0);
        JTable table = new JTable(model);

        List<LaporanPenjualan> daftar = laporanService.getLaporan();
        for (LaporanPenjualan l : daftar) {
            model.addRow(new Object[]{
                l.getIdTransaksi(), l.getTanggal(), l.getNamaPelanggan(),
                l.getNamaBarang(), l.getJumlah(), l.getSubtotal(), l.getTotalHarga()
            });
        }

        double totalPendapatan = transaksiService.getTotalPendapatan();
        JLabel lblTotal = new JLabel("Total Pendapatan Keseluruhan: Rp " + totalPendapatan);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblTotal, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }
}