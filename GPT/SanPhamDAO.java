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
    
    public int getSoLuongTonSanPham(int maSp){
        String sql = "SELECT soLuongTon FROM sanpham WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("soLuongTon");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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
    
    public String getTenSanPhamByMaSanPham(int maSp){
        String sql ="SELECT tenSP FROM sanpham WHERE maSP =?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenSP");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getTenThByMaSp(int maSp) {
        String sql = "SELECT tenTH FROM sanpham sp JOIN thuonghieu th ON sp.maTH=th.maTH WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenTH");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTenCpuByMaSp(int maSp) {
        String sql = "SELECT tenCPU FROM sanpham sp JOIN cpu ON sp.maCPU=cpu.maCPU WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenCPU");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTenDpgByMaSp(int maSp) {
        String sql = "SELECT tenDPG FROM sanpham sp JOIN dophangiai dpg ON sp.maDPG=dpg.maDPG WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenDPG");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTenLoaiByMaSp(int maSp) {
        String sql = "SELECT tenLoai FROM sanpham sp JOIN phanloai pl ON sp.maLoai=pl.maLoai WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tenLoai");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDungLuongRamByMaSp(int maSp) {
        String sql = "SELECT dungLuongRAM FROM sanpham sp JOIN ram ON sp.maRAM=ram.maRAM WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("dungLuongRAM");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDungLuongRomByMaSp(int maSp) {
        String sql = "SELECT dungLuongROM FROM sanpham sp JOIN rom ON sp.maROM=rom.maROM WHERE maSP=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maSp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("dungLuongROM");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<SanPhamDTO> searchByMaOrTen(String keyword) {
        ArrayList<SanPhamDTO> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE trangThaiXoa=0 AND (LOWER(tenSP) LIKE ? OR CAST(maSP AS CHAR) LIKE ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String kw = "%" + keyword.toLowerCase() + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ketQua.add(new SanPhamDTO(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public ArrayList<SanPhamDTO> getSanPhamByDateRange(String startDate, String endDate) {
        ArrayList<SanPhamDTO> dsSanPham = new ArrayList<>();
        String sql = "SELECT sp.* FROM sanpham sp " +
                     "JOIN chitietphieunhap ctpn ON sp.maSP = ctpn.maSP " +
                     "JOIN phieunhap pn ON ctpn.maPhieuNhap = pn.maPhieuNhap " +
                     "WHERE pn.ngayNhap BETWEEN ? AND ? AND sp.trangThaiXoa = 0";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPham;
    }
}