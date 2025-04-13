package DTO;

public class ChiTietPhieuNhapDTO {
    private int maPN;
    private int maSP;
    private int soLuongSP;
    private int giaNhap;

    public ChiTietPhieuNhapDTO() { }

    public ChiTietPhieuNhapDTO(int maPN, int maSP, int soLuong, int giaNhap) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuongSP = soLuong;
        this.giaNhap = giaNhap;
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public int getGiaNhap() {
        return giaNhap;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setSoLuong(int soLuong) {
        this.soLuongSP = soLuong;
    }

    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }
}
