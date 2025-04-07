package DTO;

public class CpuDTO {
    private int maCPU;
    private String tenCPU;
    private int trangThaiXoa;

    public CpuDTO() { }

    public CpuDTO(int maCPU, String tenCPU) {
        this.maCPU = maCPU;
        this.tenCPU = tenCPU;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaCPU() {
        return maCPU;
    }

    public String getTenCPU() {
        return tenCPU;
    }

    public void setMaCPU(int maCPU) {
        this.maCPU = maCPU;
    }

    public void setTenCPU(String tenCPU) {
        this.tenCPU = tenCPU;
    }
}
