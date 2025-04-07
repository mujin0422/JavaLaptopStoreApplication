package DAO;

import DTO.TaiKhoanDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TaiKhoanDAO {

    public int add(TaiKhoanDTO obj) {
        String sql = "INSERT INTO taikhoan (tenDangNhap, matKhau, maNV, maQuyen) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenDangNhap());
            ps.setString(2, obj.getMatKhau());
            ps.setInt(3, obj.getMaNV());
            ps.setInt(4, obj.getMaQuyen());
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

    public int delete(String tenDangNhap) {
        String sql = "UPDATE taikhoan SET trangThaiXoa=1 WHERE tenDangNhap=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap );
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<TaiKhoanDTO> getAll() {
        ArrayList<TaiKhoanDTO> dsTaiKhoan = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsTaiKhoan.add(new TaiKhoanDTO(
                    rs.getString("tenDangNhap"),
                    rs.getString("matkhau"),
                    rs.getInt("maNV"),
                    rs.getInt("maQuyen")
                    
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
                        rs.getString("matKhau"),
                        rs.getInt("maNV"),
                        rs.getInt("maQuyen")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TaiKhoanDTO getByUsername(String username) {
        String sql = "SELECT * FROM taikhoan WHERE tenDangNhap=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TaiKhoanDTO(
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getInt("maNV"),
                        rs.getInt("maQuyen")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}   