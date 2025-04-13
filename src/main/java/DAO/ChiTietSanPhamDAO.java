package DAO;

import DTO.ChiTietSanPhamDTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietSanPhamDAO {

    public String getLastSerial() {
        String query = "SELECT MAX(serialSP) AS maxSerial FROM chitietsanpham";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getString("maxSerial");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(ChiTietSanPhamDTO obj) {
        String sql = "INSERT INTO chitietsanpham (serialSP, maSP, maPN) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getSerialSP());
            ps.setInt(2, obj.getMaSP());
            ps.setInt(3, obj.getMaPN());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ChiTietSanPhamDTO getBySerial(String serialSP) {
        String sql = "SELECT * FROM chitietsanpham WHERE serialSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serialSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiTietSanPhamDTO obj = new ChiTietSanPhamDTO(
                    rs.getString("serialSP"),
                    rs.getInt("maSP"),
                    rs.getInt("maPN")
                );
                obj.setMaPX(rs.getInt("maPX"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ChiTietSanPhamDTO> getAll() {
        ArrayList<ChiTietSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietsanpham";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ChiTietSanPhamDTO obj = new ChiTietSanPhamDTO(
                    rs.getString("serialSP"),
                    rs.getInt("maSP"),
                    rs.getInt("maPN")
                );
                obj.setMaPX(rs.getInt("maPX"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int updateMaPX(String serialSP, int maPX) {
        String sql = "UPDATE chitietsanpham SET maPX = ? WHERE serialSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPX);
            ps.setString(2, serialSP);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(String serialSP) {
        String sql = "DELETE FROM chitietsanpham WHERE serialSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, serialSP);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietSanPhamDTO> getAllWithoutMaPXByMaSP(int maSP) {
        ArrayList<ChiTietSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietsanpham WHERE maSP = ? AND maPX IS NULL"; 
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietSanPhamDTO obj = new ChiTietSanPhamDTO(
                        rs.getString("serialSP"), 
                        maSP, 
                        rs.getInt("maPN"));
                    list.add(obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getMaSpBySerialSp(String serialSp){
        String sql = "SELECT maSP FROM chitietsanpham WHERE serialSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, serialSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maSP");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietSanPhamDTO> getAllByMaPX(int maPX) {
        ArrayList<ChiTietSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM chitietsanpham WHERE maPX = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPX);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChiTietSanPhamDTO obj = new ChiTietSanPhamDTO(
                        rs.getString("serialSP"),
                        rs.getInt("maSP"),
                        rs.getInt("maPN"),
                        rs.getInt("maPX")
                    );
                    list.add(obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
