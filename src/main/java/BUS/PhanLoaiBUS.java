package BUS;

import DAO.PhanLoaiDAO;
import DTO.PhanLoaiDTO;
import java.util.ArrayList;

public class PhanLoaiBUS {
    private PhanLoaiDAO phanLoaiDAO;

    public PhanLoaiBUS() {
        phanLoaiDAO = new PhanLoaiDAO();
    }

    public ArrayList<PhanLoaiDTO> getAllPhanLoai() {
        return phanLoaiDAO.getAll();
    }

    public boolean addPhanLoai(PhanLoaiDTO phanLoai) {
        return phanLoaiDAO.add(phanLoai) > 0;
    }

    public boolean updatePhanLoai(PhanLoaiDTO phanLoai) {
        return phanLoaiDAO.update(phanLoai) > 0;
    }

    public boolean deletePhanLoai(int maLoai) {
        return phanLoaiDAO.delete(maLoai) > 0;
    }

    public PhanLoaiDTO getPhanLoaiById(int maLoai) {
        return phanLoaiDAO.getById(maLoai);
    }
    
    public int getMaLoaiByTenLoai(String tenLoai){
        return phanLoaiDAO.getMaLoaiByTenLoai(tenLoai);
    }
}
