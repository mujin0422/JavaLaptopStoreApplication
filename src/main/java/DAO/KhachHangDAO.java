package DAO;
import DTO.KhachHangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Dell Vostro
 */
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
    
    public int getMaKhByTenKh(String tenKh) {
        String sql = "SELECT maKH FROM khachhang WHERE tenKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKh); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maKH"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public String getTenKhByMaKh(int maKh) {
        String sql = "SELECT tenKH FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKh); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenKH"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
