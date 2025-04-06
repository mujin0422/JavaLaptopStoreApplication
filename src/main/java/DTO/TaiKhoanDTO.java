package DTO;

import java.util.Objects;

/**
 *
 * @author Dell Vostro
 */
public class TaiKhoanDTO {
    private String tenDangNhap;
    private String matKhau;
    private int maQuyen;
    private String maNV;

    public TaiKhoanDTO(String tenDangNhap, String matKhau, int maQuyen, String maNV) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maQuyen = maQuyen;
        this.maNV = maNV;
    }

    public TaiKhoanDTO() {
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.tenDangNhap);
        hash = 71 * hash + Objects.hashCode(this.matKhau);
        hash = 71 * hash + this.maQuyen;
        hash = 71 * hash + Objects.hashCode(this.maNV);
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
        final TaiKhoanDTO other = (TaiKhoanDTO) obj;
        if (this.maQuyen != other.maQuyen) {
            return false;
        }
        if (this.maNV != other.maNV) {
            return false;
        }
        if (!Objects.equals(this.tenDangNhap, other.tenDangNhap)) {
            return false;
        }
        return Objects.equals(this.matKhau, other.matKhau);
    }
    
    
}
