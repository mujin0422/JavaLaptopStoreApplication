package DTO;

import java.util.Objects;

public class QuyenDTO {
    private int maQuyen;
    private String tenQuyen;

    public QuyenDTO() {
    }

    public QuyenDTO(int maQuyen, String tenQuyen) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    @Override
    public String toString() {
        return "QuyenDTO{" + "maQuyen=" + maQuyen + ", tenQuyen=" + tenQuyen + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.maQuyen;
        hash = 73 * hash + Objects.hashCode(this.tenQuyen);
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
        final QuyenDTO other = (QuyenDTO) obj;
        if (this.maQuyen != other.maQuyen) {
            return false;
        }
        return Objects.equals(this.tenQuyen, other.tenQuyen);
    }
    
    
}
