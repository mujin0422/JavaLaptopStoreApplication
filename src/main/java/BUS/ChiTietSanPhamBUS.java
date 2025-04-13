package BUS;

import DAO.ChiTietSanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import java.util.ArrayList;

public class ChiTietSanPhamBUS {
    private ChiTietSanPhamDAO chiTietSanPhamDao;

    public ChiTietSanPhamBUS() {
        chiTietSanPhamDao = new ChiTietSanPhamDAO();
    }

    public boolean addChiTietSanPham(ChiTietSanPhamDTO ctsp) {
        return chiTietSanPhamDao.add(ctsp) > 0;
    }

    public ChiTietSanPhamDTO getChiTietSanPhamBySerial(String serialSP) {
        return chiTietSanPhamDao.getBySerial(serialSP);
    }

    public ArrayList<ChiTietSanPhamDTO> getAllChiTietSanPham() {
        return chiTietSanPhamDao.getAll();
    }

    public boolean updateMaPX(String serialSP, int maPX) {
        return chiTietSanPhamDao.updateMaPX(serialSP, maPX) > 0;
    }

    public boolean deleteChiTietSanPham(String serialSP) {
        return chiTietSanPhamDao.delete(serialSP)> 0;
    }
    
    public String generateUniqueSerial() {
        String lastSerial = chiTietSanPhamDao.getLastSerial(); 
        if (lastSerial == null) {
            return "LTSK00000001DFFW";
        }
        String numberPart = lastSerial.substring(4, lastSerial.length() - 4);
        int number = Integer.parseInt(numberPart);
        number++;
        return String.format("LTSK%08dDFFW", number);
    }
    
    public ArrayList<ChiTietSanPhamDTO> getAllWithoutMaPXByMaSP(int maSP){
        return chiTietSanPhamDao.getAllWithoutMaPXByMaSP(maSP);
    }
    
    public int getMaSpBySerialSp(String serialSp){
        return chiTietSanPhamDao.getMaSpBySerialSp(serialSp);
    }
    
    public ArrayList<ChiTietSanPhamDTO> getAllByMaPX(int maPX) {
        return chiTietSanPhamDao.getAllByMaPX(maPX);
    }

}
