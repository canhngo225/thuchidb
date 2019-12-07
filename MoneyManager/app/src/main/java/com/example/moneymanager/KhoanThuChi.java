package com.example.moneymanager;

public class KhoanThuChi {
    private String mNoiDung;
    private String mSoTien;


    public KhoanThuChi(String mNoiDung, String mSoTien) {
        this.mNoiDung = mNoiDung;
        this.mSoTien = mSoTien;
    }

    public KhoanThuChi() {
    }

    public String getmNoiDung() {
        return mNoiDung;
    }

    public void setmNoiDung(String mNoiDung) {
        this.mNoiDung = mNoiDung;
    }

    public String getmSoTien() {
        return mSoTien;
    }

    public void setmSoTien(String mSoTien) {
        this.mSoTien = mSoTien;
    }
}
