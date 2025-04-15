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
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

import BUS.ChiTietPhieuNhapBUS;
import BUS.ChiTietPhieuXuatBUS;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;

public class ThongKeSach extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch, txtDateFrom, txtDateTo;
    private JButton btnLamMoi, btnLoc, btnToggleView;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
    private JPanel contentPanel;
    private JScrollPane tableScrollPane;
    private ChartPanel chartPanel;
    private boolean isTableView = true;

    public ThongKeSach() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Title
        UILabel lblTitle = new UILabel("Thống kê sản phẩm");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        // Filter Panel
        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new GridBagLayout());
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlFilter.setPreferredSize(new Dimension(0, 100)); // Tăng chiều cao
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Search
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

        txtSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            ArrayList<SanPhamDTO> ketQua = sanPhamBUS.searchSanPhamByMaOrTen(keyword);
            hienThiDuLieu(ketQua);
        });

        // Date Range
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
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlFilter.add(pnlRefreshWrapper, gbc);

        add(pnlFilter, BorderLayout.NORTH);

        // Content Panel (Table or Chart)
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Table Setup
        String[] columns = {"STT", "Mã máy", "Tên máy", "Số lượng nhập", "Số lượng bán"};
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
            hienThiDuLieu();
        });

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

        btnToggleView.addActionListener(e -> {
            isTableView = !isTableView;
            btnToggleView.setText(isTableView ? "Xem Biểu đồ" : "Xem Bảng");
            updateContentPanel();
        });

        hienThiDuLieu();
    }

    private void hienThiDuLieu(ArrayList<SanPhamDTO> danhSachSanPham) {
        // Update Table
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
            model.addRow(new Object[]{stt++, row[0], row[1], row[2], row[3]});
        }

        // Update Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            String tenSP = (String) row[1];
            int tongNhap = (int) row[2];
            int tongXuat = (int) row[3];
            dataset.addValue(tongNhap, "Số lượng nhập", tenSP);
            dataset.addValue(tongXuat, "Số lượng bán", tenSP);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê sản phẩm",
                "Sản phẩm",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 400));

        updateContentPanel();
    }

    private void hienThiDuLieu() {
        ArrayList<SanPhamDTO> danhSachSanPham = sanPhamBUS.getAllSanPham();
        hienThiDuLieu(danhSachSanPham);
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

}