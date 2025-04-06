package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    private KhachHangDAO khachHangDAO;

    public KhachHangBUS() {
        khachHangDAO = new KhachHangDAO();
    }

    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return khachHangDAO.getAllKhachHang();
    }

    public boolean addKhachHang(KhachHangDTO khachHang) {
        if (khachHang == null || khachHang.getTenKH().isEmpty() || khachHang.getSdt().isEmpty()) {
            return false;
        }
        return khachHangDAO.add(khachHang) > 0;
    }

    public boolean updateKhachHang(KhachHangDTO khachHang) {
        if (khachHang == null || khachHang.getMaKH().isEmpty() || khachHang.getTenKH().isEmpty() || khachHang.getSdt().isEmpty()) {
            return false;
        }
        return khachHangDAO.update(khachHang) > 0;
    }

    public boolean deleteKhachHang(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            return false;
        }
        return khachHangDAO.delete(maKH) > 0;
    }

    public ArrayList<KhachHangDTO> searchKhachHang(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return khachHangDAO.getAllKhachHang();
        }
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (KhachHangDTO kh : khachHangDAO.getAllKhachHang()) {
            if (kh.getMaKH().toLowerCase().contains(keyword) ||
                kh.getTenKH().toLowerCase().contains(keyword) ||
                kh.getSdt().contains(keyword) ||
                (kh.getEmail() != null && kh.getEmail().toLowerCase().contains(keyword))) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }
}
