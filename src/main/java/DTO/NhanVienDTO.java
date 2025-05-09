package DTO;

public class NhanVienDTO {
    private int maNV;
    private String tenNV;
    private String email;
    private String sdt;
    private String vaiTro;
    private int trangThaiXoa;

    public NhanVienDTO() {}

    public NhanVienDTO(int maNV, String tenNV, String sdt, String email, String vaiTro) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.email = email;
        this.sdt = sdt;
        this.vaiTro = vaiTro;
        this.trangThaiXoa = 0;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getEmail() {
        return email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
