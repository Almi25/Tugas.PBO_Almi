package com.penjualan.model;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String nama;
    private String role;

    // Constructor lengkap (data dari database)
    public User(int idUser, String username, String password, String nama, String role) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.role = role;
    }

    // Constructor tanpa id (data baru dari form)
    public User(String username, String password, String nama, String role) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.role = role;
    }

    // GETTER
    public int getIdUser() { 
        return idUser; 
    }
    public String getUsername() { 
        return username; 
    }
    public String getPassword() { 
        return password; 
    }
    public String getNama() { 
        return nama;
    }
    public String getRole() { 
        return role; 
    }

    // SETTER
    public void setIdUser(int idUser) { 
        this.idUser = idUser; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }
    public void setRole(String role) { 
        this.role = role; 
    }

    public void tampilMenu() {
        System.out.println("=== MENU USER ===");
    }

    public String[] getMenuItems() {
    return new String[]{};
}
}
