package DTO;

public class ThuongHieuDTO {
    private int maTH;
    private String tenTH;
    private int trangThaiXoa;

    public ThuongHieuDTO() {}

    public ThuongHieuDTO(int maTH, String tenTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }    
}
