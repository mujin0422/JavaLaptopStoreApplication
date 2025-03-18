package DTO;


public class ChiTietChucNangDTO {
    private int maCN;
    private int maQuyen;

    public ChiTietChucNangDTO() {
    }

    public ChiTietChucNangDTO(int maCN, int maQuyen) {
        this.maCN = maCN;
        this.maQuyen = maQuyen;
    }

    public int getMaCN() {
        return maCN;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.maCN;
        hash = 71 * hash + this.maQuyen;
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
        final ChiTietChucNangDTO other = (ChiTietChucNangDTO) obj;
        if (this.maCN != other.maCN) {
            return false;
        }
        return this.maQuyen == other.maQuyen;
    }

    
}
