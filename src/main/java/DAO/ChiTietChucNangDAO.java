/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietChucNangDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell Vostro
 */

public class ChiTietChucNangDAO {
    
    public int add(ChiTietChucNangDTO obj) {
        String sql = "INSERT INTO chitietchucnang (maCN, maQuyen) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaCN());
            ps.setInt(2, obj.getMaQuyen());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(ChiTietChucNangDTO obj) {
        String sql = "UPDATE chitietchucnang SET maQuyen=? WHERE maCN=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaQuyen());
            ps.setInt(2, obj.getMaCN());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maCN, int maQuyen) {
        String sql = "DELETE FROM chitietchucnang WHERE maCN=? AND maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCN);
            ps.setInt(2, maQuyen);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ChiTietChucNangDTO> getAll() {
        ArrayList<ChiTietChucNangDTO> dsChiTietCN = new ArrayList<>();
        String sql = "SELECT * FROM chitietchucnang";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsChiTietCN.add(new ChiTietChucNangDTO(
                    rs.getInt("maCN"),
                    rs.getInt("maQuyen")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietCN;
    }
    
    public ChiTietChucNangDTO getById(int maCN, int maQuyen) {
        String sql = "SELECT * FROM chitietchucnang WHERE maCN=? AND maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maCN);
            ps.setInt(2, maQuyen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietChucNangDTO(
                        rs.getInt("maCN"),
                        rs.getInt("maQuyen")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

