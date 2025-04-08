package BUS;

import DTO.NhanVienDTO;
import DAO.NhanVienDAO;
import java.util.ArrayList;

public class NhanVienBUS {
    private NhanVienDAO NhanVienDAO;

    public NhanVienBUS() {
        NhanVienDAO = new NhanVienDAO();
    }

    public ArrayList<NhanVienDTO> getAllNhanVien() {
        return NhanVienDAO.getAll();
    }

    public boolean addNhanVien(NhanVienDTO nhanVien) {       
        if (nhanVien == null || nhanVien.getMaNV() <= 0 || nhanVien.getTenNV().isEmpty() || nhanVien.getEmail().isEmpty() || nhanVien.getSdt().isEmpty()){
            System.err.println("Thông tin nhân viên không hợp lệ !");
            return false;
        } 
        return  NhanVienDAO.add(nhanVien) > 0; 
    }

    public boolean updateNhanVien(NhanVienDTO nhanVien) {
        if (nhanVien == null || nhanVien.getMaNV() <= 0 || nhanVien.getTenNV().isEmpty() || nhanVien.getEmail().isEmpty() || nhanVien.getSdt().isEmpty()){
            return false;
        } 
        return NhanVienDAO.update(nhanVien) > 0; 
    }

    public boolean deleteNhanVien(int maNV) {
        if (maNV <= 0) {
            return false;
        }
        return NhanVienDAO.delete(maNV) > 0;
    }
    
    

}
