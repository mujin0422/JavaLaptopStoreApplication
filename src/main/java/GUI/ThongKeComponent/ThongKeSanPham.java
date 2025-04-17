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
import org.jfree.chart.plot.CategoryPlot;

public class ThongKeSanPham extends JPanel {
    private UITextField txtYearFrom, txtYearTo;
    private UIButton btnLamMoi, btnLoc;
    private UIButton btnOptionMonth, btnOptionYear, btnOptionDay;
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    private ChiTietPhieuNhapBUS ctpnBUS = new ChiTietPhieuNhapBUS();
    private ChiTietPhieuXuatBUS ctpxBUS = new ChiTietPhieuXuatBUS();
    private JPanel pnlOption, pnlContent;

    public ThongKeSanPham() {
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        //=================================(PANEL OPTION)===============================//
        pnlOption = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlOption.setPreferredSize(new Dimension(0, 25));
        pnlOption.setBackground(UIConstants.MAIN_BACKGROUND);
        btnOptionYear = new UIButton("menuButton", "NĂM", 70, 25);
        btnOptionMonth = new UIButton("menuButton", "THÁNG", 70, 25);
        btnOptionDay = new UIButton("menuButton", "NGÀY", 70, 25);
        btnOptionYear.addActionListener(e -> switchPanel(OptionYearPanel()));
        btnOptionMonth.addActionListener(e -> switchPanel(OptionMonthPanel()));
        btnOptionDay.addActionListener(e -> switchPanel(OptionDayPanel()));
        pnlOption.add(btnOptionYear);
        pnlOption.add(btnOptionMonth);
        pnlOption.add(btnOptionDay);
        //===============================(End Panel Option)=============================//

        //=================================(PANEL CONTENT)==============================//
        pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        //=================================(End Panel Content)==============================//
        

        this.add(pnlOption, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        switchPanel(OptionYearPanel());
    }
    
    private void switchPanel(JPanel newPanel){
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private JPanel OptionYearPanel() {
        // Panel chính chứa mọi thứ
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        // ===================== (NORTH - Filter Panel) ===================== //
        JPanel panelForFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
        panelForFilter.setBackground(Color.WHITE);
        panelForFilter.setPreferredSize(new Dimension(0, 35));
        panelForFilter.add(new UILabel("Từ năm:", 60, 25));
        txtYearFrom = new UITextField(70, 25);
        panelForFilter.add(txtYearFrom);
        panelForFilter.add(new UILabel("Đến năm:", 70, 25));
        txtYearTo = new UITextField(70, 25);
        panelForFilter.add(txtYearTo);
        btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (CENTER - Biểu đồ cột) ===================== //
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        JFreeChart barChart = ChartFactory.createBarChart(
            "THỐNG KÊ SẢN PHẨM THEO NĂM","", "",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false // bật legend (chú thích cột)
        );
        
        barChart.setBackgroundPaint(UIConstants.WHITE_FONT); 
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(UIConstants.WHITE_FONT); 
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);

        ChartPanel chartPanel = new ChartPanel(barChart);
        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện lọc) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                int fromYear = Integer.parseInt(txtYearFrom.getText());
                int toYear = Integer.parseInt(txtYearTo.getText());
                if (fromYear < 2024 || toYear < 2024) {
                    JOptionPane.showMessageDialog(this, "Chỉ được thống kê từ năm 2024 trở đi.");
                    return;
                }
                if (fromYear > toYear) {
                    JOptionPane.showMessageDialog(this, "Năm bắt đầu phải nhỏ hơn hoặc bằng năm kết thúc.");
                    return;
                }
                if ((toYear - fromYear) > 30) {
                    JOptionPane.showMessageDialog(this, "Khoảng cách giữa hai năm không được vượt quá 30 năm.");
                    return;
                }
                model.setRowCount(0);
                dataset.clear();

                // Biểu đồ - Tổng số lượng bán theo năm
                for (int year = fromYear; year <= toYear; year++) {
                    int tongSoLuongNhap = 0;
                    int tongSoLuongBan = 0;

                    for (SanPhamDTO sp : sanPhamBUS.getAllSanPham()) {
                        tongSoLuongNhap += ctpnBUS.getTongSoLuongNhap(sp.getMaSP(), year, year);
                        tongSoLuongBan += ctpxBUS.getTongSoLuongXuat(sp.getMaSP(), year, year);
                    }
                    dataset.addValue(tongSoLuongNhap, "Số lượng Nhập", String.valueOf(year));
                    dataset.addValue(tongSoLuongBan, "Số lượng Bán", String.valueOf(year));
                }

                // Bảng - Dữ liệu từng sản phẩm trong khoảng năm
                for (SanPhamDTO sp : sanPhamBUS.getSanPhamByYearRange(fromYear, toYear)) {
                    int soLuongNhap = ctpnBUS.getTongSoLuongNhap(sp.getMaSP(), fromYear, toYear);
                    int soLuongBan = ctpxBUS.getTongSoLuongXuat(sp.getMaSP(), fromYear, toYear);

                    model.addRow(new Object[]{
                            sp.getMaSP(),
                            sp.getTenSP(),
                            soLuongNhap,
                            soLuongBan
                    });
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm hợp lệ (ví dụ: 2024).");
            }
        });


        // ===================== (Thêm vào mainPanel) ===================== //
        mainPanel.add(panelForFilter, BorderLayout.NORTH);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel OptionMonthPanel() {
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        // ===================== (NORTH - Filter Panel) ===================== //
        JPanel panelForFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelForFilter.setBackground(Color.WHITE);
        panelForFilter.setPreferredSize(new Dimension(0, 35));
        panelForFilter.add(new UILabel("Năm:", 40, 25));
        UITextField txtYear = new UITextField(70, 25);
        panelForFilter.add(txtYear);
        btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (CENTER - Biểu đồ cột đôi) ===================== //
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart barChart = ChartFactory.createBarChart(
                "THỐNG KÊ SẢN PHẨM THEO THÁNG TRONG NĂM","", "",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        barChart.setBackgroundPaint(UIConstants.WHITE_FONT); 
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(UIConstants.WHITE_FONT); 
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);

        ChartPanel chartPanel = new ChartPanel(barChart);
        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện lọc) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                int nam = Integer.parseInt(txtYear.getText());
                if (nam < 2024) {
                    JOptionPane.showMessageDialog(this, "Chỉ được thống kê từ năm 2024 trở đi.");
                    return;
                }
                dataset.clear();
                model.setRowCount(0);

                for (int thang = 1; thang <= 12; thang++) {
                    int tongNhap = ctpnBUS.getTongSoLuongNhap(thang, nam);
                    int tongBan = ctpxBUS.getTongSoLuongXuat(thang, nam);

                    dataset.addValue(tongNhap, "Số lượng nhập", String.valueOf(thang));
                    dataset.addValue(tongBan, "Số lượng bán", String.valueOf(thang));

                    ArrayList<SanPhamDTO> dsSanPham = sanPhamBUS.getSanPhamByMonthYear(thang, nam);
                    for (SanPhamDTO sp : dsSanPham) {
                        int soLuongNhap = ctpnBUS.getTongSoLuongNhap(sp.getMaSP(), thang, nam);
                        int soLuongBan = ctpxBUS.getTongSoLuongXuat(sp.getMaSP(), thang, nam);

                        if (soLuongNhap > 0 || soLuongBan > 0) {
                            model.addRow(new Object[]{
                                    sp.getMaSP(), sp.getTenSP(), soLuongNhap, soLuongBan
                            });
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm hợp lệ (ví dụ: 2024).");
            }
        });

        // ===================== (Thêm vào mainPanel) ===================== //
        mainPanel.add(panelForFilter, BorderLayout.NORTH);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel OptionDayPanel() {
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        // ===================== (NORTH - Filter Panel) ===================== //
        JPanel panelForFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelForFilter.setBackground(Color.WHITE);
        panelForFilter.setPreferredSize(new Dimension(0, 35));
        panelForFilter.add(new UILabel("Ngày (dd/MM/yyyy):", 150, 25));
        UITextField txtDate = new UITextField(100, 25);
        panelForFilter.add(txtDate);
        btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (CENTER - Biểu đồ cột đôi) ===================== //
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart barChart = ChartFactory.createBarChart(
                "THỐNG KÊ SẢN PHẨM THEO NGÀY", "", "",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        barChart.setBackgroundPaint(UIConstants.WHITE_FONT); 
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(UIConstants.WHITE_FONT); 
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(0, 300));

        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG NHẬP", "SỐ LƯỢNG BÁN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện lọc) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                String input = txtDate.getText();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                Date inputDate = sdf.parse(input);
                Date minDate = sdf.parse("01/10/2024");

                if (inputDate.before(minDate)) {
                    JOptionPane.showMessageDialog(this, "Chỉ thống kê từ ngày 01/10/2024 trở đi.");
                    return;
                }

                String date = chuyenDoiNgay(input); // Nếu bạn cần định dạng khác để dùng trong truy vấn
                if (date == null) return;

                model.setRowCount(0);
                dataset.clear();

                for (SanPhamDTO sp : sanPhamBUS.getSanPhamByExactDate(date)) {
                    int soLuongNhap = ctpnBUS.getTongSoLuongNhap(sp.getMaSP(), date);
                    int soLuongBan = ctpxBUS.getTongSoLuongXuat(sp.getMaSP(), date);

                    model.addRow(new Object[]{
                            sp.getMaSP(), sp.getTenSP(), soLuongNhap, soLuongBan
                    });

                    dataset.addValue(soLuongNhap, "Số lượng nhập", sp.getTenSP());
                    dataset.addValue(soLuongBan, "Số lượng bán", sp.getTenSP());
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hợp lệ (vd: 15/04/2025).");
            }
        });
        // ===================== (Thêm vào mainPanel) ===================== //
        mainPanel.add(panelForFilter, BorderLayout.NORTH);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        return mainPanel;
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
}
