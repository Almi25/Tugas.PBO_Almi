package com.penjualan.service;

import com.penjualan.dao.LaporanDAO;
import com.penjualan.model.LaporanPenjualan;

import java.util.List;

public class LaporanService {
    private LaporanDAO laporanDAO;

    public LaporanService() {
        this.laporanDAO = new LaporanDAO();
    }

    public List<LaporanPenjualan> getLaporan() {
        return laporanDAO.getLaporan();
    }
}