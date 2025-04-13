package DTO;

public class ChiTietSanPhamDTO {
    private String serialSP;
    private int maSP;
    private int maPN;
    private Integer maPX; 

    public ChiTietSanPhamDTO(String serialSP, int maSP, int maPN) {
        this.serialSP = serialSP;
        this.maSP = maSP;
        this.maPN = maPN;
        this.maPX = null;
    }

    public ChiTietSanPhamDTO(String serialSP, int maSP, int maPN, Integer maPX) {
        this.serialSP = serialSP;
        this.maSP = maSP;
        this.maPN = maPN;
        this.maPX = maPX;
    }

    public String getSerialSP() {
        return serialSP;
    }

    public void setSerialSP(String serialSP) {
        this.serialSP = serialSP;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public Integer getMaPX() {
        return maPX;
    }

    public void setMaPX(Integer maPX) {
        this.maPX = maPX;
    }    
}
