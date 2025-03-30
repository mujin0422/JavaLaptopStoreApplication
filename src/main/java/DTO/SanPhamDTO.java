package DTO;

import java.util.Objects;

public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private int giaSP;
    private int soLuongTon;
    private int maCPU;
    private int maRAM;
    private int maROM;
    private int maDPG;
    private int maLoai;
    private int maTH; // Mã thương hiệu
    private int thoiGianBH;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSP, String tenSP, int giaSP, int soLuongTon, int maCPU, int maRAM, int maROM, int maDPG, int maLoai, int maTH, int thoiGianBH) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongTon = soLuongTon;
        this.maCPU = maCPU;
        this.maRAM = maRAM;
        this.maROM = maROM;
        this.maDPG = maDPG;
        this.maLoai = maLoai;
        this.maTH = maTH;
        this.thoiGianBH = thoiGianBH;
    }

    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public int getMaCPU() {
        return maCPU;
    }

    public int getMaRAM() {
        return maRAM;
    }

    public int getMaROM() {
        return maROM;
    }

    public int getMaDPG() {
        return maDPG;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public int getMaTH() {
        return maTH;
    }

    public int getThoiGianBH() {
        return thoiGianBH;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public void setMaCPU(int maCPU) {
        this.maCPU = maCPU;
    }

    public void setMaRAM(int maRAM) {
        this.maRAM = maRAM;
    }

    public void setMaROM(int maROM) {
        this.maROM = maROM;
    }

    public void setMaDPG(int maDPG) {
        this.maDPG = maDPG;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public void setThoiGianBH(int thoiGianBH) {
        this.thoiGianBH = thoiGianBH;
    }
}