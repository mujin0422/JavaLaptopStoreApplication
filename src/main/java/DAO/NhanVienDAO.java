package DAO;

import DTO.NhanVienDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static int update(NhanVienDTO nv) {
        String sql = "UPDATE NhanVien SET tenNV = ?, sdt = ?, email = ? WHERE maNV = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nv.getTenNV());
            stmt.setString(2, nv.getSdt());
            stmt.setString(3, nv.getEmail());
            stmt.setInt(4, nv.getMaNV());
    
            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    public int delete(int maNhanVien) {
        String sql = "UPDATE nhanvien SET trangThaiXoa=1 WHERE maNV=?";
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
        String sql = "SELECT * FROM nhanvien WHERE trangThaiXoa=0";
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
        String sql = "SELECT * FROM nhanvien WHERE maNV=? AND trangThaiXoa=0";
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

    
