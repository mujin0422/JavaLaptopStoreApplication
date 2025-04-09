package DTO;

import java.util.Objects;


public class KhachHangDTO {
    private int maKH;
    private String tenKH;
    private String sdt;
    private String email;
    private int trangThaiXoa;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int maKH, String tenKH, String sdt, String email) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.email = email;
        this.trangThaiXoa = 0;
    }

    public int getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

   
}
