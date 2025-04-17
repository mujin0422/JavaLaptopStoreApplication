package GUI.ThongKeComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.ChiTietPhieuXuatBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;

public class ThongKeDoanhThu extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch, txtDateFrom, txtDateTo;
    private JButton btnLamMoi, btnLoc, btnToggleView;
    private JComboBox<String> cbxThongKeTheo;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
    private PhieuXuatBUS pxBUS = new PhieuXuatBUS();
    private JPanel contentPanel;
    private JScrollPane tableScrollPane;
    private ChartPanel chartPanel;
    private boolean isTableView = true;

    public ThongKeDoanhThu() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title
        UILabel lblTitle = new UILabel("Thống kê doanh thu");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Filter Panel
        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new GridBagLayout());
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlFilter.setPreferredSize(new Dimension(0, 150));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Search and Filter Type
        JPanel pnlSearchWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlSearchWrapper.setBackground(Color.WHITE);
        pnlSearchWrapper.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txtSearch = new UITextField(30, 14);
        txtSearch.setPreferredSize(new Dimension(250, 30));
        pnlSearchWrapper.add(txtSearch);

        String[] thongKeTheo = {"Sản phẩm", "Tháng", "Năm"};
        cbxThongKeTheo = new JComboBox<>(thongKeTheo);
        cbxThongKeTheo.setPreferredSize(new Dimension(120, 30));
        pnlSearchWrapper.add(new JLabel("Thống kê theo:"));
        pnlSearchWrapper.add(cbxThongKeTheo);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        pnlFilter.add(pnlSearchWrapper, gbc);

        txtSearch.addActionListener(e -> locDuLieu());

        // Date Range
        JPanel pnlDateWrapper = new JPanel(new GridBagLayout());
        pnlDateWrapper.setBackground(Color.WHITE);
        pnlDateWrapper.setBorder(BorderFactory.createTitledBorder("Lọc theo ngày"));
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.fill = GridBagConstraints.HORIZONTAL;
        gbcDate.insets = new Insets(5, 5, 5, 5);

        gbcDate.gridx = 0;
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pnlFilter.add(pnlDateWrapper, gbc);

        // Buttons
        JPanel pnlRefreshWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlRefreshWrapper.setBackground(Color.WHITE);
        pnlRefreshWrapper.setBorder(BorderFactory.createTitledBorder(""));

        btnLamMoi = new UIButton("primary", "Làm mới");
        btnLamMoi.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLamMoi);

        btnLoc = new UIButton("success", "Lọc");
        btnLoc.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLoc);

        btnToggleView = new UIButton("info", "Xem Biểu đồ");
        btnToggleView.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnToggleView);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlFilter.add(pnlRefreshWrapper, gbc);

        add(pnlFilter, BorderLayout.NORTH);

        // Content Panel (Table or Chart)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Table Setup
        String[] columns = {"STT", "Mã sản phẩm", "Vốn", "Doanh thu", "Lợi nhuận"};
        model = new DefaultTableModel(columns, 0);
        table = new UITable(model);
        tableScrollPane = new UIScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Chart Setup (initially null)
        chartPanel = null;

        // Add table to content panel initially
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        // Action Listeners
        btnLamMoi.addActionListener(e -> {
            txtSearch.setText("");
            txtDateFrom.setText("");
            txtDateTo.setText("");
            cbxThongKeTheo.setSelectedIndex(0);
            locDuLieu();
        });

        btnLoc.addActionListener(e -> locDuLieu());

        btnToggleView.addActionListener(e -> {
            isTableView = !isTableView;
            btnToggleView.setText(isTableView ? "Xem Biểu đồ" : "Xem Bảng");
            updateContentPanel();
        });

        locDuLieu();
    }

    private void locDuLieu() {
        String keyword = txtSearch.getText().trim();
        String startDate = txtDateFrom.getText().trim();
        String endDate = txtDateTo.getText().trim();
        String thongKeTheo = (String) cbxThongKeTheo.getSelectedItem();

        try {
            String startDateConverted = chuyenDoiNgay(startDate);
            String endDateConverted = chuyenDoiNgay(endDate);

            if (thongKeTheo.equals("Sản phẩm")) {
                ArrayList<SanPhamDTO> ketQua = sanPhamBUS.searchSanPhamByMaOrTen(keyword);
                if (startDateConverted != null && endDateConverted != null) {
                    ketQua = sanPhamBUS.getSanPhamByDateRange(startDateConverted, endDateConverted);
                }
                hienThiDuLieuTheoSanPham(ketQua);
            } else if (thongKeTheo.equals("Tháng")) {
                hienThiDuLieuTheoThang(startDateConverted, endDateConverted);
            } else if (thongKeTheo.equals("Năm")) {
                hienThiDuLieuTheoNam(startDateConverted, endDateConverted);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lọc dữ liệu: " + ex.getMessage());
        }
    }

    private void hienThiDuLieuTheoSanPham(ArrayList<SanPhamDTO> danhSachSanPham) {
        // Update Table
        ArrayList<Object[]> rows = new ArrayList<>();
        for (SanPhamDTO sp : danhSachSanPham) {
            int soLuongBan = ctpxBUS.getTongSoLuongXuatTheoMaSP(sp.getMaSP());
            int soLuongNhap = sp.getSoLuongTon();
            double vonNhap = calculateVon(sp.getMaSP(), soLuongNhap);
            double doanhThu = calculateDoanhThu(sp.getMaSP(), soLuongBan);
            double loiNhuan = doanhThu - vonNhap;

            rows.add(new Object[]{sp.getMaSP(), vonNhap, doanhThu, loiNhuan});
        }
        rows.sort((a, b) -> Double.compare((double) b[3], (double) a[3])); // Sắp xếp theo lợi nhuận giảm dần
        model.setColumnIdentifiers(new String[]{"STT", "Mã sản phẩm", "Vốn", "Doanh thu", "Lợi nhuận"});
        model.setRowCount(0);
        int stt = 1;
        for (Object[] row : rows) {
            model.addRow(new Object[]{
                stt++,
                row[0],
                String.format("%,.0f VNĐ", (double) row[1]),
                String.format("%,.0f VNĐ", (double) row[2]),
                String.format("%,.0f VNĐ", (double) row[3])
            });
        }

        // Update Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            String maSP = (String) row[0];
            double vonNhap = (double) row[1];
            double doanhThu = (double) row[2];
            double loiNhuan = (double) row[3];
            dataset.addValue(vonNhap, "Vốn Nhập", maSP);
            dataset.addValue(doanhThu, "Doanh Thu", maSP);
            dataset.addValue(loiNhuan, "Lợi Nhuận", maSP);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu theo sản phẩm",
                "Sản phẩm",
                "Số tiền (VNĐ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        updateContentPanel();
    }

    private void hienThiDuLieuTheoThang(String startDate, String endDate) {
        // Tính toán dữ liệu theo tháng
        Map<String, Double[]> thongKeTheoThang = new HashMap<>();
        ArrayList<ChiTietPhieuXuatDTO> danhSachCTPX = ctpxBUS.getAllChiTietPhieuXuat();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();

        for (ChiTietPhieuXuatDTO ctpx : danhSachCTPX) {
            try {
                PhieuXuatDTO px =  pxBUS.getById(ctpx.getMaPX());
                Date ngayXuat = px.getNgayXuat();
                String thangNam = sdf.format(ngayXuat);

                // Kiểm tra khoảng thời gian
                if (startDate != null && endDate != null) {
                    Date start = sdf.parse(startDate.substring(0, 7));
                    Date end = sdf.parse(endDate.substring(0, 7));
                    Date current = sdf.parse(thangNam);
                    if (current.before(start) || current.after(end)) {
                        continue;
                    }
                }

                int soLuongBan = ctpx.getSoLuongSP();
                double doanhThu = calculateDoanhThu(ctpx.getMaSP(), soLuongBan);
                double vonNhap = calculateVon(ctpx.getMaSP(), soLuongBan); // Giả sử nhập = xuất
                double loiNhuan = doanhThu - vonNhap;

                thongKeTheoThang.compute(thangNam, (k, v) -> {
                    if (v == null) v = new Double[]{0.0, 0.0, 0.0};
                    v[0] += vonNhap;
                    v[1] += doanhThu;
                    v[2] += loiNhuan;
                    return v;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Update Table
        ArrayList<Object[]> rows = new ArrayList<>();
        for (Map.Entry<String, Double[]> entry : thongKeTheoThang.entrySet()) {
            String thangNam = entry.getKey();
            Double[] values = entry.getValue();
            rows.add(new Object[]{thangNam, values[0], values[1], values[2]});
        }
        rows.sort((a, b) -> ((String) a[0]).compareTo((String) b[0])); // Sắp xếp theo tháng
        model.setColumnIdentifiers(new String[]{"STT", "Tháng", "Vốn", "Doanh thu", "Lợi nhuận"});
        model.setRowCount(0);
        int stt = 1;
        for (Object[] row : rows) {
            model.addRow(new Object[]{
                stt++,
                row[0],
                String.format("%,.0f VNĐ", (double) row[1]),
                String.format("%,.0f VNĐ", (double) row[2]),
                String.format("%,.0f VNĐ", (double) row[3])
            });
        }

        // Update Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            String thangNam = (String) row[0];
            double vonNhap = (double) row[1];
            double doanhThu = (double) row[2];
            double loiNhuan = (double) row[3];
            dataset.addValue(vonNhap, "Vốn Nhập", thangNam);
            dataset.addValue(doanhThu, "Doanh Thu", thangNam);
            dataset.addValue(loiNhuan, "Lợi Nhuận", thangNam);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu theo tháng",
                "Tháng",
                "Số tiền (VNĐ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        updateContentPanel();
    }

    private void hienThiDuLieuTheoNam(String startDate, String endDate) {
        // Tính toán dữ liệu theo năm
        Map<String, Double[]> thongKeTheoNam = new HashMap<>();
        ArrayList<ChiTietPhieuXuatDTO> danhSachCTPX = ctpxBUS.getAllChiTietPhieuXuat();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();

        for (ChiTietPhieuXuatDTO ctpx : danhSachCTPX) {
            try {
                PhieuXuatDTO px =  pxBUS.getById(ctpx.getMaPX());
                Date ngayXuat = px.getNgayXuat();
                String nam = sdf.format(ngayXuat);

                // Kiểm tra khoảng thời gian
                if (startDate != null && endDate != null) {
                    Date start = sdf.parse(startDate.substring(0, 4));
                    Date end = sdf.parse(endDate.substring(0, 4));
                    Date current = sdf.parse(nam);
                    if (current.before(start) || current.after(end)) {
                        continue;
                    }
                }

                int soLuongBan = ctpx.getSoLuongSP();
                double doanhThu = calculateDoanhThu(ctpx.getMaSP(), soLuongBan);
                double vonNhap = calculateVon(ctpx.getMaSP(), soLuongBan); // Giả sử nhập = xuất
                double loiNhuan = doanhThu - vonNhap;

                thongKeTheoNam.compute(nam, (k, v) -> {
                    if (v == null) v = new Double[]{0.0, 0.0, 0.0};
                    v[0] += vonNhap;
                    v[1] += doanhThu;
                    v[2] += loiNhuan;
                    return v;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Update Table
        ArrayList<Object[]> rows = new ArrayList<>();
        for (Map.Entry<String, Double[]> entry : thongKeTheoNam.entrySet()) {
            String nam = entry.getKey();
            Double[] values = entry.getValue();
            rows.add(new Object[]{nam, values[0], values[1], values[2]});
        }
        rows.sort((a, b) -> ((String) a[0]).compareTo((String) b[0])); // Sắp xếp theo năm
        model.setColumnIdentifiers(new String[]{"STT", "Năm", "Vốn", "Doanh thu", "Lợi nhuận"});
        model.setRowCount(0);
        int stt = 1;
        for (Object[] row : rows) {
            model.addRow(new Object[]{
                stt++,
                row[0],
                String.format("%,.0f VNĐ", (double) row[1]),
                String.format("%,.0f VNĐ", (double) row[2]),
                String.format("%,.0f VNĐ", (double) row[3])
            });
        }
        
        

        // Update Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            String nam = (String) row[0];
            double vonNhap = (double) row[1];
            double doanhThu = (double) row[2];
            double loiNhuan = (double) row[3];
            dataset.addValue(vonNhap, "Vốn Nhập", nam);
            dataset.addValue(doanhThu, "Doanh Thu", nam);
            dataset.addValue(loiNhuan, "Lợi Nhuận", nam);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu theo năm",
                "Năm",
                "Số tiền (VNĐ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        updateContentPanel();
    }

    private void updateContentPanel() {
        contentPanel.removeAll();
        if (isTableView) {
            contentPanel.add(tableScrollPane, BorderLayout.CENTER);
        } else {
            if (chartPanel != null) {
                contentPanel.add(chartPanel, BorderLayout.CENTER);
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
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

    private double calculateDoanhThu(int maSP, int soLuongBan) {
        int giaBan = sanPhamBUS.getGiaSpByMaSp(maSP);
        return soLuongBan * giaBan;
    }


    private double calculateVon(int maSP, int soLuongNhap) {
        int giaBan = sanPhamBUS.getGiaSpByMaSp(maSP);
            return soLuongNhap * giaBan;

    }
}