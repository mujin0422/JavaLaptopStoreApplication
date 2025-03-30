package BUS;

import DAO.RomDAO;
import DTO.RomDTO;
import java.util.ArrayList;

public class RomBUS {
    private RomDAO romDAO;

    public RomBUS() {
        romDAO = new RomDAO();
    }

    public ArrayList<RomDTO> getAllRom() {
        return romDAO.getAll();
    }

    public boolean addRom(RomDTO rom) {       
        return romDAO.add(rom) > 0;
    }

    public boolean updateRom(RomDTO rom) {
        return romDAO.update(rom) > 0; 
    }

    public boolean deleteRom(int maROM) {
        return romDAO.delete(maROM) > 0;  
    }
}