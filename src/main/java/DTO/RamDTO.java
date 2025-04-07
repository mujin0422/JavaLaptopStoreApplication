package DTO;

public class RamDTO {
    private int maRAM;
    private String dungLuongRAM; 
    private int trangThaiXoa;

    public RamDTO() {}

    public RamDTO(int maRAM, String dungLuongRAM) {
        this.maRAM = maRAM;
        this.dungLuongRAM = dungLuongRAM;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaRAM() {
        return maRAM;
    }

    public void setMaRAM(int maRAM) {
        this.maRAM = maRAM;
    }

    public String getDungLuongRAM() {
        return dungLuongRAM;
    }

    public void setDungLuongRAM(String dungLuongRAM) {
        this.dungLuongRAM = dungLuongRAM;
    }

    
}
