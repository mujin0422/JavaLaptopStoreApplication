package DAO;

import DTO.PhieuBaoHanhDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PhieuBaoHanhDAO {
    
    public int add(PhieuBaoHanhDTO obj) {
        String sql = "INSERT INTO phieubaohanh (maPBH, serialSP ,ngayTiepNhan, moTaLoi, trangThaiBH, maNVBH) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaPBH());
            ps.setString(2, obj.getSerialSP());
            ps.setDate(3, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(4, obj.getMoTaLoi());
            ps.setInt(5, obj.getTrangThaiBH());
            ps.setInt(6, obj.getMaNVBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(PhieuBaoHanhDTO obj) {
        String sql = "UPDATE phieubaohanh SET serialSP=?, ngayTiepNhan=?, moTaLoi=?, trangThaiBH=?, maNVBH=? WHERE maPBH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getSerialSP());
            ps.setDate(2, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(3, obj.getMoTaLoi());
            ps.setInt(4, obj.getTrangThaiBH());
            ps.setInt(5, obj.getMaNVBH());
            ps.setInt(6, obj.getMaPBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maPBH) {
        String sql = "UPDATE phieubaohanh SET trangThaiXoa=1 WHERE maPBH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<PhieuBaoHanhDTO> getAll() {
        ArrayList<PhieuBaoHanhDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieubaohanh WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PhieuBaoHanhDTO(
                    rs.getInt("maPBH"),
                    rs.getString("serialSP"),
                    rs.getInt("maNVBH"),
                    rs.getDate("ngayTiepNhan"),
                    rs.getString("moTaLoi"),
                    rs.getInt("trangThaiBH")
                    
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuBaoHanhDTO getById(int maPBH) {
        String sql = "SELECT * FROM phieubaohanh WHERE maPBH=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuBaoHanhDTO(
                        rs.getInt("maPBH"),
                        rs.getString("serialSP"),
                        rs.getInt("maNVBH"),
                        rs.getDate("ngayTiepNhan"),
                        rs.getString("moTaLoi"),
                        rs.getInt("trangThaiBH")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaPxByMaPbh(int maPBH) {
        String sql = "SELECT ctsp.maPX FROM phieubaohanh pbh "
                   + "JOIN chitietsanpham ctsp ON pbh.serialSP = ctsp.serialSP "
                   + "WHERE pbh.maPBH = ? AND pbh.trangThaiXoa = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maPX");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }
    
    public String getTenKhByMaPbh(int maPBH) {
        String sql = "SELECT kh.tenKH FROM phieubaohanh pbh "
                   + "JOIN chitietsanpham ctsp ON pbh.serialSP = ctsp.serialSP "
                   + "JOIN phieuxuat px ON px.maPX = ctsp.maPX "
                   + "JOIN khachhang kh ON px.maKH = kh.maKH "
                   + "WHERE pbh.maPBH = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
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

    
    public String getTenSpByMaPbh(int maPBH) {
        String sql = "SELECT sp.tenSP FROM phieubaohanh pbh "
                   + "JOIN chitietsanpham ctsp ON pbh.serialSP = ctsp.serialSP "
                   + "JOIN sanpham sp ON sp.maSP = ctsp.maSP "
                   + "WHERE pbh.maPBH = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenSP");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getNextGuaranteeID() {
        String query = "SELECT MAX(maPBH) AS max_id FROM phieubaohanh";
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
        return "1"; // Nếu chưa có phiếu bảo hành nào
    }
}