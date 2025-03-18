package DTO;

public class RamDTO {
    private int maRAM;
    private int dungLuongRAM; 

    public RamDTO() {}

    public RamDTO(int maRAM, int dungLuongRAM) {
        this.maRAM = maRAM;
        this.dungLuongRAM = dungLuongRAM;
    }

    public int getMaRAM() {
        return maRAM;
    }

    public void setMaRAM(int maRAM) {
        this.maRAM = maRAM;
    }

    public int getDungLuongRAM() {
        return dungLuongRAM;
    }

    public void setDungLuongRAM(int dungLuongRAM) {
        this.dungLuongRAM = dungLuongRAM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.maRAM;
        hash = 43 * hash + this.dungLuongRAM;
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
        final RamDTO other = (RamDTO) obj;
        if (this.maRAM != other.maRAM) {
            return false;
        }
        return this.dungLuongRAM == other.dungLuongRAM;
    }
    
    
}
