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
}
