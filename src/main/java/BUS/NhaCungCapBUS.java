package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBUS{
    private NhaCungCapDAO nhaCungCapDAO;

    public NhaCungCapBUS(){
        this.nhaCungCapDAO = new NhaCungCapDAO();
    }

    public ArrayList<NhaCungCapDTO> getAllNhaCungCap(){
        return nhaCungCapDAO.getAll();
    }
    public NhaCungCapDTO getById(int id){
        return nhaCungCapDAO.getById(id);
    }
    public boolean addNhaCungCap(NhaCungCapDTO ncc){
        return nhaCungCapDAO.add(ncc) > 0;
    }
    public boolean updateNhaCungCap(NhaCungCapDTO ncc){
        return nhaCungCapDAO.update(ncc) > 0;
    }
    public boolean deleteNhaCungCap(int maNCC){
        return nhaCungCapDAO.delete(maNCC) > 0;
    } 
    public String getNextSupplierID() {
        return nhaCungCapDAO.getNextSupplierID();
    }
    
    public ArrayList<NhaCungCapDTO> searchNhaCungCap(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return nhaCungCapDAO.getAll();
        }
        ArrayList<NhaCungCapDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase(); 
        ArrayList<NhaCungCapDTO> danhSach = nhaCungCapDAO.getAll();
        if (danhSach != null) {
            for (NhaCungCapDTO ncc : danhSach) {
                if (ncc.getTenNCC().toLowerCase().contains(keyword))
                    ketQua.add(ncc);
            }
        }
        return ketQua;
    }
}
