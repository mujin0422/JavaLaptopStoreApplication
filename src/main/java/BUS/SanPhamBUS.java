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
    
    public int getMaSpByTenSp(String tenSp) {
        return sanPhamDAO.getMaSpByTenSp(tenSp);
    }
    
    public int getGiaSpByMaSp(int maSp){
        return sanPhamDAO.getGiaSpByMaSp(maSp);
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

    public int getSoLuongTonSanPham(int maSp){
        return sanPhamDAO.getSoLuongTonSanPham(maSp);
    }
    
    public boolean updateSoLuongTonSanPham(int maSp, int soLuongTon){
        return sanPhamDAO.updateSoLuongTonSanPham(maSp, soLuongTon) > 0;
    }
    
    public SanPhamDTO getSanPhamById(int maSP) {
        return sanPhamDAO.getById(maSP);
    }
    
     public String getTenThByMaSp(int maSp) {
        return sanPhamDAO.getTenThByMaSp(maSp);
    }

    public String getTenCpuByMaSp(int maSp) {
        return sanPhamDAO.getTenCpuByMaSp(maSp);
    }

    public String getTenDpgByMaSp(int maSp) {
        return sanPhamDAO.getTenDpgByMaSp(maSp);
    }

    public String getTenLoaiByMaSp(int maSp) {
        return sanPhamDAO.getTenLoaiByMaSp(maSp);
    }

    public String getDungLuongRamByMaSp(int maSp) {
        return sanPhamDAO.getDungLuongRamByMaSp(maSp);
    }

    public String getDungLuongRomByMaSp(int maSp) {
        return sanPhamDAO.getDungLuongRomByMaSp(maSp);
    }
    
    public ArrayList<SanPhamDTO> searchSanPham(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return sanPhamDAO.getAll();
        }
        ArrayList<SanPhamDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<SanPhamDTO> danhSach = sanPhamDAO.getAll();
        if (danhSach != null) {
            for (SanPhamDTO sp : danhSach) {
                if (sp.getTenSP().toLowerCase().contains(keyword))
                    ketQua.add(sp);
            }
        }
        return ketQua;
    }
    
    public String getTenSanPhamByMaSanPham(int maSp){
        return sanPhamDAO.getTenSanPhamByMaSanPham(maSp);
    }
    
    public ArrayList<SanPhamDTO> searchSanPhamByMaOrTen(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return sanPhamDAO.getAll();
        }
        return sanPhamDAO.searchByMaOrTen(keyword);
    }
    
     public ArrayList<SanPhamDTO> searchSanPhamByDateRange(String startDate, String endDate) {
        return sanPhamDAO.getSanPhamByDateRange(startDate, endDate);
    }
}
