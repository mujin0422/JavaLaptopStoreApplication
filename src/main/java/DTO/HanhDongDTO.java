package DTO;

public class HanhDongDTO {
    private String maHD;
    private String tenHD;
    private int trangThaiXoa;

    public HanhDongDTO(String maHD, String tenHD) {
        this.maHD = maHD;
        this.tenHD = tenHD;
        this.trangThaiXoa = 0;
    }

    public HanhDongDTO() {
    }
 
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }
    
    
}
