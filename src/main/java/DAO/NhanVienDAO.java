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
        String sql = "INSERT INTO nhanvien (maNV, tenNV, email, sdt, vaiTro) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNV());
            ps.setString(2, obj.getTenNV());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getSdt());
            ps.setString(5, obj.getVaiTro());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
        }
        return 0;
    }

    public int update(NhanVienDTO obj) {
        String sql = "UPDATE nhanvien SET  tenNV=?, email=?, sdt=?, vaiTro=? WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenNV());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getSdt());
            ps.setString(4, obj.getVaiTro());
            ps.setInt(5, obj.getMaNV());
            return ps.executeUpdate();
        } catch (SQLException e) {
        }
        return 0;
    }

    public int delete(int maNhanVien) {
        String sql = "UPDATE nhanvien SET trangThaiXoa=1 WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNhanVien);
            return ps.executeUpdate();
        } catch (SQLException e) {
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
                    rs.getString("sdt"),
                    rs.getString("vaiTro")
                ));
            }
        } catch (SQLException e) {
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
                        rs.getString("sdt"),
                        rs.getString("vaiTro")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaNvByTenNv(String tenNv) {
        String sql = "SELECT maNV FROM nhanvien WHERE tenNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenNv); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maNV"); 
                }
            }
        } catch (SQLException e) {
        }
        return 0; 
    }
    
    public NhanVienDTO getCurrentStaffByUserName(String username){
        String sql = "SELECT * FROM taikhoan tk JOIN nhanvien nv "
                + "WHERE nv.maNV = tk.maNV AND tk.tenDangNhap=? ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhanVienDTO(
                        rs.getInt("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("email"),
                        rs.getString("sdt"),
                        rs.getString("vaiTro")
                    );
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    public ArrayList<NhanVienDTO> getAllNvNotExistsTk() {
        ArrayList<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT nv.* FROM nhanvien nv " +
                     "LEFT JOIN taikhoan tk ON nv.maNV = tk.maNV " +
                     "WHERE tk.maNV IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                    rs.getInt("maNV"),
                    rs.getString("tenNV"),
                    rs.getString("email"),
                    rs.getString("sdt"),
                    rs.getString("vaiTro")
                );
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String getNextEmployeeID() {
        String query = "SELECT MAX(maNV) AS max_id FROM nhanvien";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("max_id"); // Đọc trực tiếp int
                return String.valueOf(maxId + 1); // +1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1"; // Nếu chưa có nhân viên nào
    }
}

    
