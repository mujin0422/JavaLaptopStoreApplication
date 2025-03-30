package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;
import java.util.ArrayList;

public class QuyenBUS {
    private QuyenDAO quyenDAO;
    
    public QuyenBUS(){
        quyenDAO = new QuyenDAO();
    }
    
    public ArrayList<QuyenDTO> getAllQuyen(){
        return quyenDAO.getAll();
    }
    
    public boolean addQuyen(QuyenDTO quyen){
        return quyenDAO.add(quyen) > 0;
    }
    
    public boolean updateQuyen(QuyenDTO quyen){
        return quyenDAO.update(quyen) > 0;
    }
    
    public boolean deleteQuyen(int maQuyen){
        return quyenDAO.delete(maQuyen) > 0;
    }
        
    public ArrayList<QuyenDTO> searchQuyen(String keyword){
        if(keyword == null || keyword.trim().isEmpty()) return quyenDAO.getAll(); 
        
        ArrayList<QuyenDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();
        ArrayList<QuyenDTO> danhSach = quyenDAO.getAll();
        if (danhSach != null){
            for(QuyenDTO quyen: danhSach){
                if(quyen.getTenQuyen().toLowerCase().contains(keyword)){
                    ketQua.add(quyen);
                }
            }
        }
        return ketQua;
    }
}
