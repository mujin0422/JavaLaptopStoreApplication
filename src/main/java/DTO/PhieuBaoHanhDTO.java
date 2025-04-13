package DTO;

import java.util.Date;

public class PhieuBaoHanhDTO {
    private int maPBH;
    private String serialSP;
    private int maNVBH;
    private Date ngayTiepNhan;
    private String moTaLoi;
    private int trangThaiBH;
    private int trangThaiXoa;

    public PhieuBaoHanhDTO(int maPBH, String serialSP, int maNVBH, Date ngayTiepNhan, String moTaLoi, int trangThaiBH) {
        this.maPBH = maPBH;
        this.serialSP = serialSP;
        this.maNVBH = maNVBH;
        this.ngayTiepNhan = ngayTiepNhan;
        this.moTaLoi = moTaLoi;
        this.trangThaiBH = trangThaiBH;
        this.trangThaiXoa = 0;
    }

    public int getMaPBH() {
        return maPBH;
    }

    public void setMaPBH(int maPBH) {
        this.maPBH = maPBH;
    }

    public String getSerialSP() {
        return serialSP;
    }

    public void setSerialSP(String serialSP) {
        this.serialSP = serialSP;
    }

    public int getMaNVBH() {
        return maNVBH;
    }

    public void setMaNVBH(int maNVBH) {
        this.maNVBH = maNVBH;
    }

    public Date getNgayTiepNhan() {
        return ngayTiepNhan;
    }

    public void setNgayTiepNhan(Date ngayTiepNhan) {
        this.ngayTiepNhan = ngayTiepNhan;
    }

    public String getMoTaLoi() {
        return moTaLoi;
    }

    public void setMoTaLoi(String moTaLoi) {
        this.moTaLoi = moTaLoi;
    }

    public int getTrangThaiBH() {
        return trangThaiBH;
    }

    public void setTrangThaiBH(int trangThaiBH) {
        this.trangThaiBH = trangThaiBH;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }
    

    
}
