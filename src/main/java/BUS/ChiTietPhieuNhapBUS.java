package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    }

    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhap() {
        return chiTietPhieuNhapDAO.getAll();
    }

    public boolean addChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.add(chiTietPhieuNhap) > 0;
    }

    public boolean updateChiTietPhieuNhap(ChiTietPhieuNhapDTO chiTietPhieuNhap) {
        return chiTietPhieuNhapDAO.update(chiTietPhieuNhap) > 0;
    }

    public boolean deleteChiTietPhieuNhap(int maPN, int maSP) {
        return chiTietPhieuNhapDAO.delete(maPN, maSP) > 0;
    }

    public ChiTietPhieuNhapDTO getChiTietPhieuNhap(int maPN, int maSP) {
        return chiTietPhieuNhapDAO.getById(maPN, maSP);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhapByMaPn(int maPn){
        return chiTietPhieuNhapDAO.getAllChiTietPhieuNhapByMaPn(maPn);
    }
    
    public int getTongSoLuongNhapTheoMaSP(int maSP) {
        return chiTietPhieuNhapDAO.getTongSoLuongNhapTheoMaSP(maSP);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByDateRange(String startDate, String endDate) {
        ChiTietPhieuNhapDAO dao = new ChiTietPhieuNhapDAO();
        return dao.getAllByDateRange(startDate, endDate);
    }
    //thống kê
    public int getTongSoLuongNhap(int maSP, int fromYear, int toYear) {
        return chiTietPhieuNhapDAO.getTongSoLuongNhap(maSP, fromYear, toYear);
    }
    public int getTongSoLuongNhap(int thang, int nam) {
        return chiTietPhieuNhapDAO.getTongSoLuongNhap(thang, nam);
    }
    public int getTongSoLuongNhap(int maSP, String ngay) {
        return chiTietPhieuNhapDAO.getTongSoLuongNhapTheoNgay(maSP, ngay);
    }


}
