package BUS;

import DAO.CpuDAO;
import DTO.CpuDTO;
import java.util.ArrayList;

public class CpuBUS {
    private CpuDAO cpuDAO;

    public CpuBUS() {
        cpuDAO = new CpuDAO();
    }

    public ArrayList<CpuDTO> getAllCpu() {
        return cpuDAO.getAll();
    }

    public boolean addCpu(CpuDTO cpu) {
        return cpuDAO.add(cpu) > 0;
    }

    public boolean updateCpu(CpuDTO cpu) {
        return cpuDAO.update(cpu) > 0;
    }

    public boolean deleteCpu(int maCPU) {
        return cpuDAO.delete(maCPU) > 0;
    }

    public CpuDTO getCpuById(int maCPU) {
        return cpuDAO.getById(maCPU);
    }
}
