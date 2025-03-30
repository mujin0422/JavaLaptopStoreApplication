package DTO;

import java.util.Objects;


public class KhachHangDTO {
    private int maKH;
    private String tenKH;
    private String sdt;
    private String email;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int maKH, String tenKH, String sdt, String email) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.email = email;
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

    @Override
    public String toString() {
        return "KhachHangDTO{" + "maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", email=" + email + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KhachHangDTO other = (KhachHangDTO) obj;
        if (this.maKH != other.maKH) {
            return false;
        }
        if (this.tenKH != other.tenKH) {
            return false;
        }
        if (!Objects.equals(this.sdt, other.sdt)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }
    
}
