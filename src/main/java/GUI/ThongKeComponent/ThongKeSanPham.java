package GUI.ThongKeComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class ThongKeSanPham extends JPanel {
    private UITable table;
    private DefaultTableModel model;
    private UITextField txtSearch, txtDateFrom, txtDateTo;
    private UIButton btnLamMoi, btnLoc, btnToggleView;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
    private JPanel pnlFilter, pnlContent;
    private UIScrollPane tableScrollPane;
    private ChartPanel chartPanel;
    private boolean isTableView = true;

    public ThongKeSanPham() {
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        //==============================================================================//
        pnlFilter = new JPanel(new FlowLayout());
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlFilter.setPreferredSize(new Dimension(0, 80)); 
        
 
        // Search
        JPanel pnlSearchWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlSearchWrapper.setBackground(Color.WHITE);
        pnlSearchWrapper.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txtSearch = new UITextField(200, 25);
        pnlSearchWrapper.add(txtSearch);
        pnlFilter.add(pnlSearchWrapper);
        txtSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            ArrayList<SanPhamDTO> ketQua = sanPhamBUS.searchSanPhamByMaOrTen(keyword);
            hienThiDuLieu(ketQua);
        });

        // Date Range
        JPanel pnlDateWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlDateWrapper.setBackground(Color.WHITE);
        pnlDateWrapper.setBorder(BorderFactory.createTitledBorder("Lọc theo ngày"));

        pnlDateWrapper.add(new UILabel("Từ:",30,25));
        txtDateFrom = new UITextField(120, 25);
        pnlDateWrapper.add(txtDateFrom);
        pnlDateWrapper.add(new UILabel("Đến:",35,25));
        txtDateTo = new UITextField(120, 25);
        pnlDateWrapper.add(txtDateTo);
        pnlFilter.add(pnlDateWrapper);

        // Buttons
        JPanel pnlRefreshWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlRefreshWrapper.setBackground(Color.WHITE);
        pnlRefreshWrapper.setBorder(BorderFactory.createTitledBorder("Chức năng"));

        btnLamMoi = new UIButton("menuButton", "LÀM MỚI");
        btnLamMoi.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLamMoi);

        btnLoc = new UIButton("menuButton", "LỌC");
        btnLoc.setPreferredSize(new Dimension(100, 30));
        pnlRefreshWrapper.add(btnLoc);

        btnToggleView = new UIButton("menuButton", "XEM BIỂU ĐỒ");
        btnToggleView.setPreferredSize(new Dimension(140, 30));
        pnlRefreshWrapper.add(btnToggleView);

        pnlFilter.add(pnlRefreshWrapper);

        //==============================================================================//
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);

        // Table Setup
        String[] columns = {"STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN"};
        model = new DefaultTableModel(columns, 0);
        table = new UITable(model);
        tableScrollPane = new UIScrollPane(table);

        // Chart Setup (initially null)
        chartPanel = null;
        // Add table to content panel initially
        pnlContent.add(tableScrollPane, BorderLayout.CENTER);
        add(pnlContent, BorderLayout.CENTER);
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
        
        
        this.add(pnlFilter, BorderLayout.NORTH);
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
        pnlContent.removeAll();
        if (isTableView) {
            pnlContent.add(tableScrollPane, BorderLayout.CENTER);
        } else {
            if (chartPanel != null) {
                pnlContent.add(chartPanel, BorderLayout.CENTER);
            }
        }
        pnlContent.revalidate();
        pnlContent.repaint();
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