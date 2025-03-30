package BUS;

import DTO.PhieuXuatDTO;
import DAO.PhieuXuatDAO;
import java.util.ArrayList;

public class PhieuXuatBUS {
    private PhieuXuatDAO phieuXuatDAO;

    public PhieuXuatBUS() {
        phieuXuatDAO = new PhieuXuatDAO();
    }

    public ArrayList<PhieuXuatDTO> getAllPhieuXuat() {
        return phieuXuatDAO.getAll();
    }

    public boolean addPhieuXuat(PhieuXuatDTO phieuXuat) {       
        if (phieuXuat == null || phieuXuat.getTongTien() <= 0 || phieuXuat.getNgayXuat() == null) {
            return false;
        }
        return phieuXuatDAO.add(phieuXuat) > 0;
    }

    public boolean updatePhieuXuat(PhieuXuatDTO phieuXuat) {
        if (phieuXuat == null || phieuXuat.getMaPX() <= 0 || phieuXuat.getTongTien() <= 0 || phieuXuat.getNgayXuat() == null) {
            return false;
        }
        return phieuXuatDAO.update(phieuXuat) > 0; 
    }

    public boolean deletePhieuXuat(int maPX) {
        return phieuXuatDAO.delete(maPX) > 0;  
    }

    public ArrayList<PhieuXuatDTO> searchPhieuXuat(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return phieuXuatDAO.getAll();
        }
        ArrayList<PhieuXuatDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        for (PhieuXuatDTO px : phieuXuatDAO.getAll()) {
            if (String.valueOf(px.getMaKH()).contains(keyword)) { 
                ketQua.add(px);
            }
        }
        return ketQua;
    }

}
