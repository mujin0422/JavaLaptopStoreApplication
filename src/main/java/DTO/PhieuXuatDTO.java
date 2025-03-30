
package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuXuatDTO {
    private int maPX;
    private int maNV;
    private int maKH;
    private int tongTien;
    private Date ngayXuat;

    public PhieuXuatDTO() {
    }

    public PhieuXuatDTO(int maPX, int maNV, int maKH, int tongTien, Date ngayXuat) {
        this.maPX = maPX;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ngayXuat = ngayXuat;
    }

    public int getMaPX() {
        return maPX;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" + "maPX=" + maPX + ", maNV=" + maNV + ", maKH=" + maKH + ", tongTIen=" + tongTien + ", ngayXuat=" + ngayXuat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.maPX;
        hash = 37 * hash + this.maNV;
        hash = 37 * hash + this.maKH;
        hash = 37 * hash + this.tongTien;
        hash = 37 * hash + Objects.hashCode(this.ngayXuat);
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
        final PhieuXuatDTO other = (PhieuXuatDTO) obj;
        if (this.maPX != other.maPX) {
            return false;
        }
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.maKH != other.maKH) {
            return false;
        }
        if (this.tongTien != other.tongTien) {
            return false;
        }
        return Objects.equals(this.ngayXuat, other.ngayXuat);
    }
    
    
    
}
