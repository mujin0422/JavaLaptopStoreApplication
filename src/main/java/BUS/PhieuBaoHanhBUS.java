package BUS;

import DAO.PhieuBaoHanhDAO;
import DTO.PhieuBaoHanhDTO;
import java.util.ArrayList;

public class PhieuBaoHanhBUS {
    private PhieuBaoHanhDAO phieuBaoHanhDAO;

    public PhieuBaoHanhBUS() {
        phieuBaoHanhDAO = new PhieuBaoHanhDAO();
    }
    public PhieuBaoHanhDTO getById(int maPBH) {
        return phieuBaoHanhDAO.getById(maPBH);
    }
    public ArrayList<PhieuBaoHanhDTO> getAllPhieuBaoHanh() {
        return phieuBaoHanhDAO.getAll();
    }
    public boolean addPhieuBaoHanh(PhieuBaoHanhDTO phieuBaoHanh) {
        return phieuBaoHanhDAO.add(phieuBaoHanh) > 0;
    }
    public boolean updatePhieuBaoHanh(PhieuBaoHanhDTO phieuBaoHanh) {
        return phieuBaoHanhDAO.update(phieuBaoHanh) > 0;
    }
    public boolean deletePhieuBaoHanh(int maPBH) {
        return phieuBaoHanhDAO.delete(maPBH) > 0;
    }
    public String getNextGuaranteeID() {
        return phieuBaoHanhDAO.getNextGuaranteeID();
    }

    
    
    public int getMaPxByMaPbh(int maPBH) {
        return phieuBaoHanhDAO.getMaPxByMaPbh(maPBH);
    }
    public String getTenKhByMaPbh(int maPBH) {
        return phieuBaoHanhDAO.getTenKhByMaPbh(maPBH);
    }
    public String getTenSpByMaPbh(int maPBH) {
        return phieuBaoHanhDAO.getTenSpByMaPbh(maPBH);
    }   
}
