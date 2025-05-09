package DAO;

import DTO.QuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        String sql ="UPDATE quyen SET trangThaiXoa=1 WHERE maQuyen=?";
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
        String sql = "SELECT * FROM quyen WHERE trangThaiXoa=0";
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
        String sql ="SELECT * FROM quyen WHERE maQuyen=? AND trangThaiXoa=0";
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
    
    public int getMaQuyenByTenQuyen(String tenQuyen) {
        String sql = "SELECT maQuyen FROM quyen WHERE tenQuyen=? AND trangThaiXoa=0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenQuyen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maQuyen");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }
}
