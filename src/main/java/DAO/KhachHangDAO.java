/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.KhachHangDTO;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Dell Vostro
 */
public class KhachHangDAO {
    public int add(KhachHangDTO obj) {
        String sql = "INSERT INTO khachhang(maKH, tenKH, sdt, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, obj.getMaKH());
            ps.setString(2, obj.getTenKH());
            ps.setString(3, obj.getSdt());
            ps.setString(4, obj.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(KhachHangDTO obj) {
        String sql = "UPDATE khachhang SET tenKH=?, sdt=?, email=? WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, obj.getTenKH());
            ps.setString(2, obj.getSdt());
            ps.setString(3, obj.getEmail());
            ps.setInt(4, obj.getMaKH());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int maKH) {
        String sql = "DELETE FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KhachHangDTO> getAll() {
        ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<>();
        String sql = "SELECT * FROM khachhang";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                dsKhachHang.add(new KhachHangDTO(
                    rs.getInt("maKH"),
                    rs.getString("tenKH"),
                    rs.getString("sdt"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public KhachHangDTO getById(int maKH) {
        String sql = "SELECT * FROM khachhang WHERE maKH=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHangDTO(
                        rs.getInt("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
