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
    public SanPhamDTO getById(int maSP) {
        return sanPhamDAO.getById(maSP);
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
    public String getNextProductID() {
        return sanPhamDAO.getNextProductID();
    }
    
    public int getMaSpByTenSp(String tenSp) {
        return sanPhamDAO.getMaSpByTenSp(tenSp);
    }
    
    public boolean updateSoLuongTonSanPham(int maSp, int soLuongTon){
        return sanPhamDAO.updateSoLuongTonSanPham(maSp, soLuongTon) > 0;
    }

    public ArrayList<SanPhamDTO> getSanPhamByDateRange(String startDate, String endDate) {
        return sanPhamDAO.getSanPhamByDateRange(startDate, endDate);
    }
    public ArrayList<SanPhamDTO> getSanPhamByYearRange(int fromYear, int toYear) {
        return sanPhamDAO.getSanPhamByYearRange(fromYear, toYear);
    }
    public ArrayList<SanPhamDTO> getSanPhamByMonthYear(int month, int year) {
        return sanPhamDAO.getSanPhamByMonthYear(month, year);
    }
    public ArrayList<SanPhamDTO> getSanPhamByExactDate(String date) {
        return sanPhamDAO.getSanPhamByExactDate(date);
    }
    
    public ArrayList<SanPhamDTO> searchSanPham(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return sanPhamDAO.getAll();
        }
        ArrayList<SanPhamDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        ArrayList<SanPhamDTO> ds = sanPhamDAO.getAll();
        if(ds != null){
            for(SanPhamDTO kh : ds){
                if(kh.getTenSP().toLowerCase().contains(keyword)) 
                    ketQua.add(kh);      
            }
        }
        return ketQua;
    }
}
