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
    public NhanVienDTO getById(int maNV){
        return NhanVienDAO.getById(maNV);
    }
    public boolean addNhanVien(NhanVienDTO nhanVien) {       
        return NhanVienDAO.add(nhanVien) > 0;
    }
    public boolean updateNhanVien(NhanVienDTO nhanVien) {
        return NhanVienDAO.update(nhanVien) > 0; 
    }
    public boolean deleteNhanVien(int maNV) {
        return NhanVienDAO.delete(maNV) > 0;  
    }
    
    public NhanVienDTO getCurrentStaffByUserName(String username) {
        return NhanVienDAO.getCurrentStaffByUserName(username);
    }
    public int getMaNvByTenNv(String tenNv){
        return NhanVienDAO.getMaNvByTenNv(tenNv);
    }
    public String getNextEmployeeID() {
        return NhanVienDAO.getNextEmployeeID();
    }
    
    public ArrayList<NhanVienDTO> searchNhanVien(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return NhanVienDAO.getAll();
        }
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<NhanVienDTO> danhSach = NhanVienDAO.getAll();
        if (danhSach != null) {
            for (NhanVienDTO nv : danhSach) {
                if (nv.getTenNV().toLowerCase().contains(keyword))
                    ketQua.add(nv);
            }
        }
        return ketQua;
    }
    
    public ArrayList<NhanVienDTO> getAllNvNotExistsTk(){
        return NhanVienDAO.getAllNvNotExistsTk();
    }
}
