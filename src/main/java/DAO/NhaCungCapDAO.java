package DAO;

import DTO.NhaCungCapDTO;
import java.sql.*;
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
        String sql = "DELETE FROM nhacungcap WHERE maNCC=?";
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
        String sql = "SELECT * FROM nhacungcap";
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

//    public boolean exists(int maNCC) {
//        String sql = "SELECT 1 FROM nhacungcap WHERE maNCC = ? LIMIT 1";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, maNCC);
//            try (ResultSet rs = ps.executeQuery()) {
//                return rs.next();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
