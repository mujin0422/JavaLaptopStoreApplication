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
        
        return NhanVienDAO.add(nhanVien) > 0;
    }

    public boolean updateSach(NhanVienDTO nhanVien) {
        
        return NhanVienDAO.update(nhanVien) > 0; 
    }

    public boolean deleteNhanVien(int maNV) {
        return NhanVienDAO.delete(maNV) > 0;  
    }
    
    // public ArrayList<SachDTO> searchSach(String keyword) {
    //     if (keyword == null || keyword.trim().isEmpty()) {
    //         return sachDAO.getAll();
    //     }
    //     ArrayList<SachDTO> ketQua = new ArrayList<>();
    //     keyword = keyword.toLowerCase(); 
    //     ArrayList<SachDTO> danhSach = sachDAO.getAll();
    //     if (danhSach != null) {
    //         for (SachDTO sach : danhSach) {
    //             if (sach.getTenSach().toLowerCase().contains(keyword)) {
    //                 ketQua.add(sach);
    //             }
    //         }
    //     }
    //     return ketQua;
    // }

}
