package DTO;

import java.util.Objects;


public class NhaCungCapDTO {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String sdt;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(int maNCC, String tenNCC, String diaChi, String sdt) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sdt=" + sdt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.maNCC;
        hash = 29 * hash + Objects.hashCode(this.tenNCC);
        hash = 29 * hash + Objects.hashCode(this.diaChi);
        hash = 29 * hash + Objects.hashCode(this.sdt);
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
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        if (this.maNCC != other.maNCC) {
            return false;
        }
        if (!Objects.equals(this.tenNCC, other.tenNCC)) {
            return false;
        }
        if (!Objects.equals(this.diaChi, other.diaChi)) {
            return false;
        }
        return Objects.equals(this.sdt, other.sdt);
    }
    
}
