package DTO;

import java.util.Objects;

public class DoPhanGiaiDTO {
    private int maDPG;
    private String tenDPG;

    public DoPhanGiaiDTO() {
    }

    public DoPhanGiaiDTO(int maDPG, String tenDPG) {
        this.maDPG = maDPG;
        this.tenDPG = tenDPG;
    }

    public int getMaDPG() {
        return maDPG;
    }

    public String getTenDPG() {
        return tenDPG;
    }

    public void setMaDPG(int maDPG) {
        this.maDPG = maDPG;
    }

    public void setTenDPG(String tenDPG) {
        this.tenDPG = tenDPG;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.maDPG;
        hash = 97 * hash + Objects.hashCode(this.tenDPG);
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
        final DoPhanGiaiDTO other = (DoPhanGiaiDTO) obj;
        if (this.maDPG != other.maDPG) {
            return false;
        }
        return Objects.equals(this.tenDPG, other.tenDPG);
    }
    
}
