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
        String sql = "INSERT INTO phieubaohanh (maPBH, maSP, maPX, serialSP ,ngayTiepNhan, moTaLoi, trangThaiBH, maNVBH) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaPBH());
            ps.setInt(2, obj.getMaSP());
            ps.setInt(3, obj.getMaPX());
            ps.setString(4, obj.getSerialSP());
            ps.setDate(5, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(6, obj.getMoTaLoi());
            ps.setInt(7, obj.getTrangThaiBH());
            ps.setInt(8, obj.getMaNVBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(PhieuBaoHanhDTO obj) {
        String sql = "UPDATE phieubaohanh SET maSP=?, maPX=?, serialSP=?, ngayTiepNhan=?, moTaLoi=?, trangThaiBH=?, maNVBH=? WHERE maPBH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSP());
            ps.setInt(2, obj.getMaPX());
            ps.setString(3, obj.getSerialSP());
            ps.setDate(4, new java.sql.Date(obj.getNgayTiepNhan().getTime()));
            ps.setString(5, obj.getMoTaLoi());
            ps.setInt(6, obj.getTrangThaiBH());
            ps.setInt(7, obj.getMaNVBH());
            ps.setInt(8, obj.getMaPBH());
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
                    rs.getInt("maSP"),
                    rs.getInt("maPX"),
                    rs.getString("serialSP"),
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
        String sql = "SELECT * FROM phieubaohanh WHERE maPBH=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPBH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuBaoHanhDTO(
                        rs.getInt("maPBH"),
                        rs.getInt("maSP"),
                        rs.getInt("maPX"),
                        rs.getString("serialSP"),
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