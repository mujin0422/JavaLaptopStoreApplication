package DTO;

public class RomDTO {
    private int maROM;
    private String dungLuongROM;

    public RomDTO() {
    }

    public RomDTO(int maROM, String dungLuongROM) {
        this.maROM = maROM;
        this.dungLuongROM = dungLuongROM;
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
