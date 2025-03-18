package DTO;

import java.util.Objects;


public class NhanVienDTO {
    private int maNV;
    private int tenNV;
    private String taiKhoan;
    private String matKhau;
    private String email;
    private String sdt;
    private int maQuyen;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int maNV, int tenNV, String taiKhoan, String matKhau, String email, String sdt, int maQuyen) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.sdt = sdt;
        this.maQuyen = maQuyen;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getTenNV() {
        return tenNV;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getEmail() {
        return email;
    }

    public String getSdt() {
        return sdt;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(int tenNV) {
        this.tenNV = tenNV;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", email=" + email + ", sdt=" + sdt + ", maQuyen=" + maQuyen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.maNV;
        hash = 83 * hash + this.tenNV;
        hash = 83 * hash + Objects.hashCode(this.taiKhoan);
        hash = 83 * hash + Objects.hashCode(this.matKhau);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.sdt);
        hash = 83 * hash + this.maQuyen;
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
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (this.maNV != other.maNV) {
            return false;
        }
        if (this.tenNV != other.tenNV) {
            return false;
        }
        if (this.maQuyen != other.maQuyen) {
            return false;
        }
        if (!Objects.equals(this.taiKhoan, other.taiKhoan)) {
            return false;
        }
        if (!Objects.equals(this.matKhau, other.matKhau)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.sdt, other.sdt);
    }
    
    
}
