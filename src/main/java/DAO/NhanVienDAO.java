package DAO;

import DTO.NhanVienDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {

    public int add(NhanVienDTO obj) {
        String sql = "INSERT INTO nhanvien (maNV, tenNV, email, sdt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNV());
            ps.setString(2, obj.getTenNV());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getSdt());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(NhanVienDTO obj) {
        String sql = "UPDATE nhanvien SET  tenNV=?, email=?, sdt=? WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, obj.getTenNV());
            ps.setString(3, obj.getEmail());
            ps.setString(3, obj.getSdt());
            ps.setInt(4, obj.getMaNV());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maNhanVien) {
        String sql = "DELETE FROM nhanvien WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNhanVien);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhanVienDTO> getAll() {
        ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsNhanVien.add(new NhanVienDTO(
                    rs.getInt("maNV"),
                    rs.getString("tenNV"),
                    rs.getString("email"),
                    rs.getString("sdt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVienDTO getById(int id) {
        String sql = "SELECT * FROM nhanvien WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhanVienDTO(
                        rs.getInt("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("email"),
                        rs.getString("sdt")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

    
