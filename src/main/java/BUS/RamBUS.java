package BUS;

import DAO.RamDAO;
import DTO.RamDTO;
import java.util.ArrayList;

public class RamBUS {
    private RamDAO ramDAO;

    public RamBUS() {
        ramDAO = new RamDAO();
    }

    public ArrayList<RamDTO> getAllRAM() {
        return ramDAO.getAll();
    }

    public boolean addRAM(RamDTO ram) {
        return ramDAO.add(ram) > 0;
    }

    public boolean updateRAM(RamDTO ram) {
        return ramDAO.update(ram) > 0;
    }

    public boolean deleteRAM(int maRAM) {
        return ramDAO.delete(maRAM) > 0;
    }
    
    public int getMaRamByDungLuongRam(String dungLuongRam){
        return ramDAO.getMaRamByDungLuongRam(dungLuongRam);
    }
}