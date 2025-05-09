package DAO;

import DTO.DoPhanGiaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DoPhanGiaiDAO {
    
    public int add(DoPhanGiaiDTO obj) {
        String sql = "INSERT INTO dophangiai (maDPG, tenDPG) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaDPG());
            ps.setString(2, obj.getTenDPG());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(DoPhanGiaiDTO obj) {
        String sql = "UPDATE dophangiai SET tenDPG=? WHERE maDPG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenDPG());
            ps.setInt(2, obj.getMaDPG());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maDPG) {
        String sql = "UPDATE dophangiai SET trangThaiXoa=1 WHERE maDPG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDPG);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<DoPhanGiaiDTO> getAll() {
        ArrayList<DoPhanGiaiDTO> dsDPG = new ArrayList<>();
        String sql = "SELECT * FROM dophangiai WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsDPG.add(new DoPhanGiaiDTO(
                    rs.getInt("maDPG"),
                    rs.getString("tenDPG")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDPG;
    }

    public DoPhanGiaiDTO getById(int maDPG) {
        String sql = "SELECT * FROM dophangiai WHERE maDPG=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDPG);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DoPhanGiaiDTO(
                        rs.getInt("maDPG"),
                        rs.getString("tenDPG")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaDpgByTenDpg(String tenDpg) {
        String sql = "SELECT maDPG FROM dophangiai WHERE tenDPG=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDpg);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maDPG");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
