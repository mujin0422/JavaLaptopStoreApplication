package BUS;

import java.util.ArrayList;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO;

    public PhieuNhapBUS() {
        phieuNhapDAO = new PhieuNhapDAO();
    }

    public ArrayList<PhieuNhapDTO> getAllPhieuNhap() {
        return phieuNhapDAO.getAll();
    }

    public boolean addPhieuNhap(PhieuNhapDTO phieuNhap) {
        if (phieuNhap == null || phieuNhap.getMaPN() <= 0 || phieuNhap.getTongTien() < 0) {
            return false;
        }
        return phieuNhapDAO.add(phieuNhap) > 0;
    }

    public boolean updatePhieuNhap(PhieuNhapDTO phieuNhap) {
        if (phieuNhap == null || phieuNhap.getMaPN() <= 0 || phieuNhap.getTongTien() < 0) {
            return false;
        }
        return phieuNhapDAO.update(phieuNhap) > 0;
    }

    public boolean deletePhieuNhap(int maPN) {
        return phieuNhapDAO.delete(maPN) > 0;
    }

    public ArrayList<PhieuNhapDTO> searchPhieuNhap(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return phieuNhapDAO.getAll();
        }
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (PhieuNhapDTO pn : phieuNhapDAO.getAll()) {
            if (String.valueOf(pn.getMaNCC()).contains(keyword)) { 
                ketQua.add(pn);
            }
        }
        return ketQua;
    }
    public String getMaPhieuNhapTiepTheo() {
        int soLuongHienTai = phieuNhapDAO.demSoPhieuNhap();
        return "PN" + String.format("%03d", soLuongHienTai + 1);
    }
}
