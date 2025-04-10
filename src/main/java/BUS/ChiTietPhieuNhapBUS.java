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
}
