package DTO;

public class DoPhanGiaiDTO {
    private int maDPG;
    private String tenDPG;
    private int trangThaiXoa;

    public DoPhanGiaiDTO() { }

    public DoPhanGiaiDTO(int maDPG, String tenDPG) {
        this.maDPG = maDPG;
        this.tenDPG = tenDPG;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaDPG() {
        return maDPG;
    }

    public String getTenDPG() {
        return tenDPG;
    }

    public void setMaDPG(int maDPG) {
        this.maDPG = maDPG;
    }

    public void setTenDPG(String tenDPG) {
        this.tenDPG = tenDPG;
    }
}
