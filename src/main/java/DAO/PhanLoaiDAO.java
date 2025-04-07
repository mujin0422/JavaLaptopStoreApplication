package DAO;

import DTO.PhanLoaiDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanLoaiDAO {
    
    public int add(PhanLoaiDTO obj) {
        String sql = "INSERT INTO phanloai (maLoai, tenLoai) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaLoai());
            ps.setString(2, obj.getTenLoai());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(PhanLoaiDTO obj) {
        String sql = "UPDATE phanloai SET tenLoai=? WHERE maLoai=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenLoai());
            ps.setInt(2, obj.getMaLoai());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maLoai) {
        String sql = "UPDATE phanloai SET trangThaiXoa=1 WHERE maLoai=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<PhanLoaiDTO> getAll() {
        ArrayList<PhanLoaiDTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM phanloai WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                danhSach.add(new PhanLoaiDTO(
                    rs.getInt("maLoai"),
                    rs.getString("tenLoai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    
    public PhanLoaiDTO getById(int maLoai) {
        String sql = "SELECT * FROM phanloai WHERE maLoai=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhanLoaiDTO(
                        rs.getInt("maLoai"),
                        rs.getString("tenLoai")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}