package BUS;

import DTO.TaiKhoanDTO;
import DAO.TaiKhoanDAO;
import java.util.ArrayList;

public class TaiKhoanBUS {
    private TaiKhoanDAO TaiKhoanDAO;

    TaiKhoanDAO dao = new TaiKhoanDAO();

    public TaiKhoanDTO getTaiKhoanByUsername(String tenDangNhap) {
        return dao.getByUsername(tenDangNhap);
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

}
