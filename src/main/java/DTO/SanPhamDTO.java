package DTO;

public class SanPhamDTO {
    private int maSP;
    private String tenSP;
    private int giaSP;
    private int soLuongTon;
    private int maCPU;
    private int maRAM;
    private int maROM;
    private int maDPG;
    private int maLoai;
    private int maTH; 
    private int thoiGianBH;
    private int trangThaiXoa;

    public SanPhamDTO() {}

    public SanPhamDTO(int maSP, String tenSP, int giaSP, int soLuongTon, int maCPU, int maRAM, int maROM, int maDPG, int maLoai, int maTH, int thoiGianBH) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuongTon = soLuongTon;
        this.maCPU = maCPU;
        this.maRAM = maRAM;
        this.maROM = maROM;
        this.maDPG = maDPG;
        this.maLoai = maLoai;
        this.maTH = maTH;
        this.thoiGianBH = thoiGianBH;
        this.trangThaiXoa = 0;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public int getMaCPU() {
        return maCPU;
    }

    public void setMaCPU(int maCPU) {
        this.maCPU = maCPU;
    }

    public int getMaRAM() {
        return maRAM;
    }

    public void setMaRAM(int maRAM) {
        this.maRAM = maRAM;
    }

    public int getMaROM() {
        return maROM;
    }

    public void setMaROM(int maROM) {
        this.maROM = maROM;
    }

    public int getMaDPG() {
        return maDPG;
    }

    public void setMaDPG(int maDPG) {
        this.maDPG = maDPG;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public int getThoiGianBH() {
        return thoiGianBH;
    }

    public void setThoiGianBH(int thoiGianBH) {
        this.thoiGianBH = thoiGianBH;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    
}
