
package DTO;

import java.util.Objects;

public class ChiTietPhieuXuatDTO {
    private int maPX;
    private int maSP;
    private int giaBan;
    private int soLuongSP;
    private String serialSp;

    public ChiTietPhieuXuatDTO() {
    }

    public ChiTietPhieuXuatDTO(int maPX, int maSP, int giaBan, int soLuongSP, String serialSp) {
        this.maPX = maPX;
        this.maSP = maSP;
        this.giaBan = giaBan;
        this.soLuongSP = soLuongSP;
        this.serialSp = serialSp;
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

    public String getSerialSp() {
        return serialSp;
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

    public void setSerialSp(String serialSp) {
        this.serialSp = serialSp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.maPX;
        hash = 17 * hash + this.maSP;
        hash = 17 * hash + this.giaBan;
        hash = 17 * hash + this.soLuongSP;
        hash = 17 * hash + Objects.hashCode(this.serialSp);
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
        final ChiTietPhieuXuatDTO other = (ChiTietPhieuXuatDTO) obj;
        if (this.maPX != other.maPX) {
            return false;
        }
        if (this.maSP != other.maSP) {
            return false;
        }
        if (this.giaBan != other.giaBan) {
            return false;
        }
        if (this.soLuongSP != other.soLuongSP) {
            return false;
        }
        return Objects.equals(this.serialSp, other.serialSp);
    }

    
}
