package DTO;

public class QuyenDTO {
    private int maQuyen;
    private String tenQuyen;
    private int trangThaiXoa;

    public QuyenDTO() {
    }

    public QuyenDTO(int maQuyen, String tenQuyen) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }
}
