package BUS;

import DAO.DoPhanGiaiDAO;
import DTO.DoPhanGiaiDTO;
import java.util.ArrayList;

public class DoPhanGiaiBUS {
    private DoPhanGiaiDAO doPhanGiaiDAO;

    public DoPhanGiaiBUS() {
        doPhanGiaiDAO = new DoPhanGiaiDAO();
    }

    public ArrayList<DoPhanGiaiDTO> getAllDoPhanGiai() {
        return doPhanGiaiDAO.getAll();
    }

    public boolean addDoPhanGiai(DoPhanGiaiDTO doPhanGiai) {
        return doPhanGiaiDAO.add(doPhanGiai) > 0;
    }

    public boolean updateDoPhanGiai(DoPhanGiaiDTO doPhanGiai) {
        return doPhanGiaiDAO.update(doPhanGiai) > 0;
    }

    public boolean deleteDoPhanGiai(int maDPG) {
        return doPhanGiaiDAO.delete(maDPG) > 0;
    }

    public DoPhanGiaiDTO getDoPhanGiaiById(int maDPG) {
        return doPhanGiaiDAO.getById(maDPG);
    }
    
    public int getMaDpgByTenDpg(String tenDpg){
        return doPhanGiaiDAO.getMaDpgByTenDpg(tenDpg);
    }
    
    public String getTenDpgByMaDpg(int maDpg) {
        return doPhanGiaiDAO.getTenDpgByMaDpg(maDpg);
    }
}
