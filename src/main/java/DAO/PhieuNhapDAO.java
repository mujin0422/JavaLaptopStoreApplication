package DAO;

import DTO.PhieuNhapDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class PhieuNhapDAO {
    public int add(PhieuNhapDTO obj) {
        String sql = "INNERT INTO phieunhap(maPN, maNV, maNCC, tongTien, ngayNhap) VALUES (?,?,?,?,?)";
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
        String sql = "DELETE FROM phieunhap WHERE maPN=?";
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
        String sql = "SELECT * FROM phieunhap";
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

}
