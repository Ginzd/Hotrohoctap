package com.example.hotrohoctap.model;

public class Student {
    private String maLop;
    private String maSinhvien;
    private String tenSinhVien;

    public Student() {
    }

    public Student(String maLop, String maSinhvien, String tenSinhVien) {
        this.maLop = maLop;
        this.maSinhvien = maSinhvien;
        this.tenSinhVien = tenSinhVien;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaSinhvien() {
        return maSinhvien;
    }

    public void setMaSinhvien(String maSinhvien) {
        this.maSinhvien = maSinhvien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }
}
