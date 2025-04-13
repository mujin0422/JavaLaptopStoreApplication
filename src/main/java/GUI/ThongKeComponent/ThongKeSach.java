package GUI.ThongKeComponent;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import BUS.ChiTietPhieuNhapBUS;
import BUS.ChiTietPhieuXuatBUS;

import Utils.UIButton;
import Utils.UILabel;
import Utils.UIConstants;
import Utils.UITextField;
import Utils.UITable;
import Utils.UIScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThongKeSach extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch, txtDateFrom, txtDateTo;
    private JButton btnLamMoi, btnLoc;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();

    public ThongKeSach() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        UILabel lblTitle = new UILabel("Thống kê sản phẩm");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new GridBagLayout());
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(new EmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); 

        // Tìm kiếm
        JPanel pnlSearchWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearchWrapper.setBackground(Color.WHITE);
        pnlSearchWrapper.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txtSearch = new UITextField(30, 14);
        txtSearch.setPreferredSize(new Dimension(250, 30));
        pnlSearchWrapper.add(txtSearch);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; 
        pnlFilter.add(pnlSearchWrapper, gbc);

        // Tạo sự kiện tìm kiếm
        txtSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            ArrayList<SanPhamDTO> ketQua = sanPhamBUS.searchSanPhamByMaOrTen(keyword);
            hienThiDuLieu(ketQua);  // Cập nhật bảng với kết quả tìm kiếm
        });

        // Lọc theo ngày
        JPanel pnlDateWrapper = new JPanel(new GridBagLayout());
        pnlDateWrapper.setBackground(Color.WHITE);
        pnlDateWrapper.setBorder(BorderFactory.createTitledBorder("Lọc theo ngày"));
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.fill = GridBagConstraints.HORIZONTAL;
        gbcDate.insets = new Insets(5, 5, 5, 5);

        pnlDateWrapper.add(new UILabel("Từ:"), gbcDate);
        gbcDate.gridx = 1;
        txtDateFrom = new UITextField(15, 14);
        txtDateFrom.setPreferredSize(new Dimension(120, 30));
        pnlDateWrapper.add(txtDateFrom, gbcDate);

        gbcDate.gridx = 2;
        pnlDateWrapper.add(new JLabel("Đến:"), gbcDate);
        gbcDate.gridx = 3;
        txtDateTo = new UITextField(15, 14);
        txtDateTo.setPreferredSize(new Dimension(120, 30));
        pnlDateWrapper.add(txtDateTo, gbcDate);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        pnlFilter.add(pnlDateWrapper, gbc);

        // Nút làm mới
        JPanel pnlRefreshWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlRefreshWrapper.setBackground(Color.WHITE);
        pnlRefreshWrapper.setBorder(BorderFactory.createTitledBorder(""));

        btnLamMoi = new UIButton("primary", "Làm mới");
        btnLamMoi.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLamMoi);

        // Nút lọc
        btnLoc = new UIButton("success", "Lọc");
        btnLoc.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLoc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        pnlFilter.add(pnlRefreshWrapper, gbc);

        btnLamMoi.addActionListener(e -> {
            txtSearch.setText("");
            txtDateFrom.setText("");
            txtDateTo.setText("");
            hienThiDuLieu(); // Hiển thị lại dữ liệu không lọc
        });

        // Thêm sự kiện lọc theo ngày
        btnLoc.addActionListener(e -> {
            String startDate = txtDateFrom.getText().trim();
            String endDate = txtDateTo.getText().trim();

            try {
                String startDateConverted = chuyenDoiNgay(startDate);
                String endDateConverted = chuyenDoiNgay(endDate);

                if (startDateConverted != null && endDateConverted != null) {
                    ArrayList<SanPhamDTO> ketQua = sanPhamBUS.searchSanPhamByDateRange(startDateConverted, endDateConverted);
                    hienThiDuLieu(ketQua);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(pnlFilter, BorderLayout.NORTH);

        // Cấu hình bảng
        String[] columns = {"STT", "Mã máy", "Tên máy", "Số lượng nhập", "Số lượng bán"};
        model = new DefaultTableModel(columns, 0);
        table = new UITable(model);
        JScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(scrollPane, BorderLayout.SOUTH);

        hienThiDuLieu();
    }

    private void hienThiDuLieu(ArrayList<SanPhamDTO> danhSachSanPham) {
        ArrayList<Object[]> rows = new ArrayList<>();

        for (SanPhamDTO sp : danhSachSanPham) {
            int tongNhap = ctpnBUS.getTongSoLuongNhapTheoMaSP(sp.getMaSP());
            int tongXuat = ctpxBUS.getTongSoLuongXuatTheoMaSP(sp.getMaSP());

            rows.add(new Object[]{sp.getMaSP(), sp.getTenSP(), tongNhap, tongXuat});
        }

        rows.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));

        model.setRowCount(0);

        int stt = 1;
        for (Object[] row : rows) {
            model.addRow(new Object[]{
                stt++,
                row[0],  
                row[1],  
                row[2], 
                row[3]
            });
        }
    }

    private void hienThiDuLieu() {
        ArrayList<SanPhamDTO> danhSachSanPham = sanPhamBUS.getAllSanPham();
        hienThiDuLieu(danhSachSanPham);
    }
    
    private String chuyenDoiNgay(String ngayDauVao) {
        if (ngayDauVao.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdfNguoiDung = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdfDB = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdfNguoiDung.parse(ngayDauVao);
            return sdfDB.format(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày không hợp lệ. Vui lòng nhập dd/MM/yyyy");
            return null;
        }
    }
}
