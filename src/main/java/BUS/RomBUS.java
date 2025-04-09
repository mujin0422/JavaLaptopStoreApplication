package BUS;

import DAO.RomDAO;
import DTO.RomDTO;
import java.util.ArrayList;

public class RomBUS {
    private RomDAO romDAO;

    public RomBUS() {
        romDAO = new RomDAO();
    }

    public ArrayList<RomDTO> getAllROM() {
        return romDAO.getAll();
    }

    public boolean addROM(RomDTO rom) {       
        return romDAO.add(rom) > 0;
    }

    public boolean updateROM(RomDTO rom) {
        return romDAO.update(rom) > 0; 
    }

    public boolean deleteROM(int maROM) {
        return romDAO.delete(maROM) > 0;  
    }
    
    public int getMaRomByDungLuongRom(String dungLuongRom){
        return romDAO.getMaRomByDungLuongRom(dungLuongRom);
    }
}