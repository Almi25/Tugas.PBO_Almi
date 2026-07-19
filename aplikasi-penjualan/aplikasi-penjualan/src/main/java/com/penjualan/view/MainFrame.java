package com.penjualan.view;

import com.penjualan.model.User;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private User userLogin;

    public MainFrame(User userLogin) {
        this.userLogin = userLogin;

        setTitle("Dashboard - " + userLogin.getNama() + " (" + userLogin.getRole() + ")");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] menuItems = userLogin.getMenuItems(); // hasilnya beda tergantung Admin/Kasir

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(menuItems.length + 1, 1, 5, 5));

        for (String menu : menuItems) {
            JButton btn = new JButton(menu);
            btn.addActionListener(e -> bukaMenu(menu));
            panel.add(btn);
        }

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
        panel.add(btnLogout);

        add(panel);
        setVisible(true);
    }

    private void bukaMenu(String menu) {
        switch (menu) {
            case "Kelola User":
                new UserFrame();
                break;
            case "Kelola Barang":
                new BarangFrame();
                break;
            case "Kelola Pelanggan":
                new PelangganFrame();
                break;
            case "Transaksi Penjualan":
                new TransaksiFrame();
                break;
            case "Laporan Penjualan":
                new LaporanFrame();
                break;
        }
    }
}