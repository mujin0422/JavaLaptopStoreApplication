package DAO;

import DTO.ChiTietPhieuXuatDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChiTietPhieuXuatDAO {

    public int add(ChiTietPhieuXuatDTO obj) {
        String sql = "INSERT INTO chitietphieuxuat (maSP, maPX, giaBan, soLuongSP, serialSP) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaSP());
            ps.setInt(2, obj.getMaPX());
            ps.setInt(3, obj.getGiaBan());
            ps.setInt(4, obj.getSoLuongSP());
            ps.setString(5, obj.getSerialSP());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(ChiTietPhieuXuatDTO obj) {
        String sql = "UPDATE chitietphieuxuat SET giaBan=?, soLuongSP=?, serialSP=? WHERE maSP=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getGiaBan());
            ps.setInt(2, obj.getSoLuongSP());
            ps.setString(3, obj.getSerialSP());
            ps.setInt(4, obj.getMaSP());
            ps.setInt(5, obj.getMaPX());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maSP, int maPX) {
        String sql = "DELETE FROM chitietphieuxuat WHERE maSP=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setInt(2, maPX);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<ChiTietPhieuXuatDTO> getAll() {
        ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieuxuat";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new ChiTietPhieuXuatDTO(
                    rs.getInt("maSP"),
                    rs.getInt("maPX"),
                    rs.getInt("giaBan"),
                    rs.getInt("soLuongSP"),
                    rs.getString("serialSP")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ChiTietPhieuXuatDTO getById(int maSP, int maPX) {
        String sql = "SELECT * FROM chitietphieuxuat WHERE maSP=? AND maPX=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            ps.setInt(2, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietPhieuXuatDTO(
                        rs.getInt("maSP"),
                        rs.getInt("maPX"),
                        rs.getInt("giaBan"),
                        rs.getInt("soLuongSP"),
                        rs.getString("serialSP")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<ChiTietPhieuXuatDTO> getAllChiTietPhieuXuatByMaPx(int maPX) {
        ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietphieuxuat WHERE maPX = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietPhieuXuatDTO ct = new ChiTietPhieuXuatDTO(
                        rs.getInt("maPX"),
                        rs.getInt("maSP"),
                        rs.getInt("giaBan"),
                        rs.getInt("soLuongSP"),
                        rs.getString("serialSP")
                    );
                    list.add(ct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getTongSoLuongXuatTheoMaSP(int maSP) {
        String sql = "SELECT SUM(soLuongSP) AS tong FROM chitietphieuxuat WHERE maSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("tong");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietPhieuXuatDTO> getAllByDateRange(String startDate, String endDate) {
        ArrayList<ChiTietPhieuXuatDTO> list = new ArrayList<>();
        String sql = "SELECT ct.* FROM chitietphieuxuat ct " +
                     "JOIN phieuxuat px ON ct.maPX = px.maPX " +
                     "WHERE px.ngayXuat BETWEEN ? AND ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate); // ngày bắt đầu
            ps.setString(2, endDate);   // ngày kết thúc
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new ChiTietPhieuXuatDTO(
                        rs.getInt("maSP"),
                        rs.getInt("maPX"),
                        rs.getInt("giaBan"),
                        rs.getInt("soLuongSP"),
                        rs.getString("serialSP")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}