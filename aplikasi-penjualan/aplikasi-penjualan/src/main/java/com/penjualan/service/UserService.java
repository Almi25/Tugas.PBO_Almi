package com.penjualan.service;

import com.penjualan.dao.UserDAO;
import com.penjualan.model.User;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Login return User kalau berhasil, null kalau gagal
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username tidak boleh kosong!");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password tidak boleh kosong!");
            return null;
        }
        return userDAO.login(username, password);
    }

    public void tambahUser(String username, String password, String nama, String role) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username tidak boleh kosong!");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password tidak boleh kosong!");
            return;
        }
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        User user = new User(username, password, nama, role);
        userDAO.tambah(user);
    }

    public List<User> getAllUser() {
        return userDAO.getAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void hapusUser(int idUser) {
        userDAO.hapus(idUser);
    }
}