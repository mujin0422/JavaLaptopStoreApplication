package DTO;

public class RomDTO {
    private int maROM;
    private int dungluongROM;

    public RomDTO() {
    }

    public RomDTO(int maROM, int dungluongROM) {
        this.maROM = maROM;
        this.dungluongROM = dungluongROM;
    }

    public int getMaROM() {
        return maROM;
    }

    public int getDungluongROM() {
        return dungluongROM;
    }

    public void setMaROM(int maROM) {
        this.maROM = maROM;
    }

    public void setDungluongROM(int dungluongROM) {
        this.dungluongROM = dungluongROM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.maROM;
        hash = 73 * hash + this.dungluongROM;
        return hash;
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
        final RomDTO other = (RomDTO) obj;
        if (this.maROM != other.maROM) {
            return false;
        }
        return this.dungluongROM == other.dungluongROM;
    }
    
    
    
}
