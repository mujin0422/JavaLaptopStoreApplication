package DAO;

import DTO.CpuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CpuDAO {
    
    public int add(CpuDTO obj) {
        String sql = "INSERT INTO cpu (maCPU, tenCPU) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaCPU());
            ps.setString(2, obj.getTenCPU());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(CpuDTO obj) {
        String sql = "UPDATE cpu SET tenCPU=? WHERE maCPU=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenCPU());
            ps.setInt(2, obj.getMaCPU());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maCPU) {
        String sql = "UPDATE cpu SET trangThaiXoa=1 WHERE maCPU=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCPU);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<CpuDTO> getAll() {
        ArrayList<CpuDTO> dsCPU = new ArrayList<>();
        String sql = "SELECT * FROM cpu WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsCPU.add(new CpuDTO(
                    rs.getInt("maCPU"),
                    rs.getString("tenCPU")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCPU;
    }
    
    public CpuDTO getById(int maCPU) {
        String sql = "SELECT * FROM cpu WHERE maCPU=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCPU);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CpuDTO(
                        rs.getInt("maCPU"),
                        rs.getString("tenCPU")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaCpuByTenCpu(String tenCpu) {
        String sql = "SELECT maCPU FROM cpu WHERE tenCPU=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenCpu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maCPU");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String getTenCpuByMaCpu(int maCpu){
        String sql = "SELECT tenCPU FROM cpu WHERE maCPU=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCpu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("tenCPU");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
