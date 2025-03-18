/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Dell Vostro
 */
public class ChiTietPhieuNhapDTO {
    private int maPN;
    private int maSP;
    private int soLuong;
    private int giaNhap;

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maPN, int maSP, int soLuong, int giaNhap) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.maPN;
        hash = 59 * hash + this.maSP;
        hash = 59 * hash + this.soLuong;
        hash = 59 * hash + this.giaNhap;
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
        final ChiTietPhieuNhapDTO other = (ChiTietPhieuNhapDTO) obj;
        if (this.maPN != other.maPN) {
            return false;
        }
        if (this.maSP != other.maSP) {
            return false;
        }
        if (this.soLuong != other.soLuong) {
            return false;
        }
        return this.giaNhap == other.giaNhap;
    }

  
}
