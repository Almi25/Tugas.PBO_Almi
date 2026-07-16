package com.lostfound.exception;

/**
 * Dilempar ketika proses klaim tidak dapat dilakukan,
 * misalnya barang sudah pernah diklaim sebelumnya.
 */
public class StatusKlaimTidakValidException extends Exception {
    public StatusKlaimTidakValidException(String message) {
        super(message);
    }
}
