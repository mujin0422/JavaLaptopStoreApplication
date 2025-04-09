package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    private KhachHangDAO khachHangDAO;

    public KhachHangBUS() {
        khachHangDAO = new KhachHangDAO();
    }

    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return khachHangDAO.getAll();
    }

    public boolean addKhachHang(KhachHangDTO khachHang) {
        return khachHangDAO.add(khachHang) > 0;
    }

    public boolean updateKhachHang(KhachHangDTO khachHang) {
        return khachHangDAO.update(khachHang) > 0;
    }

    public boolean deleteKhachHang(int maKH) {
        return khachHangDAO.delete(maKH) > 0;
    }
    
    public int getMaKhByTenKh(String tenKh){
        return khachHangDAO.getMaKhByTenKh(tenKh);
    }
    
    public String getTenKhByMaKh(int maKh){
        return khachHangDAO.getTenKhByMaKh(maKh);
    }

    public ArrayList<KhachHangDTO> searchKhachHang(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return khachHangDAO.getAll();
        }
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        ArrayList<KhachHangDTO> ds = khachHangDAO.getAll();
        if(ds != null){
            for(KhachHangDTO kh : ds){
                if(kh.getTenKH().toLowerCase().contains(keyword)) 
                    ketQua.add(kh);      
            }
        }
        return ketQua;
    }
}
