package DAO;

import DTO.PhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuXuatDAO {
    
    public int add(PhieuXuatDTO obj) {
        String sql = "INSERT INTO phieuxuat(maPX, maNV, maKH, tongTien, ngayXuat) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaPX());
            ps.setInt(2, obj.getMaNV());
            ps.setInt(3, obj.getMaKH());
            ps.setInt(4, obj.getTongTien());
            ps.setDate(5, new java.sql.Date(obj.getNgayXuat().getTime()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(PhieuXuatDTO obj) {
        String sql = "UPDATE phieuxuat SET maNV=?, maKH=?, tongTien=?, ngayXuat=? WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNV());
            ps.setInt(2, obj.getMaKH());
            ps.setInt(3, obj.getTongTien());
            ps.setDate(4, new java.sql.Date(obj.getNgayXuat().getTime()));
            ps.setInt(5, obj.getMaPX());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maPX) {
        String sql = "UPDATE phieuxuat SET trangThaiXoa=1 WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int exists(int maPX) {
        String sql = "SELECT COUNT(*) FROM phieuxuat WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maPX);  
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  
    }

    public ArrayList<PhieuXuatDTO> getAll() {
        ArrayList<PhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM phieuxuat WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                PhieuXuatDTO obj = new PhieuXuatDTO(
                    rs.getInt("maPX"),
                    rs.getInt("maNV"),
                    rs.getInt("maKH"),
                    rs.getInt("tongTien"),
                    rs.getDate("ngayXuat")
                );
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuXuatDTO getById(int maPX) {
        String sql = "SELECT * FROM phieuxuat WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuXuatDTO(
                        rs.getInt("maPX"),
                        rs.getInt("maNV"),
                        rs.getInt("maKH"),
                        rs.getInt("tongTien"),
                        rs.getDate("ngayXuat")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
