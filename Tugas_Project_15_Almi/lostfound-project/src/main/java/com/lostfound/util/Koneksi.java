package com.lostfound.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class utilitas untuk membuka koneksi ke database MySQL.
 * Sesuaikan URL, USER, dan PASSWORD dengan konfigurasi MySQL lokal.
 */
public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/lost_found?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC MySQL tidak ditemukan. Pastikan mysql-connector-j ada di classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
