package DTO;

import java.util.Date;

public class PhieuBaoHanhDTO {
    private int maPBH;
    private int maSP;
    private int maPX;
    private String serialSP;
    private Date ngayTiepNhan;
    private String moTaLoi;
    private int trangThaiBH;
    private int maNVBH;
    private int trangThaiXoa;

    public PhieuBaoHanhDTO() {
    }

    public PhieuBaoHanhDTO(int maPBH, int maSP, int maPX,String serialSP, Date ngayTiepNhan, String moTaLoi, int trangThaiBH, int maNVBH) {
        this.maPBH = maPBH;
        this.maSP = maSP;
        this.maPX = maPX;
        this.serialSP = serialSP;
        this.ngayTiepNhan = ngayTiepNhan;
        this.moTaLoi = moTaLoi;
        this.trangThaiBH = trangThaiBH;
        this.maNVBH = maNVBH;
        this.trangThaiXoa = 0;
    }

    public String getSerialSP() {
        return serialSP;
    }

    public void setSerialSP(String serialSP) {
        this.serialSP = serialSP;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaPBH() {
        return maPBH;
    }

    public int getMaSP() {
        return maSP;
    }

    public int getMaPX() {
        return maPX;
    }

    public Date getNgayTiepNhan() {
        return ngayTiepNhan;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public int getTrangThaiBH() {
        return trangThaiBH;
    }

    public int getMaNVBH() {
        return maNVBH;
    }

    public void setMaPBH(int maPBH) {
        this.maPBH = maPBH;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public void setMaPX(int maPX) {
        this.maPX = maPX;
    }

    public void setNgayTiepNhan(Date ngayTiepNhan) {
        this.ngayTiepNhan = ngayTiepNhan;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public void setTrangThaiBH(int trangThaiBH) {
        this.trangThaiBH = trangThaiBH;
    }

    public void setMaNVBH(int maNVBH) {
        this.maNVBH = maNVBH;
    }
}
