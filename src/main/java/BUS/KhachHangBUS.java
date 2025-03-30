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
        return khachHangDAO.getAll();
    }

    public boolean addKhachHang(KhachHangDTO khachHang) {
        if (khachHang == null || khachHang.getTenKH().isEmpty() || khachHang.getSdt().isEmpty()) {
            return false;
        }
        return khachHangDAO.add(khachHang) > 0;
    }

    public boolean updateKhachHang(KhachHangDTO khachHang) {
        if (khachHang == null || khachHang.getMaKH() <= 0 || khachHang.getTenKH().isEmpty() || khachHang.getSdt().isEmpty()) {
            return false;
        }
        return khachHangDAO.update(khachHang) > 0;
    }

    public boolean deleteKhachHang(int maKH) {
        if (maKH <= 0) {
            return false;
        }
        return khachHangDAO.delete(maKH) > 0;
    }

    public ArrayList<KhachHangDTO> searchKhachHang(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return khachHangDAO.getAll();
        }
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (KhachHangDTO kh : khachHangDAO.getAll()) {
            if (String.valueOf(kh.getMaKH()).contains(keyword) ||
                kh.getTenKH().toLowerCase().contains(keyword) ||
                kh.getSdt().contains(keyword) ||
                (kh.getEmail() != null && kh.getEmail().toLowerCase().contains(keyword))) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }
}
