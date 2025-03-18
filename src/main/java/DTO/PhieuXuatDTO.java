
package DTO;

import java.util.Date;
import java.util.Objects;

public class PhieuXuatDTO {
    private int maPX;
    private int maNV;
    private int maKH;
    private int tongTIen;
    private Date ngayXuat;

    public PhieuXuatDTO() {
    }

    public PhieuXuatDTO(int maPX, int maNV, int maKH, int tongTIen, Date ngayXuat) {
        this.maPX = maPX;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTIen = tongTIen;
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

    public int getTongTIen() {
        return tongTIen;
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

    public void setTongTIen(int tongTIen) {
        this.tongTIen = tongTIen;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    @Override
    public String toString() {
        return "PhieuXuatDTO{" + "maPX=" + maPX + ", maNV=" + maNV + ", maKH=" + maKH + ", tongTIen=" + tongTIen + ", ngayXuat=" + ngayXuat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.maPX;
        hash = 37 * hash + this.maNV;
        hash = 37 * hash + this.maKH;
        hash = 37 * hash + this.tongTIen;
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
        if (this.tongTIen != other.tongTIen) {
            return false;
        }
        return Objects.equals(this.ngayXuat, other.ngayXuat);
    }
    
    
    
}
