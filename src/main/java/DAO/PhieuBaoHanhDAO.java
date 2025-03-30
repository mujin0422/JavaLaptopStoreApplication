package DAO;

import DTO.PhieuBaoHanhDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuBaoHanhDAO {
    
    public int add(PhieuBaoHanhDTO obj) {
        String sql = "INSERT INTO phieubaohanh (maPBH, maSP, maPX, ngayTiepNhan, moTaLoi, trangThaiBH, maNVBH) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaPBH());
            ps.setInt(2, obj.getMaSP());
            ps.setInt(3, obj.getMaPX());
            ps.setDate(4, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(5, obj.getMoTaLoi());
            ps.setInt(6, obj.getTrangThaiBH());
            ps.setInt(7, obj.getMaNVBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(PhieuBaoHanhDTO obj) {
        String sql = "UPDATE phieubaohanh SET maSP=?, maPX=?, ngayTiepNhan=?, moTaLoi=?, trangThaiBH=?, maNVBH=? WHERE maPBH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSP());
            ps.setInt(2, obj.getMaPX());
            ps.setDate(3, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(4, obj.getMoTaLoi());
            ps.setInt(5, obj.getTrangThaiBH());
            ps.setInt(6, obj.getMaNVBH());
            ps.setInt(7, obj.getMaPBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maPBH) {
        String sql = "DELETE FROM phieubaohanh WHERE maPBH=?";
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
        String sql = "SELECT * FROM phieubaohanh";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PhieuBaoHanhDTO(
                    rs.getInt("maPBH"),
                    rs.getInt("maSP"),
                    rs.getInt("maPX"),
                    rs.getDate("ngayTiepNhan"),
                    rs.getString("moTaLoi"),
                    rs.getInt("trangThaiBH"),
                    rs.getInt("maNVBH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuBaoHanhDTO getById(int maPBH) {
        String sql = "SELECT * FROM phieubaohanh WHERE maPBH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuBaoHanhDTO(
                        rs.getInt("maPBH"),
                        rs.getInt("maSP"),
                        rs.getInt("maPX"),
                        rs.getDate("ngayTiepNhan"),
                        rs.getString("moTaLoi"),
                        rs.getInt("trangThaiBH"),
                        rs.getInt("maNVBH")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}