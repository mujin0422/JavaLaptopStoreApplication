package DAO;

import DTO.ThuongHieuDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ThuongHieuDAO {
    
    public int add(ThuongHieuDTO obj) {
        String sql = "INSERT INTO thuonghieu (maTH, tenTH) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaTH());
            ps.setString(2, obj.getTenTH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(ThuongHieuDTO obj) {
        String sql = "UPDATE thuonghieu SET tenTH=? WHERE maTH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenTH());
            ps.setInt(2, obj.getMaTH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maTH) {
        String sql = "UPDATE thuonghieu SET trangThaiXoa=1 WHERE maTH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTH);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ThuongHieuDTO> getAll() {
        ArrayList<ThuongHieuDTO> dsThuongHieu = new ArrayList<>();
        String sql = "SELECT * FROM thuonghieu WHERE trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsThuongHieu.add(new ThuongHieuDTO(
                    rs.getInt("maTH"),
                    rs.getString("tenTH")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsThuongHieu;
    }
    
    public ThuongHieuDTO getById(int maTH) {
        String sql = "SELECT * FROM thuonghieu WHERE maTH=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ThuongHieuDTO(
                        rs.getInt("maTH"),
                        rs.getString("tenTH")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getMaThByTenTh(String tenTh) {
        String sql = "SELECT maTH FROM thuonghieu WHERE tenTH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenTh);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("maTH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}