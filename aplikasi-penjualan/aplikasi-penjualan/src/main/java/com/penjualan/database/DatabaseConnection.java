package com.penjualan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static Connection connection = null;
    
    private static final String URL = "jdbc:mysql://localhost:3306/db_penjualan";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // isi sesuai password MySQL kamu
    
    private DatabaseConnection() {
    }
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi ke database berhasil.");
            }
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal: " + e.getMessage());
        }
        return connection;
    }
}