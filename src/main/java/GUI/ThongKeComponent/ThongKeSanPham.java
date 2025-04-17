package GUI.ThongKeComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private UITextField txtSearch, txtDateFrom, txtDateTo, txtYearFrom, txtYearTo;
    private UIButton btnLamMoi, btnLoc;
    private UIButton btnOptionMonth, btnOptionYear, btnOptionDay;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
    private JPanel pnlOption, pnlContent;
    private JPanel pnlForFilter;
    private UIScrollPane tableScrollPane;
    private ChartPanel chartPanel;

    public ThongKeSanPham() {
        setLayout(new BorderLayout(5, 5));
        setBackground(UIConstants.MAIN_BACKGROUND);
        setBorder(new EmptyBorder(5, 5, 5, 5));

        // Thanh chọn thời gian
        pnlOption = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlOption.setPreferredSize(new Dimension(0, 35));
        pnlOption.setBackground(Color.WHITE);
        pnlOption.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnOptionYear = new UIButton("menuButton", "Năm", 80, 25);
        btnOptionMonth = new UIButton("menuButton", "Tháng", 80, 25);
        btnOptionDay = new UIButton("menuButton", "Ngày", 80, 25);
        btnOptionYear.addActionListener(e -> OptionYearPanel());
        btnOptionMonth.addActionListener(e -> OptionMonthPanel());
        btnOptionDay.addActionListener(e -> OptionDayPanel());
        pnlOption.add(btnOptionYear);
        pnlOption.add(btnOptionMonth);
        pnlOption.add(btnOptionDay);

        // Panel nội dung chính
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setPreferredSize(new Dimension(0, 500));

        pnlForFilter = new JPanel(new FlowLayout());
        pnlForFilter.setBackground(Color.WHITE);
        pnlForFilter.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Table
        String[] columns = { "STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN" };
        model = new DefaultTableModel(columns, 0);
        table = new UITable(model);
        tableScrollPane = new UIScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(0, 150));

        // Gọi dữ liệu ban đầu và tạo biểu đồ mặc định
        hienThiDuLieu();

        // Add các phần vào pnlContent
        pnlContent.add(pnlForFilter, BorderLayout.NORTH);
        if (chartPanel != null) {
            pnlContent.add(chartPanel, BorderLayout.CENTER);
        }
        pnlContent.add(tableScrollPane, BorderLayout.SOUTH);

        // Add vào chính giao diện
        add(pnlOption, BorderLayout.NORTH);
        add(pnlContent, BorderLayout.CENTER);
    }

    private void showFilterPanel(JPanel filterPanel, ChartPanel newChartPanel, UIScrollPane newTableScrollPane) {
        pnlContent.removeAll();
        pnlForFilter = filterPanel;
        chartPanel = newChartPanel;
        tableScrollPane = newTableScrollPane;
        pnlContent.add(pnlForFilter, BorderLayout.NORTH);
        if (chartPanel != null) {
            pnlContent.add(chartPanel, BorderLayout.CENTER);
        }
        pnlContent.add(tableScrollPane, BorderLayout.SOUTH);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private JPanel OptionYearPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new UILabel("Từ năm:", 60, 25));
        txtYearFrom = new UITextField(100, 25);
        panel.add(txtYearFrom);
        panel.add(new UILabel("Đến năm:", 60, 25));
        txtYearTo = new UITextField(100, 25);
        panel.add(txtYearTo);
        btnLoc = new UIButton("filterButton", "Lọc", 100, 25);
        panel.add(btnLoc);

        btnLoc.addActionListener(e -> {
            try {
                int fromYear = Integer.parseInt(txtYearFrom.getText());
                int toYear = Integer.parseInt(txtYearTo.getText());
                ArrayList<SanPhamDTO> dsSanPham = sanPhamBUS.getSanPhamByYearRange(fromYear, toYear);
                showFilterPanel(panel, taoBieuDo(taoDataset(dsSanPham)), taoBang(dsSanPham));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm hợp lệ (ví dụ: 2022).");
            }
        });

        ArrayList<SanPhamDTO> allSP = sanPhamBUS.getAllSanPham();
        showFilterPanel(panel, taoBieuDo(taoDataset(allSP)), taoBang(allSP));
        return panel;
    }

    private JPanel OptionMonthPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new UILabel("Tháng (1-12)", 60, 25));
        UITextField txtMonth = new UITextField(100, 25);
        panel.add(txtMonth);
        panel.add(new UILabel("Năm (yyyy)", 60, 25));
        UITextField txtYear = new UITextField(100, 25);
        panel.add(txtYear);
        btnLoc = new UIButton("filterButton", "Lọc", 100, 25);
        panel.add(btnLoc);

        btnLoc.addActionListener(e -> {
            try {
                int thang = Integer.parseInt(txtMonth.getText());
                int nam = Integer.parseInt(txtYear.getText());
                ArrayList<SanPhamDTO> dsSanPham = sanPhamBUS.getSanPhamByMonthYear(thang, nam);
                showFilterPanel(panel, taoBieuDo(taoDataset(dsSanPham)), taoBang(dsSanPham));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tháng và năm hợp lệ.");
            }
        });

        ArrayList<SanPhamDTO> allSP = sanPhamBUS.getAllSanPham();
        showFilterPanel(panel, taoBieuDo(taoDataset(allSP)), taoBang(allSP));
        return panel;
    }

    private JPanel OptionDayPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.WHITE);
        panel.add(new UILabel("Ngày(dd/MM/yyyy):", 150, 25));
        UITextField txtDate = new UITextField(150, 25);
        panel.add(txtDate);
        btnLoc = new UIButton("filterButton", "Lọc", 100, 25);
        panel.add(btnLoc);

        btnLoc.addActionListener(e -> {
            try {
                String input = txtDate.getText();
                String date = chuyenDoiNgay(input);
                ArrayList<SanPhamDTO> dsSanPham = sanPhamBUS.getSanPhamByExactDate(date);
                showFilterPanel(panel, taoBieuDo(taoDataset(dsSanPham)), taoBang(dsSanPham));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hợp lệ (vd: 15/04/2025).");
            }
        });

        ArrayList<SanPhamDTO> allSP = sanPhamBUS.getAllSanPham();
        showFilterPanel(panel, taoBieuDo(taoDataset(allSP)), taoBang(allSP));
        return panel;
    }

    private DefaultCategoryDataset taoDataset(ArrayList<SanPhamDTO> danhSachSanPham) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (SanPhamDTO sp : danhSachSanPham) {
            int tongNhap = ctpnBUS.getTongSoLuongNhapTheoMaSP(sp.getMaSP());
            int tongXuat = ctpxBUS.getTongSoLuongXuatTheoMaSP(sp.getMaSP());
            dataset.addValue(tongNhap, "Số lượng nhập", sp.getTenSP());
            dataset.addValue(tongXuat, "Số lượng bán", sp.getTenSP());
        }
        return dataset;
    }

    private ChartPanel taoBieuDo(DefaultCategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê sản phẩm",
                "Sản phẩm",
                "Số lượng",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        ChartPanel panel = new ChartPanel(barChart);
        panel.setPreferredSize(new Dimension(800, 400));
        return panel;
    }

    private UIScrollPane taoBang(ArrayList<SanPhamDTO> danhSachSanPham) {
        DefaultTableModel newModel = new DefaultTableModel(
                new String[] { "STT", "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN" }, 0);
        ArrayList<Object[]> rows = new ArrayList<>();
        for (SanPhamDTO sp : danhSachSanPham) {
            int tongNhap = ctpnBUS.getTongSoLuongNhapTheoMaSP(sp.getMaSP());
            int tongXuat = ctpxBUS.getTongSoLuongXuatTheoMaSP(sp.getMaSP());
            rows.add(new Object[] { sp.getMaSP(), sp.getTenSP(), tongNhap, tongXuat });
        }
        rows.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));
        int stt = 1;
        for (Object[] row : rows) {
            newModel.addRow(new Object[] { stt++, row[0], row[1], row[2], row[3] });
        }

        UITable newTable = new UITable(newModel);
        UIScrollPane newScroll = new UIScrollPane(newTable);
        newScroll.setPreferredSize(new Dimension(0, 150));
        return newScroll;
    }

    private String chuyenDoiNgay(String ngayDauVao) {
        if (ngayDauVao.isEmpty()) return null;
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

    private void hienThiDuLieu() {
        ArrayList<SanPhamDTO> danhSachSanPham = sanPhamBUS.getAllSanPham();
        hienThiDuLieu(danhSachSanPham);
    }

    private void hienThiDuLieu(ArrayList<SanPhamDTO> danhSachSanPham) {
        // Table
        ArrayList<Object[]> rows = new ArrayList<>();
        for (SanPhamDTO sp : danhSachSanPham) {
            int tongNhap = ctpnBUS.getTongSoLuongNhapTheoMaSP(sp.getMaSP());
            int tongXuat = ctpxBUS.getTongSoLuongXuatTheoMaSP(sp.getMaSP());
            rows.add(new Object[] { sp.getMaSP(), sp.getTenSP(), tongNhap, tongXuat });
        }
        rows.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));
        model.setRowCount(0);
        int stt = 1;
        for (Object[] row : rows) {
            model.addRow(new Object[] { stt++, row[0], row[1], row[2], row[3] });
        }

        // Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Object[] row : rows) {
            String tenSP = (String) row[1];
            int tongNhap = (int) row[2];
            int tongXuat = (int) row[3];
            dataset.addValue(tongNhap, "Số lượng nhập", tenSP);
            dataset.addValue(tongXuat, "Số lượng bán", tenSP);
        }
        chartPanel = taoBieuDo(dataset);
    }
}
