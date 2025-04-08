package BUS;

import java.util.ArrayList;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
    private NhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS() {
        this.nhaCungCapDAO = new NhaCungCapDAO();
    }

    public ArrayList<NhaCungCapDTO> getAllNhaCungCap() {
        return nhaCungCapDAO.getAllNhaCungCap();
    }

    public NhaCungCapDTO getNhaCungCapById(int id) {
        return nhaCungCapDAO.getById(id);
    }

    public boolean addNhaCungCap(NhaCungCapDTO ncc) {
        return nhaCungCapDAO.add(ncc) > 0;
    }

    public boolean updateNhaCungCap(NhaCungCapDTO ncc) {
        return nhaCungCapDAO.update(ncc) > 0;
    }

    public boolean deleteNhaCungCap(int maNCC) {
        return nhaCungCapDAO.delete(maNCC) > 0;
    }
}
