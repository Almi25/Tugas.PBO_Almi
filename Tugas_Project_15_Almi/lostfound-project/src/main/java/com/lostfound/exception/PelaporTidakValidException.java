package com.lostfound.exception;

/**
 * Dilempar ketika data pelapor yang diinput tidak valid,
 * misalnya tipe pelapor selain Mahasiswa/Staf.
 */
public class PelaporTidakValidException extends Exception {
    public PelaporTidakValidException(String message) {
        super(message);
    }
}
