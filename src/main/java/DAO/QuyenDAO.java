/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.QuyenDTO;
import DAO.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell Vostro
 */
public class QuyenDAO {
    
    public int add(QuyenDTO obj){
        String sql = "INSERT INTO quyen (maQuyen , tenQuyen) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, obj.getMaQuyen());
            ps.setString(2, obj.getTenQuyen());
            return ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(QuyenDTO obj){
        String sql = "UPDATE quyen SET tenQuyen=? WHERE maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, obj.getTenQuyen());
            ps.setInt(2, obj.getMaQuyen());
            return ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public int delete(int maQuyen){
        String sql ="DELETE FROM quyen WHERE maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, maQuyen);
            return ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<QuyenDTO> getAll(){
        ArrayList<QuyenDTO> dsquyen = new ArrayList<>();
        String sql = "SELECT * FROM quyen";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()){
                dsquyen.add( new QuyenDTO(
                    rs.getInt("maQuyen"),
                    rs.getString("tenQuyen")
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dsquyen;
    }
    
    public QuyenDTO getById(int id){
        String sql ="SELECT * FROM quyen WHERE maQuyen=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new QuyenDTO(
                        rs.getInt("maQuyen"),
                        rs.getString("tenQuyen")
                    );
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
}
