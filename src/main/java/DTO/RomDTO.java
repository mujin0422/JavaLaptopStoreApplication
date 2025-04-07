package DTO;

public class RomDTO {
    private int maROM;
    private String dungLuongROM;
    private int trangThaiXoa;

    public RomDTO() {}

    public RomDTO(int maROM, String dungLuongROM) {
        this.maROM = maROM;
        this.dungLuongROM = dungLuongROM;
        this.trangThaiXoa = 0;
    }

    public int getTrangThaiXoa() {
        return trangThaiXoa;
    }

    public void setTrangThaiXoa(int trangThaiXoa) {
        this.trangThaiXoa = trangThaiXoa;
    }

    public int getMaROM() {
        return maROM;
    }

    public String getDungLuongROM() {
        return dungLuongROM;
    }

    public void setMaROM(int maROM) {
        this.maROM = maROM;
    }

    public void setDungluongROM(String dungLuongROM) {
        this.dungLuongROM = dungLuongROM;
    }
}
