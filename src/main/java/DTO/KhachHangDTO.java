package DTO;

import java.util.Objects;

public class KhachHangDTO {
    private String maKH;
    private String tenKH;
    private String sdt;
    private String email;

    public KhachHangDTO() {}

    public KhachHangDTO(String maKH, String tenKH, String sdt, String email) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.email = email;
    }

    // Getter và Setter
    public String getMaKH() { return maKH; }
    public String getTenKH() { return tenKH; }
    public String getSdt() { return sdt; }
    public String getEmail() { return email; }
    
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }
    public void setSdt(String sdt) { this.sdt = sdt; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return "KhachHangDTO{" + "maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", email=" + email + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        KhachHangDTO other = (KhachHangDTO) obj;
        return maKH.equals(other.maKH) && Objects.equals(tenKH, other.tenKH) && Objects.equals(sdt, other.sdt) && Objects.equals(email, other.email);
    }
}
