package DAO;

import DTO.ChucNangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChucNangDAO {
    
    public int add(ChucNangDTO obj) {
        String sql = "INSERT INTO chucnang (maCN, tenCN) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaCN());
            ps.setString(2, obj.getTenCN());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(ChucNangDTO obj) {
        String sql = "UPDATE chucnang SET tenCN=? WHERE maCN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenCN());
            ps.setInt(2, obj.getMaCN());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maCN) {
        String sql = "UPDATE chucnang SET trangThaiXoa=1 WHERE maCN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCN);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChucNangDTO> getAll() {
        ArrayList<ChucNangDTO> dsChucNang = new ArrayList<>();
        String sql = "SELECT * FROM chucnang WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChucNang.add(new ChucNangDTO(
                    rs.getInt("maCN"),
                    rs.getString("tenCN")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChucNang;
    }
    
    public ChucNangDTO getById(int id) {
        String sql = "SELECT * FROM chucnang WHERE maCN=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChucNangDTO(
                        rs.getInt("maCN"),
                        rs.getString("tenCN")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
