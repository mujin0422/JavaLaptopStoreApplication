package BUS;

import DAO.ChiTietPhieuXuatDAO;
import DTO.ChiTietPhieuXuatDTO;
import java.util.ArrayList;

public class ChiTietPhieuXuatBUS {
    private ChiTietPhieuXuatDAO chiTietPhieuXuatDAO;

    public ChiTietPhieuXuatBUS() {
        chiTietPhieuXuatDAO = new ChiTietPhieuXuatDAO();
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuat() {
        return chiTietPhieuXuatDAO.getAll();
    }

    public boolean addChiTietPhieuXuat(ChiTietPhieuXuatDTO chiTietPhieuXuat) {
        return chiTietPhieuXuatDAO.add(chiTietPhieuXuat) > 0;
    }

    public boolean updateChiTietPhieuXuat(ChiTietPhieuXuatDTO chiTietPhieuXuat) {
        return chiTietPhieuXuatDAO.update(chiTietPhieuXuat) > 0;
    }

    public boolean deleteChiTietPhieuXuat(int maPX, int maSP) {
        return chiTietPhieuXuatDAO.delete(maPX, maSP) > 0;
    }

    public ChiTietPhieuXuatDTO getChiTietPhieuXuat(int maPX, int maSP) {
        return chiTietPhieuXuatDAO.getById(maPX, maSP);
    }
    
    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuatByMaPx(int maPX){
        return chiTietPhieuXuatDAO.getAllChiTietPhieuXuatByMaPx(maPX);
    }
    
    public int getTongSoLuongXuatTheoMaSP(int maSP) {
        return chiTietPhieuXuatDAO.getTongSoLuongXuatTheoMaSP(maSP);
    }
    
    public ArrayList<ChiTietPhieuXuatDTO> getChiTietPhieuXuatByDateRange(String startDate, String endDate) {
        ChiTietPhieuXuatDAO dao = new ChiTietPhieuXuatDAO();
        return dao.getAllByDateRange(startDate, endDate);
    }
    //thống kê
    public int getTongSoLuongXuat(int maSP, int fromYear, int toYear) {
        return chiTietPhieuXuatDAO.getTongSoLuongXuat(maSP, fromYear, toYear);
    }
    public int getTongSoLuongXuat(int thang, int nam) {
        return chiTietPhieuXuatDAO.getTongSoLuongXuat(thang, nam);
    }
    public int getTongSoLuongXuat(int maSP, String ngay) {
        return chiTietPhieuXuatDAO.getTongSoLuongXuatTheoNgay(maSP, ngay);
    }

}
