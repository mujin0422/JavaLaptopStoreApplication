package BUS;

import DAO.HanhDongDAO;
import DTO.HanhDongDTO;
import java.util.ArrayList;

public class HanhDongBUS {
    private HanhDongDAO hanhDongDAO;
    
    public HanhDongBUS(){
        hanhDongDAO = new HanhDongDAO();
    }
    
    public ArrayList<HanhDongDTO> getAllHanhDong(){
        return hanhDongDAO.getAll();
    }
    
    public boolean addHanhDong(HanhDongDTO hanhDong){
        return hanhDongDAO.add(hanhDong) > 0;
    }
    
    public boolean editHanhDong(HanhDongDTO hanhDong){
        return hanhDongDAO.edit(hanhDong) > 0;
    }
    
    public boolean deleteHanhDong(String maHD){
        return hanhDongDAO.delete(maHD) > 0;
    }
}
