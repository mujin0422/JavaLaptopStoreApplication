package BUS;

import DTO.TaiKhoanDTO;
import DAO.TaiKhoanDAO;
import java.util.ArrayList;

public class TaiKhoanBUS {
    private TaiKhoanDAO TaiKhoanDAO;

    public TaiKhoanBUS() {
        TaiKhoanDAO = new TaiKhoanDAO();
    }
    public ArrayList<TaiKhoanDTO> getAllTaiKhoan() {
        return TaiKhoanDAO.getAll();
    }
    public TaiKhoanDTO getByUsername(String username) {
        return TaiKhoanDAO.getByUsername(username);
    }
    public boolean addTaiKhoan(TaiKhoanDTO taikhoan) {       
        return TaiKhoanDAO.add(taikhoan) > 0;
    }
    public boolean updateTaiKhoan(TaiKhoanDTO taikhoan) {
        return TaiKhoanDAO.update(taikhoan) > 0; 
    }
    public boolean deleteTaiKhoan(String tenDangNhap) {
        return TaiKhoanDAO.delete(tenDangNhap) > 0;  
    }
    
    public String getTenNvByUsername(String username){
        return TaiKhoanDAO.getTenNvByUserName(username);
    }
    public String getTenQuyenByUsername(String username){
        return TaiKhoanDAO.getTenQuyenByUserName(username);
    }
    public ArrayList<Integer> getDanhSachMaCnByUsername(String username){
        return TaiKhoanDAO.getDanhSachMaCnByUsername(username);
    }
    public boolean hasPermission(String username, int maCN, String maHD){
        return TaiKhoanDAO.hasPermission(username, maCN, maHD);
    }
}