package DTO;

import java.util.Date;

public class PhieuXuatDTO {
    private int maPX;
    private int maNV;
    private int maKH;
    private int tongTien;
    private Date ngayXuat;
    private int trangThaiXoa;

    public PhieuXuatDTO() {
    }

    public PhieuXuatDTO(int maPX, int maNV, int maKH, int tongTien, Date ngayXuat) {
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

    public Date getNgayXuat() {
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

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
    
}
