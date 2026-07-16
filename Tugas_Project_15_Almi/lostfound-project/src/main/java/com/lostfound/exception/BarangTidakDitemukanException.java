package com.lostfound.exception;

/**
 * Dilempar ketika id barang yang dicari tidak ditemukan di database.
 */
public class BarangTidakDitemukanException extends Exception {
    public BarangTidakDitemukanException(String message) {
        super(message);
    }
}
