package DTO;

public class ChiTietPhieuXuatDTO {
    private int maPX;
    private int maSP;
    private int giaBan;
    private int soLuongSP;

    public ChiTietPhieuXuatDTO(int maPX, int maSP, int giaBan, int soLuongSP) {
        this.maPX = maPX;
        this.maSP = maSP;
        this.giaBan = giaBan;
        this.soLuongSP = soLuongSP;
    }

    public int getMaPX() {
        return maPX;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    

}
