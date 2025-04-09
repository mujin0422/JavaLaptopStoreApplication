package BUS;

import DTO.PhieuNhapDTO;
import DAO.PhieuNhapDAO;
import java.util.ArrayList;

public class PhieuNhapBUS{
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS(){
        phieuNhapDAO = new PhieuNhapDAO();
    }

    public ArrayList<PhieuNhapDTO> getAllPhieuNhap(){
        return phieuNhapDAO.getAll();
    }

    public boolean addPhieuNhap(PhieuNhapDTO phieuNhap){
        return phieuNhapDAO.add(phieuNhap) > 0;
    }

    public boolean updatePhieuNhap(PhieuNhapDTO phieuNhap){
        return phieuNhapDAO.update(phieuNhap) > 0;
    }

    public boolean deletePhieuNhap(int maPN){
        return phieuNhapDAO.delete(maPN) > 0;
    }
    
    public boolean existsPhieuNhap(int maPN){
        return phieuNhapDAO.exists(maPN) > 0;
    }

    public ArrayList<PhieuNhapDTO> searchPhieuNhap(String keyword){
        if (keyword == null || keyword.trim().isEmpty()) {
            return phieuNhapDAO.getAll();
        }
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (PhieuNhapDTO pn : phieuNhapDAO.getAll()) {
            if (String.valueOf(pn.getMaNCC()).contains(keyword)) { 
                ketQua.add(pn);
            }
        }
        return ketQua;
    }
}
