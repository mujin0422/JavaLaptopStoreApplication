package BUS;

import DAO.ChiTietChucNangDAO;
import DTO.ChiTietChucNangDTO;
import java.util.ArrayList;

public class ChiTietChucNangBUS {
    private ChiTietChucNangDAO chiTietChucNangDAO;

    public ChiTietChucNangBUS() {
        chiTietChucNangDAO = new ChiTietChucNangDAO();
    }

    public ArrayList<ChiTietChucNangDTO> getAllChiTietChucNang() {
        return chiTietChucNangDAO.getAll();
    }

    public boolean addChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.add(chiTietChucNang) > 0;
    }

    public boolean updateChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.update(chiTietChucNang) > 0;
    }

    public boolean deleteChiTietChucNang(int maCN, int maQuyen) {
        return chiTietChucNangDAO.delete(maCN, maQuyen) > 0;
    }

    public ArrayList<ChiTietChucNangDTO> searchChiTietChucNang(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return chiTietChucNangDAO.getAll();

        ArrayList<ChiTietChucNangDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        ArrayList<ChiTietChucNangDTO> danhSach = chiTietChucNangDAO.getAll();
        if (danhSach != null) {
            for (ChiTietChucNangDTO ctc : danhSach) {
                if (String.valueOf(ctc.getMaCN()).contains(keyword) ||
                    String.valueOf(ctc.getMaQuyen()).contains(keyword)) {
                    ketQua.add(ctc);
                }
            }
        }
        return ketQua;
    }
}
