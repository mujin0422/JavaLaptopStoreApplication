package DTO;

import java.util.Date;

public class PhieuXuatDTO {
    private int maPX;
    private int maNV;
    private int maKH;
    private int tongTien;
    private String ngayXuat;
    private int trangThaiXoa;

    public PhieuXuatDTO() {
    }

    public PhieuXuatDTO(int maPX, int maNV, int maKH, int tongTien, String ngayXuat) {
        this.maPX = maPX;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
        this.ngayXuat = ngayXuat;
        this.trangThaiXoa = 0;
    }

    public int getMaPX() {
        return maPX;
    }

    public int getMaNV() {
        return maNV;
    }

    public int getMaKH() {
        return maKH;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }
}
