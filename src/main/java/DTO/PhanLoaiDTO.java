package DTO;

import java.util.Objects;

public class PhanLoaiDTO {
    private int maLoai;
    private String tenLoai;

    public PhanLoaiDTO() {
    }

    public PhanLoaiDTO(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.maLoai;
        hash = 67 * hash + Objects.hashCode(this.tenLoai);
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
        final PhanLoaiDTO other = (PhanLoaiDTO) obj;
        if (this.maLoai != other.maLoai) {
            return false;
        }
        return Objects.equals(this.tenLoai, other.tenLoai);
    }
    
}
