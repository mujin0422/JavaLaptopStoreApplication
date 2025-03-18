package DTO;

import java.util.Objects;

public class ChucNangDTO {
    private int maCN;
    private String tenCN;

    public ChucNangDTO() {
    }

    public ChucNangDTO(int maCN, String tenCN) {
        this.maCN = maCN;
        this.tenCN = tenCN;
    }

    public int getMaCN() {
        return maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    @Override
    public String toString() {
        return "ChucNangDTO{" + "maCN=" + maCN + ", tenCN=" + tenCN + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.maCN;
        hash = 97 * hash + Objects.hashCode(this.tenCN);
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
        final ChucNangDTO other = (ChucNangDTO) obj;
        if (this.maCN != other.maCN) {
            return false;
        }
        return Objects.equals(this.tenCN, other.tenCN);
    }
    
}
