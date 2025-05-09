package DAO;
import DTO.KhachHangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KhachHangDAO {
    public int add(KhachHangDTO obj) {
        String sql = "INSERT INTO khachhang(maKH, tenKH, sdt, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaKH());
            ps.setString(2, obj.getTenKH());
            ps.setString(3, obj.getSdt());
            ps.setString(4, obj.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(KhachHangDTO obj) {
        String sql = "UPDATE khachhang SET tenKH=?, sdt=?, email=? WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenKH());
            ps.setString(2, obj.getSdt());
            ps.setString(3, obj.getEmail());
            ps.setInt(4, obj.getMaKH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maKH) {
        String sql = "UPDATE khachhang SET trangThaiXoa=1 WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KhachHangDTO> getAll() {
        ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsKhachHang.add(new KhachHangDTO(
                    rs.getInt("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public KhachHangDTO getById(int maKH) {
        String sql = "SELECT * FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangDTO(
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean existsSDT(String sdt) {
        String query = "SELECT COUNT(*) FROM khachhang WHERE sdt = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public KhachHangDTO getKhBySDT(String sdt) {
        String query = "SELECT * FROM khachhang WHERE sdt = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sdt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new KhachHangDTO(
                    rs.getInt("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getNextCustomerID() {
        String query = "SELECT MAX(maKH) AS max_id FROM khachhang";
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
        return "1"; // Nếu chưa có khách hàng nào
    }
}
