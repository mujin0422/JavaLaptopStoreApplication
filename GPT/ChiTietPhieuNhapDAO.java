package DAO;

import DTO.ChiTietPhieuNhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChiTietPhieuNhapDAO {
    public int add(ChiTietPhieuNhapDTO obj) {
        String sql = "INSERT INTO chitietphieunhap (maSP, maPN, soLuongSP, giaNhap) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSP());
            ps.setInt(2, obj.getMaPN());
            ps.setInt(3, obj.getSoLuongSP());
            ps.setInt(4, obj.getGiaNhap());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(ChiTietPhieuNhapDTO obj) {
        String sql = "UPDATE chitietphieunhap SET soLuongSP=?, giaNhap=? WHERE maSP=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getSoLuongSP());
            ps.setInt(2, obj.getGiaNhap());
            ps.setInt(3, obj.getMaSP());
            ps.setInt(4, obj.getMaPN());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maSP, int maPN) {
        String sql = "DELETE FROM chitietphieunhap WHERE maSP=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setInt(2, maPN);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getAll() {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new ChiTietPhieuNhapDTO(
                    rs.getInt("maSP"),
                    rs.getInt("maPN"),
                    rs.getInt("soLuongSP"),
                    rs.getInt("giaNhap")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ChiTietPhieuNhapDTO getById(int maSP, int maPN) {
        String sql = "SELECT * FROM chitietphieunhap WHERE maSP=? AND maPN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setInt(2, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietPhieuNhapDTO(
                        rs.getInt("maSP"),
                        rs.getInt("maPN"),
                        rs.getInt("soLuongSP"),
                        rs.getInt("giaNhap")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getAllChiTietPhieuNhapByMaPn(int maPN) {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieunhap WHERE maPN = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPN);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(
                        rs.getInt("maPN"),
                        rs.getInt("maSP"),
                        rs.getInt("soLuongSP"),
                        rs.getInt("giaNhap")
                    );
                    list.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getTongSoLuongNhapTheoMaSP(int maSP) {
        String sql = "SELECT SUM(soLuongSP) AS tongNhap FROM chitietphieunhap WHERE maSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tongNhap");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> getAllByDateRange(String startDate, String endDate) {
        ArrayList<ChiTietPhieuNhapDTO> list = new ArrayList<>();
        String sql = "SELECT ct.* FROM chitietphieunhap ct " +
                     "JOIN phieunhap pn ON ct.maPN = pn.maPN " +
                     "WHERE pn.ngayNhap BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate); // ngày bắt đầu
            ps.setString(2, endDate);   // ngày kết thúc
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ChiTietPhieuNhapDTO(
                        rs.getInt("maSP"),
                        rs.getInt("maPN"),
                        rs.getInt("giaNhap"),
                        rs.getInt("soLuongSP")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}