package BUS;

import DAO.ThuongHieuDAO;
import DTO.ThuongHieuDTO;
import java.util.ArrayList;

public class ThuongHieuBUS {
    private ThuongHieuDAO thuongHieuDAO;

    public ThuongHieuBUS() {
        thuongHieuDAO = new ThuongHieuDAO();
    }

    public ArrayList<ThuongHieuDTO> getAllThuongHieu() {
        return thuongHieuDAO.getAll();
    }

    public boolean addThuongHieu(ThuongHieuDTO thuongHieu) {
        return thuongHieuDAO.add(thuongHieu) > 0;
    }

    public boolean updateThuongHieu(ThuongHieuDTO thuongHieu) {
        return thuongHieuDAO.update(thuongHieu) > 0;
    }

    public boolean deleteThuongHieu(int maTH) {
        return thuongHieuDAO.delete(maTH) > 0;
    }
}