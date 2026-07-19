package com.penjualan.view;

import com.penjualan.model.Admin;
import com.penjualan.model.Kasir;
import com.penjualan.model.User;
import com.penjualan.service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private UserService userService;

    public LoginFrame() {
        userService = new UserService();

        setTitle("Login - Aplikasi Penjualan");
        setSize(350, 220);
        setLocationRelativeTo(null); // buka di tengah layar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(new JLabel()); // pengisi kosong
        add(btnLogin);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User hasil = userService.login(username, password);

        if (hasil == null) {
            JOptionPane.showMessageDialog(this, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Berdasarkan role, buat objek Admin atau Kasir (polymorphism)
        User userLogin;
        if (hasil.getRole().equalsIgnoreCase("admin")) {
            userLogin = new Admin(hasil.getIdUser(), hasil.getUsername(), hasil.getPassword(), hasil.getNama(), hasil.getRole());
        } else {
            userLogin = new Kasir(hasil.getIdUser(), hasil.getUsername(), hasil.getPassword(), hasil.getNama(), hasil.getRole());
        }

        userLogin.tampilMenu(); // bukti polymorphism tercetak di console

        JOptionPane.showMessageDialog(this, "Login berhasil! Selamat datang, " + userLogin.getNama());
        new MainFrame(userLogin);
        dispose(); // tutup jendela login
    }
}