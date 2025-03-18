package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuNhapDTO {
    private int maPN;
    private int maNV;
    private int maNCC;
    private int tongTien;
    private Date ngayNhap;

    public PhieuNhapDTO(int maPN, int maNV, int maNCC, int tongTien, Date ngayNhap) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
    }

    public PhieuNhapDTO() {
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public int getTongTien() {
        return tongTien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.maPN;
        hash = 83 * hash + this.maNV;
        hash = 83 * hash + this.maNCC;
        hash = 83 * hash + this.tongTien;
        hash = 83 * hash + Objects.hashCode(this.ngayNhap);
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
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        if (this.maPN != other.maPN) {
            return false;
        }
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.maNCC != other.maNCC) {
            return false;
        }
        if (this.tongTien != other.tongTien) {
            return false;
        }
        return Objects.equals(this.ngayNhap, other.ngayNhap);
    }
    
    
}
