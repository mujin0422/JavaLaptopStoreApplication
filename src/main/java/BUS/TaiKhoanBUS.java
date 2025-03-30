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

    public boolean addTaiKhoan(TaiKhoanDTO taikhoan) {       
        
        return TaiKhoanDAO.add(taikhoan) > 0;
    }

    public boolean updateTaiKhoan(TaiKhoanDTO taikhoan) {
        
        return TaiKhoanDAO.update(taikhoan) > 0; 
    }

    public boolean deleteTaiKhoan(int maNV) {
        return TaiKhoanDAO.delete(maNV) > 0;  
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
