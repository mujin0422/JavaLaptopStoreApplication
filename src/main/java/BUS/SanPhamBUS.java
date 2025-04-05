package BUS;

import java.util.ArrayList;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {
    private SanPhamDAO sanPhamDAO;

    public SanPhamBUS() {
        sanPhamDAO = new SanPhamDAO();
    }

    public  ArrayList<SanPhamDTO> getAllSanPham() {
        return sanPhamDAO.getAll();
    }

    public boolean addSanPham(SanPhamDTO sanPham) {
        return sanPhamDAO.add(sanPham) > 0;
    }

    public boolean updateSanPham(SanPhamDTO sanPham) {
        return sanPhamDAO.update(sanPham) > 0;
    }

    public boolean deleteSanPham(int maSP) {
        return sanPhamDAO.delete(maSP) > 0;
    }

    public SanPhamDTO getSanPhamById(int maSP) {
        return sanPhamDAO.getById(maSP);
    }
}