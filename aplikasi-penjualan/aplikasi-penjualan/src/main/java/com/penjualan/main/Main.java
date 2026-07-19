package com.penjualan.main;

import com.penjualan.view.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}