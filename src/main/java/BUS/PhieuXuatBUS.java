package BUS;

import DTO.PhieuXuatDTO;
import DAO.PhieuXuatDAO;
import java.util.ArrayList;

public class PhieuXuatBUS {
    private PhieuXuatDAO phieuXuatDAO;

    public PhieuXuatBUS() {
        phieuXuatDAO = new PhieuXuatDAO();
    }
    public PhieuXuatDTO getById(int maPX){
        return phieuXuatDAO.getById(maPX);
    }
    public ArrayList<PhieuXuatDTO> getAllPhieuXuat() {
        return phieuXuatDAO.getAll();
    }
    public boolean addPhieuXuat(PhieuXuatDTO phieuXuat) {       
        return phieuXuatDAO.add(phieuXuat) > 0;
    }
    public boolean updatePhieuXuat(PhieuXuatDTO phieuXuat) {
        return phieuXuatDAO.update(phieuXuat) > 0; 
    }
    public boolean deletePhieuXuat(int maPX) {
        return phieuXuatDAO.delete(maPX) > 0;  
    }
    public String getNextExportID() {
        return PhieuXuatDAO.getNextExportID();
    }
    
    
    public int countPhieuXuatByMaKh(int maKh){
        return phieuXuatDAO.countPhieuXuatByMaKh(maKh);
    }
    public int getMaKhByMaPx(int maPx) {
        return phieuXuatDAO.getMaKhByMaPx(maPx);
    }

    
    // thống kê
    public double getTongTienTheoNam(int year) {
        return phieuXuatDAO.getTongTienTheoNam(year);
    }
    public double getTongTienTheoThangNam(int thang, int nam) {
        return phieuXuatDAO.getTongTienTheoThangNam(thang, nam);
    }
    public double getTongTienTheoNgay(String ngay) {
        return phieuXuatDAO.getTongTienTheoNgay(ngay);
    }
}
