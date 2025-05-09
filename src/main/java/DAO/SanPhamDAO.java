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
    
    public int updateSoLuongTonSanPham(int maSp, int soLuongTon) {
        String sql = "UPDATE sanpham SET soLuongTon = ? WHERE maSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongTon);
            ps.setInt(2, maSp);
            return ps.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMaSpByTenSp(String tenSp) {
        String sql = "SELECT maSP FROM sanpham WHERE tenSP = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenSp.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("maSP");
                }
            }
        } catch (SQLException e) {
            
        }
        return 0; 
    }
    
    public ArrayList<SanPhamDTO> getSanPhamByDateRange(String startDate, String endDate) {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT DISTINCT sp.* FROM sanpham sp " +
                     "JOIN chitietphieunhap ctpn ON sp.maSP = ctpn.maSP " +
                     "JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                     "WHERE pn.ngayNhap BETWEEN ? AND ? AND sp.trangThaiXoa = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO(
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
                    dsSanPham.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }
    public ArrayList<SanPhamDTO> getSanPhamByYearRange(int fromYear, int toYear) {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT DISTINCT sp.* FROM sanpham sp " +
                     "JOIN chitietphieunhap ctpn ON sp.maSP = ctpn.maSP " +
                     "JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                     "WHERE YEAR(pn.ngayNhap) BETWEEN ? AND ? AND sp.trangThaiXoa = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, fromYear);
            ps.setInt(2, toYear);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO(
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
                    dsSanPham.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsSanPham;
    }
    public ArrayList<SanPhamDTO> getSanPhamByMonthYear(int month, int year) {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT DISTINCT sp.* FROM sanpham sp " +
                     "JOIN chitietphieunhap ctpn ON sp.maSP = ctpn.maSP " +
                     "JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                     "WHERE MONTH(pn.ngayNhap) = ? AND YEAR(pn.ngayNhap) = ? AND sp.trangThaiXoa = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO(
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
                    dsSanPham.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsSanPham;
    }
    public ArrayList<SanPhamDTO> getSanPhamByExactDate(String date) {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT DISTINCT sp.* FROM sanpham sp " +
                     "LEFT JOIN chitietphieunhap ctpn ON sp.maSP = ctpn.maSP " +
                     "LEFT JOIN phieunhap pn ON ctpn.maPN = pn.maPN " +
                     "LEFT JOIN chitietphieuxuat ctpx ON sp.maSP = ctpx.maSP " +
                     "LEFT JOIN phieuxuat px ON ctpx.maPX = px.maPX " +
                     "WHERE DATE(pn.ngayNhap) = ? OR DATE(px.ngayXuat) = ? " +
                     "AND sp.trangThaiXoa = 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, date);
            ps.setString(2, date);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO(
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
                    dsSanPham.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }
    
    public static String getNextProductID() {
        String query = "SELECT MAX(maSP) AS max_id FROM sanpham";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int maxId = rs.getInt("max_id"); // Đọc trực tiếp int
                return String.valueOf(maxId + 1); // +1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1"; // Nếu chưa có sản phẩm nào
    }
}