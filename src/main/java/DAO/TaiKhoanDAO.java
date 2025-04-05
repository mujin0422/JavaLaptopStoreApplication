package DAO;

import DTO.TaiKhoanDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class TaiKhoanDAO {

    public int add(TaiKhoanDTO obj) {
        String sql = "INSERT INTO taikhoan (tenDangNhap, matKhau, maQuyen, maNV) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenDangNhap());
            ps.setString(2, obj.getMatKhau());
            ps.setInt(3, obj.getMaQuyen());
            ps.setInt(4, obj.getMaNV());
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(TaiKhoanDTO obj) {
        String sql = "UPDATE taikhoan SET tenDangNhap=?, matKhau=?, maQuyen=? WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenDangNhap());
            ps.setString(2, obj.getMatKhau());
            ps.setInt(3, obj.getMaQuyen());
            ps.setInt(4, obj.getMaNV());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maNV) {
        String sql = "DELETE FROM taikhoan WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNV);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<TaiKhoanDTO> getAll() {
        ArrayList<TaiKhoanDTO> dsTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsTaiKhoan.add(new TaiKhoanDTO(
                    rs.getString("tenDangNhap"),
                    rs.getString("matKhau"),
                    rs.getInt("maQuyen"),
                    rs.getInt("maNV")
                    
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTaiKhoan;
    }

    public TaiKhoanDTO getById(int id) {
        String sql = "SELECT * FROM taikhoan WHERE maNV=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                        rs.getString("tenDangNhap"),
                        rs.getString("matkhau"),
                        rs.getInt("maQuyen"),
                        rs.getInt("maNV")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TaiKhoanDTO getByUsername(String username) {
        String sql = "SELECT * FROM taikhoan WHERE tenDangNhap=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getInt("maQuyen"),
                        rs.getInt("maNV")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TaiKhoanDTO getByTenDangNhap(String tenDangNhap) {
        for (TaiKhoanDTO tk : getAll()) {  // getAll() là method lấy tất cả tài khoản
            if (tk.getTenDangNhap().equals(tenDangNhap)) {
                return tk;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
}

   