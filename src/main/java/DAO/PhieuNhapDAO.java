package DAO;

import DTO.PhieuNhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhieuNhapDAO {
    public int add(PhieuNhapDTO obj) {
        String sql = "INSERT INTO phieunhap(maPN, maNV, maNCC, tongTien, ngayNhap) VALUES (?,?,?,?,?)";
        try(Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, obj.getMaPN());
            ps.setInt(2, obj.getMaNV());
            ps.setInt(3, obj.getMaNCC());
            ps.setInt(4, obj.getTongTien());
            ps.setDate(5, new java.sql.Date(obj.getNgayNhap().getTime()));
            return ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(PhieuNhapDTO obj){
        String sql = "UPDATE phieunhap SET maNV=?, maNCC=?, tongTien=?, ngayNhap=? WHERE maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaNV());
            ps.setInt(2, obj.getMaNCC());
            ps.setInt(3, obj.getTongTien());
            ps.setDate(5, new java.sql.Date(obj.getNgayNhap().getTime()));
            ps.setInt(5, obj.getMaPN()); 
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maPN) {
        String sql = "UPDATE phieunhap SET trangThaiXoa=1 WHERE maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPN);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        ArrayList<PhieuNhapDTO> dspn = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhieuNhapDTO obj = new PhieuNhapDTO(
                    rs.getInt("maPN"),
                    rs.getInt("maNV"),
                    rs.getInt("maNCC"),
                    rs.getInt("tongTien"),
                    rs.getDate("ngayNhap")
                );
                dspn.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dspn;
    }

    public PhieuNhapDTO getById(int maPN) {
        String sql = "SELECT * FROM phieunhap WHERE maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PhieuNhapDTO(
                        rs.getInt("maPN"),
                        rs.getInt("maNV"),
                        rs.getInt("maNCC"),
                        rs.getInt("tongTien"),
                        rs.getDate("ngayNhap")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public double getTongTienTheoNam(int year) {
        double tongTien = 0;
        String sql = "SELECT SUM(tongTien) as tongTien " +
                     "FROM phieunhap " +
                     "WHERE YEAR(ngayNhap) = ?";
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
                     "FROM phieunhap " +
                     "WHERE MONTH(ngayNhap) = ? AND YEAR(ngayNhap) = ?";
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
        String sql = "SELECT SUM(tongTien) AS tongTien FROM phieunhap WHERE DATE(ngayNhap) = ?";
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
    
    public static String getNextImportID() {
        String query = "SELECT MAX(maPN) AS max_id FROM phieunhap";
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
        return "1"; // Nếu chưa có phiếu nhập nào
    }
}
