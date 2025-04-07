package DTO;

public class ChucNangDTO {
    private int maCN;
    private String tenCN;
    private int trangThaiXoa;

    public ChucNangDTO() {}

    public ChucNangDTO(int maCN, String tenCN) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.trangThaiXoa = 0;
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

    public String getTenCN() {
        return tenCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }
}
