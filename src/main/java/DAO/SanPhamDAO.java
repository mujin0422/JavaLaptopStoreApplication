package DAO;

import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    
    public int add(SanPhamDTO obj) {
        String sql = "INSERT INTO sanpham (maSP, tenSP, giaSP, soLuongTon, maCPU, maROM, maRAM, maTH, maDPG, maLoai, thoigianBH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSP());
            ps.setString(2, obj.getTenSP());
            ps.setInt(3, obj.getGiaSP());
            ps.setInt(4, obj.getSoLuongTon());
            ps.setInt(5, obj.getMaCPU());
            ps.setInt(6, obj.getMaROM());
            ps.setInt(7, obj.getMaRAM());
            ps.setInt(8, obj.getMaTH());
            ps.setInt(9, obj.getMaDPG());
            ps.setInt(10, obj.getMaLoai());
            ps.setInt(11, obj.getThoiGianBH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(SanPhamDTO obj) {
        String sql = "UPDATE sanpham SET tenSP=?, giaSP=?, soLuongTon=?, maCPU=?, maROM=?, maRAM=?, maTH=?, maDPG=?, maLoai=?, thoigianBH=? WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenSP());
            ps.setInt(2, obj.getGiaSP());
            ps.setInt(3, obj.getSoLuongTon());
            ps.setInt(4, obj.getMaCPU());
            ps.setInt(5, obj.getMaROM());
            ps.setInt(6, obj.getMaRAM());
            ps.setInt(7, obj.getMaTH());
            ps.setInt(8, obj.getMaDPG());
            ps.setInt(9, obj.getMaLoai());
            ps.setInt(10, obj.getThoiGianBH());
            ps.setInt(11, obj.getMaSP());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maSP) {
        String sql = "UPDATE sanpham SET trangThaiXoa=1 WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public List<SanPhamDTO> getAllSanPham() {
        List<SanPhamDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMaSP(rs.getInt("maSP"));
                sp.setTenSP(rs.getString("tenSP"));
                sp.setGiaSP(rs.getInt("giaSP"));
                sp.setSoLuongTon(rs.getInt("soLuongTon"));
                // các trường khác nếu có
                ds.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public ArrayList<SanPhamDTO> getAll() {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsSanPham.add(new SanPhamDTO(
                    rs.getInt("maSP"),
                    rs.getString("tenSP"),
                    rs.getInt("giaSP"),
                    rs.getInt("soLuongTon"),
                    rs.getInt("maCPU"),
                    rs.getInt("maROM"),
                    rs.getInt("maRAM"),
                    rs.getInt("maTH"),
                    rs.getInt("maDPG"),
                    rs.getInt("maLoai"),
                    rs.getInt("thoigianBH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }

    
    public SanPhamDTO getById(int maSP) {
        String sql = "SELECT * FROM sanpham WHERE maSP=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SanPhamDTO(
                        rs.getInt("maSP"),
                        rs.getString("tenSP"),
                        rs.getInt("giaSP"),
                        rs.getInt("soLuongTon"),
                        rs.getInt("maCPU"),
                        rs.getInt("maROM"),
                        rs.getInt("maRAM"),
                        rs.getInt("maTH"),
                        rs.getInt("maDPG"),
                        rs.getInt("maLoai"),
                        rs.getInt("thoigianBH")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}