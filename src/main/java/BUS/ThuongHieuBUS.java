package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;

public class ThuongHieuBUS {
    private ThuongHieuDAO thuongHieuDAO;

    public ThuongHieuBUS() {
        thuongHieuDAO = new ThuongHieuDAO();
    }
    public ArrayList<ThuongHieuDTO> getAllThuongHieu() {
        return thuongHieuDAO.getAll();
    }
    public ThuongHieuDTO getById(int maTH){
        return thuongHieuDAO.getById(maTH);
    }
    public boolean addThuongHieu(ThuongHieuDTO thuongHieu) {
        return thuongHieuDAO.add(thuongHieu) > 0;
    }
    public boolean updateThuongHieu(ThuongHieuDTO thuongHieu) {
        return thuongHieuDAO.update(thuongHieu) > 0;
    }
    public boolean deleteThuongHieu(int maTH) {
        return thuongHieuDAO.delete(maTH) > 0;
    }
    
    public int getMaThByTenTh(String tenTh){
        return thuongHieuDAO.getMaThByTenTh(tenTh);
    }
    
}