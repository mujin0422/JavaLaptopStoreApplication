package DTO;

public class ChiTietChucNangDTO {
    private int maCN;
    private int maQuyen;
    private String maHD;
    private int trangThaiXoa;
    
    public ChiTietChucNangDTO() {}

    public ChiTietChucNangDTO(int maCN, int maQuyen, String maHD) {
        this.maCN = maCN;
        this.maQuyen = maQuyen;
        this.maHD = maHD;
        this.trangThaiXoa = 0;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
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
}
