package GUI.ThongKeComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;

public class ThongKeDoanhThu extends JPanel {
    private UIButton btnLamMoi, btnLoc;
    private UIButton btnOptionMonth, btnOptionYear, btnOptionDay;
    private PhieuNhapBUS phieuNhapBUS = new PhieuNhapBUS();
    private PhieuXuatBUS phieuXuatBUS = new PhieuXuatBUS();
    private JPanel pnlOption, pnlContent;

    public ThongKeDoanhThu() {
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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        // ===================== (NORTH - Filter Panel) ===================== //
        JPanel panelForFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,5));
        panelForFilter.setBackground(Color.WHITE);
        panelForFilter.setPreferredSize(new Dimension(0, 35));
        panelForFilter.add(new UILabel("Từ năm:", 60, 25));
        UITextField txtYearFrom = new UITextField(70, 25);
        panelForFilter.add(txtYearFrom);
        panelForFilter.add(new UILabel("Đến năm:", 70, 25));
        UITextField txtYearTo = new UITextField(70, 25);
        panelForFilter.add(txtYearTo);
        btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (CENTER - Biểu đồ) ===================== //
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        JFreeChart barChart = ChartFactory.createBarChart(
            "THỐNG KÊ LỢI NHUẬN THEO NĂM", "", "", dataset, PlotOrientation.VERTICAL,
            true, true, false
        );

        barChart.setBackgroundPaint(UIConstants.WHITE_FONT);
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(UIConstants.WHITE_FONT);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));  
        ChartPanel chartPanel = new ChartPanel(barChart);

        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"NĂM", "VỐN", "DOANH THU", "LỢI NHUẬN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện LỌC) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                int fromYear = Integer.parseInt(txtYearFrom.getText().trim());
                int toYear = Integer.parseInt(txtYearTo.getText().trim());

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

                for (int year = fromYear; year <= toYear; year++) {
                    double tongVon = phieuNhapBUS.getTongTienTheoNam(year);
                    double tongDoanhThu = phieuXuatBUS.getTongTienTheoNam(year);
                    double loiNhuan = tongDoanhThu - tongVon;
                    // Đưa vào biểu đồ
                    dataset.addValue(tongVon, "VỐN", String.valueOf(year));
                    dataset.addValue(tongDoanhThu, "DOANH THU", String.valueOf(year));
                    // Đưa vào bảng
                    model.addRow(new Object[]{
                            year,
                            String.format("%,.0f", tongVon),
                            String.format("%,.0f", tongDoanhThu),
                            String.format("%,.0f", loiNhuan)
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
        UIButton btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (CENTER - Biểu đồ lợi nhuận theo tháng) ===================== //
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        JFreeChart barChart = ChartFactory.createBarChart( "THỐNG KÊ LỢI NHUẬN THEO THÁNG TRONG NĂM", "", "",dataset, PlotOrientation.VERTICAL, true, true, false );

        barChart.setBackgroundPaint(UIConstants.WHITE_FONT);
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(UIConstants.WHITE_FONT);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));  
        ChartPanel chartPanel = new ChartPanel(barChart);

        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"THÁNG", "VỐN", "DOANH THU", "LỢI NHUẬN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện lọc) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                int nam = Integer.parseInt(txtYear.getText().trim());
                if (nam < 2024) {
                    JOptionPane.showMessageDialog(this, "Chỉ được thống kê từ năm 2024 trở đi.");
                    return;
                }
                dataset.clear();
                model.setRowCount(0);

                for (int thang = 1; thang <= 12; thang++) {
                    double tongVon = phieuNhapBUS.getTongTienTheoThangNam(thang, nam);
                    double tongDoanhThu = phieuXuatBUS.getTongTienTheoThangNam(thang, nam);
                    double loiNhuan = tongDoanhThu - tongVon;

                    dataset.addValue(tongVon, "VỐN", String.valueOf(thang));
                    dataset.addValue(tongDoanhThu, "DOANH THU", String.valueOf(thang));

                    model.addRow(new Object[]{
                            thang,
                            String.format("%,.0f", tongVon),
                            String.format("%,.0f", tongDoanhThu),
                            String.format("%,.0f", loiNhuan)
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


    private JPanel OptionDayPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        // ===================== (NORTH - Bộ lọc) ===================== //
        JPanel panelForFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelForFilter.setBackground(Color.WHITE);
        panelForFilter.setPreferredSize(new Dimension(0, 35));
        panelForFilter.add(new UILabel("Từ (dd/MM/yyyy):", 130, 25));
        UITextField txtFromDate = new UITextField(100, 25);
        panelForFilter.add(txtFromDate);
        panelForFilter.add(new UILabel("Đến (dd/MM/yyyy):", 135, 25));
        UITextField txtToDate = new UITextField(100, 25);
        panelForFilter.add(txtToDate);
        btnLoc = new UIButton("confirm", "LỌC", 50, 25);
        panelForFilter.add(btnLoc);

        // ===================== (SOUTH - Bảng dữ liệu) ===================== //
        String[] columnNames = {"NGÀY", "VỐN", "DOANH THU", "LỢI NHUẬN"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        UITable table = new UITable(model);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 150));

        // ===================== (Sự kiện LỌC) ===================== //
        btnLoc.addActionListener(e -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                String fromInput = txtFromDate.getText().trim();
                Date fromDate = sdf.parse(fromInput);
                Date minDate = sdf.parse("01/10/2024");
                if (fromDate.before(minDate)) {
                    JOptionPane.showMessageDialog(this, "Chỉ thống kê từ ngày 01/10/2024 trở đi.");
                    return;
                }
                String toInput = txtToDate.getText().trim();
                Date toDate = sdf.parse(toInput);

                if (toDate.before(fromDate)) {
                    JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu.");
                    return;
                }

                model.setRowCount(0);
                long startTime = fromDate.getTime();
                long endTime = toDate.getTime();
                long oneDay = 24 * 60 * 60 * 1000; 

                for (long time = startTime; time <= endTime; time += oneDay) {
                    Date currentDate = new Date(time);
                    String currentDateStr = sdf.format(currentDate);
                    String dbDateStr = chuyenDoiNgay(currentDateStr);

                    double von = phieuNhapBUS.getTongTienTheoNgay(dbDateStr);
                    double doanhThu = phieuXuatBUS.getTongTienTheoNgay(dbDateStr);
                    double loiNhuan = doanhThu - von;

                    model.addRow(new Object[]{
                        currentDateStr,
                        String.format("%,.0f VNĐ", von),
                        String.format("%,.0f VNĐ", doanhThu),
                        String.format("%,.0f VNĐ", loiNhuan)
                    });
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hợp lệ (vd: 15/04/2025).");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thực hiện thống kê.");
                ex.printStackTrace();
            }
        });

        // ===================== (Thêm vào mainPanel) ===================== //
        mainPanel.add(panelForFilter, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
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