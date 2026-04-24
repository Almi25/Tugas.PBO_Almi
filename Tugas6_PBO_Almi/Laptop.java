package com.alat.elektronik;

/**
 * Interface Laptop
 * Mendefinisikan kontrak untuk semua jenis laptop
 */
public interface Laptop {
    void turnOn();
    void turnOff();
    void increaseVolume();
    void decreaseVolume();
    void displayStatus();
}