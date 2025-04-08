package DTO;

public class PhieuNhapDTO {
    private int maPN;
    private int maNV;
    private int maNCC;
    private int tongTien;
    private String ngayNhap;
    private int trangThaiXoa;

    public PhieuNhapDTO(int maPN, int maNV, int maNCC, int tongTien, String ngayNhap) {
        this.maPN = maPN;
        this.maNV = maNV;
        this.maNCC = maNCC;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.trangThaiXoa = 0;
    }

    public PhieuNhapDTO() {}

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaPN() {
        return maPN;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
