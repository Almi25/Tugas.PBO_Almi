package com.penjualan.view;

import com.penjualan.model.User;
import com.penjualan.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserFrame extends JFrame {

    private UserService userService;
    private JTextField txtUsername, txtPassword, txtNama;
    private JComboBox<String> cmbRole;
    private JTable table;
    private DefaultTableModel model;
    private int idTerpilih = 0;

    public UserFrame() {
        userService = new UserService();

        setTitle("Kelola User");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(2, 5, 5, 5));
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtNama = new JTextField();
        cmbRole = new JComboBox<>(new String[]{"admin", "kasir"});
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnBersih = new JButton("Bersihkan");

        form.add(new JLabel("Username"));
        form.add(new JLabel("Password"));
        form.add(new JLabel("Nama"));
        form.add(new JLabel("Role"));
        form.add(new JLabel());
        form.add(txtUsername);
        form.add(txtPassword);
        form.add(txtNama);
        form.add(cmbRole);
        form.add(btnBersih);

        JPanel panelTombol = new JPanel();
        panelTombol.add(btnTambah);
        panelTombol.add(btnUpdate);
        panelTombol.add(btnHapus);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(form, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        model = new DefaultTableModel(new String[]{"ID", "Username", "Password", "Nama", "Role"}, 0);
        table = new JTable(model);

        add(panelAtas, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnTambah.addActionListener(e -> {
            userService.tambahUser(txtUsername.getText(), txtPassword.getText(), txtNama.getText(), (String) cmbRole.getSelectedItem());
            tampilkanData();
            bersihkanForm();
        });

        btnUpdate.addActionListener(e -> {
            if (idTerpilih == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }
            User u = new User(idTerpilih, txtUsername.getText(), txtPassword.getText(), txtNama.getText(), (String) cmbRole.getSelectedItem());
            userService.updateUser(u);
            tampilkanData();
            bersihkanForm();
        });

        btnHapus.addActionListener(e -> {
            if (idTerpilih == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu!");
                return;
            }
            userService.hapusUser(idTerpilih);
            tampilkanData();
            bersihkanForm();
        });

        btnBersih.addActionListener(e -> bersihkanForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idTerpilih = (int) model.getValueAt(row, 0);
                txtUsername.setText(model.getValueAt(row, 1).toString());
                txtPassword.setText(model.getValueAt(row, 2).toString());
                txtNama.setText(model.getValueAt(row, 3).toString());
                cmbRole.setSelectedItem(model.getValueAt(row, 4).toString());
            }
        });

        tampilkanData();
        setVisible(true);
    }

    private void tampilkanData() {
        model.setRowCount(0);
        List<User> daftar = userService.getAllUser();
        for (User u : daftar) {
            model.addRow(new Object[]{u.getIdUser(), u.getUsername(), u.getPassword(), u.getNama(), u.getRole()});
        }
    }

    private void bersihkanForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtNama.setText("");
        idTerpilih = 0;
        table.clearSelection();
    }
}