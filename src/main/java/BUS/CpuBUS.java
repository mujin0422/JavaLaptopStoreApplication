package BUS;

import DAO.CpuDAO;
import DTO.CpuDTO;
import java.util.ArrayList;

public class CpuBUS {
    private CpuDAO cpuDAO;

    public CpuBUS() {
        cpuDAO = new CpuDAO();
    }

    public ArrayList<CpuDTO> getAllCPU() {
        return cpuDAO.getAll();
    }

    public boolean addCPU(CpuDTO cpu) {
        return cpuDAO.add(cpu) > 0;
    }

    public boolean updateCPU(CpuDTO cpu) {
        return cpuDAO.update(cpu) > 0;
    }

    public boolean deleteCPU(int maCPU) {
        return cpuDAO.delete(maCPU) > 0;
    }

    public CpuDTO getCpuById(int maCPU) {
        return cpuDAO.getById(maCPU);
    }
    
    public int getMaCpuByTenCpu(String tenCpu){
        return cpuDAO.getMaCpuByTenCpu(tenCpu);
    }
}
