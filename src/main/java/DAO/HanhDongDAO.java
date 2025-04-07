package DAO;

import DTO.HanhDongDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HanhDongDAO {
    public int add(HanhDongDTO obj) {
        String sql = "INSERT INTO hanhdong (maHD, tenHD) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getMaHD());
            ps.setString(2, obj.getTenHD());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int edit(HanhDongDTO obj){
        String sql = "UPDATE hanhdong tenHD=? WHERE maHD=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenHD());
            ps.setString(2, obj.getMaHD());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(String maHD) {
        String sql = "UPDATE hanhdong trangThaiXoa=1 WHERE maHD=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maHD);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<HanhDongDTO> getAll() {
        ArrayList<HanhDongDTO> dsChiTietCN = new ArrayList<>();
        String sql = "SELECT * FROM hanhdong WHERE trangThaiXoa=0 ";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChiTietCN.add(new HanhDongDTO( rs.getString("maHD"), rs.getString("tenHD")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietCN;
    }

}
