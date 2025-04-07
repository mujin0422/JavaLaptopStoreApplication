package DAO;

import DTO.RomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RomDAO {
    public int add(RomDTO obj) {
        String sql = "INSERT INTO rom (maROM, dungLuongROM) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaROM());
            ps.setString(2, obj.getDungLuongROM());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(RomDTO obj) {
        String sql = "UPDATE rom SET dungLuongROM=? WHERE maROM=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getDungLuongROM());
            ps.setInt(2, obj.getMaROM());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maROM) {
        String sql = "UPDATE rom SET trangThaiXoa=1 WHERE maROM=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maROM);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<RomDTO> getAll() {
        ArrayList<RomDTO> dsRom = new ArrayList<>();
        String sql = "SELECT * FROM rom WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsRom.add(new RomDTO(
                    rs.getInt("maROM"),
                    rs.getString("dungLuongROM")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsRom;
    }

    public RomDTO getById(int maROM) {
        String sql = "SELECT * FROM rom WHERE maROM=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maROM);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RomDTO(
                        rs.getInt("maROM"),
                        rs.getString("dungLuongROM")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}