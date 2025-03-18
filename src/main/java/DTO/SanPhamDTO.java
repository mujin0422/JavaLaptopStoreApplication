/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Dell Vostro
 */
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
    private int thoiGianBH; 

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSP, String tenSP, int giaSP, int soLuongTon, int maCPU, int maRAM, int maROM, int maDPG, int maLoai, int thoiGianBH) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongTon = soLuongTon;
        this.maCPU = maCPU;
        this.maRAM = maRAM;
        this.maROM = maROM;
        this.maDPG = maDPG;
        this.maLoai = maLoai;
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

    public void setThoiGianBH(int thoiGianBH) {
        this.thoiGianBH = thoiGianBH;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.maSP;
        hash = 41 * hash + Objects.hashCode(this.tenSP);
        hash = 41 * hash + this.giaSP;
        hash = 41 * hash + this.soLuongTon;
        hash = 41 * hash + this.maCPU;
        hash = 41 * hash + this.maRAM;
        hash = 41 * hash + this.maROM;
        hash = 41 * hash + this.maDPG;
        hash = 41 * hash + this.maLoai;
        hash = 41 * hash + this.thoiGianBH;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.maSP != other.maSP) {
            return false;
        }
        if (this.giaSP != other.giaSP) {
            return false;
        }
        if (this.soLuongTon != other.soLuongTon) {
            return false;
        }
        if (this.maCPU != other.maCPU) {
            return false;
        }
        if (this.maRAM != other.maRAM) {
            return false;
        }
        if (this.maROM != other.maROM) {
            return false;
        }
        if (this.maDPG != other.maDPG) {
            return false;
        }
        if (this.maLoai != other.maLoai) {
            return false;
        }
        if (this.thoiGianBH != other.thoiGianBH) {
            return false;
        }
        return Objects.equals(this.tenSP, other.tenSP);
    }

    
}
