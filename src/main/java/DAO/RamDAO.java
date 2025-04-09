package DAO;

import DTO.RamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RamDAO {
    
    public int add(RamDTO obj) {
        String sql = "INSERT INTO ram (maRAM, dungLuongRAM) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaRAM());
            ps.setString(2, obj.getDungLuongRAM());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(RamDTO obj) {
        String sql = "UPDATE ram SET dungLuongRAM=? WHERE maRAM=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getDungLuongRAM());
            ps.setInt(2, obj.getMaRAM());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maRAM) {
        String sql = "UPDATE ram SET trangThaiXoa=1 WHERE maRAM=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maRAM);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<RamDTO> getAll() {
        ArrayList<RamDTO> dsRam = new ArrayList<>();
        String sql = "SELECT * FROM ram WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsRam.add(new RamDTO(
                    rs.getInt("maRAM"),
                    rs.getString("dungLuongRAM")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsRam;
    }
    
    public RamDTO getById(int maRAM) {
        String sql = "SELECT * FROM ram WHERE maRAM=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maRAM);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RamDTO(
                        rs.getInt("maRAM"),
                        rs.getString("dungLuongRAM")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaRamByDungLuongRam(String dungLuongRam) {
        String sql = "SELECT maRAM FROM ram WHERE dungLuongRAM=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dungLuongRam);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maRAM");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
