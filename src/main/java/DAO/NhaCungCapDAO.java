package DAO;

import DTO.NhaCungCapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhaCungCapDAO {

    public int add(NhaCungCapDTO obj) {
        String sql = "INSERT INTO nhacungcap (maNCC, tenNCC, diaChi, sdt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNCC());
            ps.setString(2, obj.getTenNCC());
            ps.setString(3, obj.getDiaChi());
            ps.setString(4, obj.getSdt());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(NhaCungCapDTO obj) {
        String sql = "UPDATE nhacungcap SET tenNCC=?, diaChi=?, sdt=? WHERE maNCC=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenNCC());
            ps.setString(2, obj.getDiaChi());
            ps.setString(3, obj.getSdt());
            ps.setInt(4, obj.getMaNCC());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maNCC) {
        String sql = "UPDATE nhacungcap SET trangThaiXoa=1 WHERE maNCC=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNCC);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<NhaCungCapDTO> getAll() {
        ArrayList<NhaCungCapDTO> dsNCC = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsNCC.add(new NhaCungCapDTO(
                    rs.getInt("maNCC"),
                    rs.getString("tenNCC"),
                    rs.getString("diaChi"),
                    rs.getString("sdt")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsNCC;
    }

    public NhaCungCapDTO getById(int id) {
        String sql = "SELECT * FROM nhacungcap WHERE maNCC=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new NhaCungCapDTO(
                        rs.getInt("maNCC"),
                        rs.getString("tenNCC"),
                        rs.getString("diaChi"),
                        rs.getString("sdt")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaNccByTenNCC(String tenNcc) {
        String sql = "SELECT maNCC FROM nhacungcap WHERE tenNCC=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenNcc); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maNCC"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

    public String getTenNccByMaNCC(int maNcc) {
        String sql = "SELECT tenNCC FROM nhacungcap WHERE maNCC=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNcc); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenNCC"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
