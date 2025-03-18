package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuBaoHanhDTO {
    private int maPBH;
    private int maSP;
    private int maPX;
    private Date ngayTiepNhan;
    private String moTaLoi;
    private int trangThaiBH;
    private int maNVBH;

    public PhieuBaoHanhDTO() {
    }

    public PhieuBaoHanhDTO(int maPBH, int maSP, int maPX, Date ngayTiepNhan, String moTaLoi, int trangThaiBH, int maNVBH) {
        this.maPBH = maPBH;
        this.maSP = maSP;
        this.maPX = maPX;
        this.ngayTiepNhan = ngayTiepNhan;
        this.moTaLoi = moTaLoi;
        this.trangThaiBH = trangThaiBH;
        this.maNVBH = maNVBH;
    }

    public int getMaPBH() {
        return maPBH;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getMaPX() {
        return maPX;
    }

    public Date getNgayTiepNhan() {
        return ngayTiepNhan;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public int getTrangThaiBH() {
        return trangThaiBH;
    }

    public int getMaNVBH() {
        return maNVBH;
    }

    public void setMaPBH(int maPBH) {
        this.maPBH = maPBH;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setNgayTiepNhan(Date ngayTiepNhan) {
        this.ngayTiepNhan = ngayTiepNhan;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public void setTrangThaiBH(int trangThaiBH) {
        this.trangThaiBH = trangThaiBH;
    }

    public void setMaNVBH(int maNVBH) {
        this.maNVBH = maNVBH;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.maPBH;
        hash = 37 * hash + this.maSP;
        hash = 37 * hash + this.maPX;
        hash = 37 * hash + Objects.hashCode(this.ngayTiepNhan);
        hash = 37 * hash + Objects.hashCode(this.moTaLoi);
        hash = 37 * hash + this.trangThaiBH;
        hash = 37 * hash + this.maNVBH;
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
        final PhieuBaoHanhDTO other = (PhieuBaoHanhDTO) obj;
        if (this.maPBH != other.maPBH) {
            return false;
        }
        if (this.maSP != other.maSP) {
            return false;
        }
        if (this.maPX != other.maPX) {
            return false;
        }
        if (this.trangThaiBH != other.trangThaiBH) {
            return false;
        }
        if (this.maNVBH != other.maNVBH) {
            return false;
        }
        if (!Objects.equals(this.moTaLoi, other.moTaLoi)) {
            return false;
        }
        return Objects.equals(this.ngayTiepNhan, other.ngayTiepNhan);
    }
    
}
