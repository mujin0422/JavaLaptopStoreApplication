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
    
    public int countPhieuXuatByMaKh(int maKh) {
        String sql = "SELECT COUNT(*) AS total FROM phieuxuat WHERE maKH = ? AND trangThaiXoa = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKh);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }
    
    public int getMaKhByMaPx(int maPx) {
        String sql = "SELECT maKH FROM phieuxuat WHERE maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPx);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maKH");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }
    
    public double getTongTienTheoNam(int year) {
        double tongTien = 0;
        String sql = "SELECT SUM(tongTien) as tongTien " +
                     "FROM phieuxuat " +
                     "WHERE YEAR(ngayXuat) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTien = rs.getDouble("tongTien");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongTien;
    }
    
    public double getTongTienTheoThangNam(int thang, int nam) {
        double tongTien = 0;
        String sql = "SELECT SUM(tongTien) as tongTien " +
                     "FROM phieuxuat " +
                     "WHERE MONTH(ngayXuat) = ? AND YEAR(ngayXuat) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTien = rs.getDouble("tongTien");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongTien;
    }
    
    public double getTongTienTheoNgay(String ngay) {
        double tongTien = 0;
        String sql = "SELECT SUM(tongTien) AS tongTien FROM phieuxuat WHERE DATE(ngayXuat) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ngay); // Định dạng yyyy-MM-dd
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongTien = rs.getDouble("tongTien");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tongTien;
    }

    public static String getNextExportID() {
        String query = "SELECT MAX(maPX) AS max_id FROM phieuxuat";
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
        return "1"; // Nếu chưa có phiếu xuất nào
    }
}
