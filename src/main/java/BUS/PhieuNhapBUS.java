package BUS;

import DTO.PhieuNhapDTO;
import DAO.PhieuNhapDAO;
import java.util.ArrayList;

public class PhieuNhapBUS{
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS(){
        phieuNhapDAO = new PhieuNhapDAO();
    }
    public PhieuNhapDTO getById(int maPn){
        return phieuNhapDAO.getById(maPn);
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
    public String getNextImportID() {
        return PhieuNhapDAO.getNextImportID();
    }
    
    
    public double getTongTienTheoNam(int year) {
        return phieuNhapDAO.getTongTienTheoNam(year);
    }
    public double getTongTienTheoThangNam(int thang, int nam) {
        return phieuNhapDAO.getTongTienTheoThangNam(thang, nam);
    }
    public double getTongTienTheoNgay(String ngay) {
        return phieuNhapDAO.getTongTienTheoNgay(ngay);
    }
}
