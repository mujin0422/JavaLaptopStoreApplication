package BUS;

import DAO.ChiTietChucNangDAO;
import DTO.ChiTietChucNangDTO;
import java.util.ArrayList;

public class ChiTietChucNangBUS {
    private ChiTietChucNangDAO chiTietChucNangDAO;

    public ChiTietChucNangBUS() {
        chiTietChucNangDAO = new ChiTietChucNangDAO();
    }

    public ArrayList<ChiTietChucNangDTO> getAllChiTietChucNang() {
        return chiTietChucNangDAO.getAll();
    }
    
    public ArrayList<ChiTietChucNangDTO> getChiTietChucNangByMaQuyen(int maQuyen){
        return chiTietChucNangDAO.getById(maQuyen);
    }

    public boolean addChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.add(chiTietChucNang) > 0;
    }

    public boolean updateChiTietChucNang(ChiTietChucNangDTO chiTietChucNang) {
        return chiTietChucNangDAO.update(chiTietChucNang) > 0;
    }

    public boolean deleteChiTietChucNang(int maCN, int maQuyen, String maHD) {
        return chiTietChucNangDAO.delete(maCN, maQuyen, maHD) > 0;
    }
    
    public boolean activeChiTietChucNang(int maCN, int maQuyen, String maHD){
        return chiTietChucNangDAO.active(maCN, maQuyen, maHD) > 0;
    }
    
    public boolean existsChiTietChucNang(int maCN, int maQuyen, String maHD){
        return chiTietChucNangDAO.exists(maCN, maQuyen, maHD) > 0;
    }
}
