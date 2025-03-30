
package DTO;

import java.util.Objects;

public class ChiTietPhieuXuatDTO {
    private int maPX;
    private int maSP;
    private int giaBan;
    private int soLuongSP;
    private String serialSP;

    public ChiTietPhieuXuatDTO() {
    }

    public ChiTietPhieuXuatDTO(int maPX, int maSP, int giaBan, int soLuongSP, String serialSp) {
        this.maPX = maPX;
        this.maSP = maSP;
        this.giaBan = giaBan;
        this.soLuongSP = soLuongSP;
        this.serialSP = serialSp;
    }

    public int getMaPX() {
        return maPX;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public String getSerialSP() {
        return serialSP;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public void setSerialSP(String serialSp) {
        this.serialSP = serialSp;
    }

}
