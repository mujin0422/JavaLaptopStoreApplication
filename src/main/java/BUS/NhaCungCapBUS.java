package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBUS {
    private NhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS() {
        this.nhaCungCapDAO = new NhaCungCapDAO();
    }

    public ArrayList<NhaCungCapDTO> getAllNhaCungCap() {
        return nhaCungCapDAO.getAll();
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
